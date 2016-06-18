package appplication.gui.view

import java.util.concurrent.TimeUnit

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import appplication.gui.model.Model
import appplication.util.Util

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 6/14/2016.
  */
trait View {

  protected implicit val timeout = new Timeout(10, TimeUnit.SECONDS)
  protected implicit val owner: View = this
  protected implicit val controller: ActorRef

  protected val nodes: Model

  def execute(params: Any): Unit = {
    controller ? params map {
      case model: Model => Util.runOnFxThread {this.updateView(model)}
      case _ => Unit
    } recover {
      case e => e.printStackTrace()
    }
  }

  def updateView(model: Model): Unit
  def getDataView: Model


  protected def init(): Model
}
