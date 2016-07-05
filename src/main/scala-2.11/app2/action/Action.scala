package app2.action

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import app2.gui.model.Model

import async.Async.async
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/5/2016.
  */
trait Action {

  protected implicit val timeout = new Timeout(10, TimeUnit.SECONDS)

  protected val execute: PartialFunction[Any, Any]

  def executeAsync(model: Any): Future[Any] = async { this.execute(model) }

  def executeSync(model: Any): Future[Any] = {

    var result: Any = null
    var exception: Exception = null

    try { result = this.execute(model) }
    catch { case ex: Exception => exception = ex }

    if(exception != null){
      async { throw exception }
    } else {
      async { result }
    }
  }
}


