package app.action.impl


import akka.actor.{ActorRef, Props}
import app.action.TAppController
import app.gui.model.AppModel
import app.util.Constants

/**
  * Created by Dragos on 18.06.2016.
  */
class AppAction extends TAppController{
  override def receive: Receive = {
    case msg =>
      //Thread.sleep(2000)
      println(msg)

      sender ! new AppModel(Constants.commandNone)

  }
}

object AppAction {
  val getActor: ActorRef = Constants.system.actorOf(Props[AppAction], name = "app")
}
