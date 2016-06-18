package appplication.util

import akka.actor.{Actor, ActorSystem}

/**
  * Created by Dragos on 6/14/2016.
  */
object Constants {

  val system = ActorSystem("appplication")

  val collectData = "collectData"

  val clearCanvas = "clearCanvas"
  val applicationCSS = "application.css"
  val addImage = "addImage"



}
