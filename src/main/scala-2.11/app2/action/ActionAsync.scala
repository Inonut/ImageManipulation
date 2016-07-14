package app2.action

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Dragos on 13.07.2016.
  */
trait ActionAsync[T, R] extends Action[T, R]{

  def executeAsync(params: T): Future[R] = async { this.execute(params) }

}
