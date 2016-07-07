package app2.gui.modelView

import java.util.concurrent.TimeUnit
import javafx.fxml.Initializable

import akka.actor.ActorRef
import akka.util.Timeout
import app2.gui.model.Model
import app2.util.Util

/**
  * Created by Dragos on 7/5/2016.
  */
trait ModelView {

  def updateView: PartialFunction[Model, Unit]

  def onSuccess[T]: Model => T = model => {
    Util.runOnFxThread {this.updateView(model)}
    null.asInstanceOf[T]
  }

  def onError[T]: PartialFunction[Throwable, T] = {
    case e =>
      e.printStackTrace()
      null.asInstanceOf[T]
  }

  def binding(): Unit
}
