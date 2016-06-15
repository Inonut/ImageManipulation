package app.gui.controller

import app.gui.model.Model
import app.gui.view.View
import app.util.Util
import com.sun.javafx.application.PlatformImpl
import com.sun.javafx.tk.PlatformImage

import scala.async.Async._
import scala.compat.Platform
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.stage.FileChooser


/**
  * Created by Dragos on 6/14/2016.
  */
trait Controller {

  protected def continueExecute: PartialFunction[Any, Model]

  def execute(params: Any)(implicit owner: View): Unit = {
    async { continueExecute(params)} map {
      case (model: Model) => Util.runOnFxThread {owner.updateView(model)}
      case _ => Unit
    } recover {
      case (e: Exception) => e.printStackTrace()
    }
  }
}
