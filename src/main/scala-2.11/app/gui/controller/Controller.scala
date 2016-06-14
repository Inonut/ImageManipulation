package app.gui.controller

import app.gui.model.Model
import app.gui.view.View

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by Dragos on 6/14/2016.
  */
trait Controller {

  def continueExecute(data: Model, command: String): PartialFunction[String, Model]

  def execute(view: View, command: String): Unit = {
    val data = view.getDataView
    async { continueExecute(data, command)} map{
      case (model: Model) => view.updateView(model)
      case (e: Exception) => print("bau")
    }
  }

}
