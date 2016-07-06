package app2.action

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import app2.gui.model.Model
import app2.util.Message

import async.Async.async
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/5/2016.
  */
trait Action[T, R] {

  protected implicit val timeout = new Timeout(10, TimeUnit.SECONDS)

  protected def execute(params: T): R

  def executeAsync(params: T): Future[R] = async { this.execute(params) }

  def executeSync(params: T): R = this.execute(params)
}

case class Complete[T](message: Message, obj: T)
