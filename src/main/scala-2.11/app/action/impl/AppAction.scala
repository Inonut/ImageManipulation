package app.action.impl


import java.io.{File, FileInputStream}
import javafx.scene.image.{Image, ImageView}
import javafx.stage.FileChooser

import akka.actor.Actor._
import akka.actor.{ActorRef, Props}
import app.action.TAppController
import app.gui.model.AppModel
import app.util.{Command, Constants, Util}

/**
  * Created by Dragos on 18.06.2016.
  */
class AppAction extends TAppController{

  override def receive: Receive = {
    case data: AppModel if Command.IMPORT_IMAGE.equals(data.command) => sender ! addImage(data)
    case data: AppModel if Command.CLEAR.equals(data.command) => sender ! clearCanvas(data)
    case _ => println("CANCI!!!")
  }

  override def addImage(data: AppModel): AppModel = {
    Util.runOnFxThread {
      new FileChooser().showOpenDialog(data.window)
    } match {
      case null => new AppModel(Command.IMPORT_IMAGE)
      case file: File =>

        val inputStream = new FileInputStream(file)
        try {

          val imageView = new ImageView
          imageView.setFitHeight(data.canvasHeight)
          imageView.setFitWidth(data.canvasWidth)
          imageView.setImage(new Image(inputStream))

          new AppModel(Command.IMPORT_IMAGE, Util.runOnFxThread { imageView.snapshot(null, null) })
        } finally {
          inputStream.close()
        }
    }
  }

  override def clearCanvas(model: AppModel): AppModel = new AppModel(Command.CLEAR, model.canvasWidth, model.canvasHeight)
}

object AppAction {
  val getActor: ActorRef = Constants.system.actorOf(Props[AppAction], name = "app")
}
