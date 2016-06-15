package app.gui.controller

import app.gui.model.Model
import app.gui.view.View
import app.util.Util

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by Dragos on 6/14/2016.
  */
trait Controller {

  def execute(params: Any)(implicit owner: View): Unit = {
    async {
      continueExecute(params)
    } map {
      case (model: Model) => Util.runOnFxThread {
        owner.updateView(model)
      }
      case _ => Unit
    } recover {
      case (e: Exception) => e.printStackTrace()
    }
  }

  protected def continueExecute: PartialFunction[Any, Model]
}
