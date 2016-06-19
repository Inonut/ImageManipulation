package app.gui.controller

import java.util.concurrent.TimeUnit
import java.util.logging.Logger

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import app.gui.model.Model
import app.util.{Command, Util}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 18.06.2016.
  */
abstract class Controller {

  protected implicit val timeout = new Timeout(10, TimeUnit.SECONDS)
  protected implicit val owner: Controller = this
  protected implicit val action: ActorRef

  def updateView(model: Model)

  //def getModel: Model = getModel(Command.COLLECT_DATA)
  //def getModel(command: Command): Model

  def execute(modelParam: Model): Unit = {
    action ? modelParam map {
      case modelReceive: Model => Util.runOnFxThread { owner.updateView(modelReceive) }
      case _ => println("Nu s-a primit un Model.")
    } recover {
      case e => e.printStackTrace()
    }
  }
}
