package app2.gui.modelView

import java.util.concurrent.TimeUnit
import javafx.fxml.Initializable

import akka.actor.ActorRef
import akka.util.Timeout
import app2.gui.model.Model

/**
  * Created by Dragos on 7/5/2016.
  */
trait ModelView {

  def updateView: PartialFunction[Model, Unit]
}
