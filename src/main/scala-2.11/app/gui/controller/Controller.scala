package app.gui.controller

import app.gui.model.Model
import app.gui.view.View

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.stage.FileChooser


/**
  * Created by Dragos on 6/14/2016.
  */
trait Controller {

  protected def continueExecute: PartialFunction[Any, Any]

  def execute(view: View, command: String): Unit = {
    val data = view.getDataView
    async { continueExecute(data, command)} recover {
      case (model: Model) => view.updateView(model)
      case (e: Exception) => e.printStackTrace()
      case _ => println("-----")
    }
  }

}
