package app2.gui.action

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import async.Async.async

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/5/2016.
  */
trait Action {

  protected implicit val timeout = new Timeout(10, TimeUnit.SECONDS)

  def execute: PartialFunction[Any, Any]

  def executeAsync(): Future[Any] = {
    async{
      this.execute()
    }
  }
}
