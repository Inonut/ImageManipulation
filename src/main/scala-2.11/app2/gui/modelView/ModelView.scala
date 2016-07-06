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

  val updateView: PartialFunction[Any, Unit]

  val onSuccess: (Any) => Any = {
    case model => Util.runOnFxThread{this.updateView(model)}
    case _ => println("----")
  }
  val onError: PartialFunction[Throwable, Any] = {
    case e => e.printStackTrace()
  }

  def binding(): Unit
}
