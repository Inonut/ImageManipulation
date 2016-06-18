package app.util

import akka.actor.ActorSystem

/**
  * Created by Dragos on 17.06.2016.
  */
object Constants {

  val system = ActorSystem("appplication")

  val applicationFXML = "appplication.fxml"
  val applicationCSS = "application.css"


  val commandTest = "test"
  val commandNone = "none"

}
