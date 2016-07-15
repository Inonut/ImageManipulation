package app2.action

import java.util.concurrent.Semaphore

import scala.async.Async._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/5/2016.
  */
trait Action[T, R] {
  def execute(params: T): R
}

trait ActionAsync[T, R] extends Action[T, Future[R]]{

  override def execute(params: T): Future[R] = async { this.executeAsync(params) }

  protected def executeAsync(params: T): R
}

trait ActionSync[T, R] extends Action[T, R]{

  override def execute(params: T): R = this.executeSync(params)

  protected def executeSync(params: T): R
}

trait ActionControl[T, R] extends ActionAsync[T, R]{

  val stopEvent = new Event[Any]
  val restartEvent = new Event[T]
  val playEvent = new Event[Any]
  val distroyEvent = new Event[Any]

  def clearEvents(): Unit = {
    stopEvent.clearListeners()
    restartEvent.clearListeners()
    playEvent.clearListeners()
    distroyEvent.clearListeners()
  }

  override protected def executeAsync(params: T): R = {

    restartEvent addListener ActionSync { executeAsyncControled(params) }
    stopEvent addListener ActionSync {
      val semaphore = new Semaphore(1)
      playEvent addListener ActionSync { semaphore.release() }
      semaphore.acquire()
    }
    distroyEvent addListener ActionSync { throw new Exception }



    executeAsyncControled(params)
  }

  protected def executeAsyncControled(params: T): R
}

object Action {
  def apply[T, R](f: =>R): Action[T, R] = new Action[T, R]() {
    override def execute(params: T): R = f
  }
}

object ActionAsync {
  def apply[T, R](f: =>R): ActionAsync[T, R] = new ActionAsync[T, R]() {
    override protected def executeAsync(params: T): R = f
  }
}

object ActionSync {
  def apply[T, R](f: =>R): ActionSync[T, R] = new ActionSync[T, R]() {
    override protected def executeSync(params: T): R = f
  }
}

object ActionControl {
  def apply[T, R](f: =>R): ActionControl[T, R] = new ActionControl[T, R]() {
    override protected def executeAsyncControled(params: T): R = f
  }
}