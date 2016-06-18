package appplication.gui.controller.impl


import java.io.{File, FileInputStream}

import akka.actor.Actor
import akka.actor.Actor.Receive
import appplication.gui.controller.TAppController
import appplication.gui.model.{AppModel, Model}
import appplication.util.{Constants, Util}

import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.FileChooser


/**
  * Created by Dragos on 6/14/2016.
  */
class AppController extends TAppController {

  override def receive: Receive = {
    case (data: AppModel, Constants.addImage) => sender ! addImage(data)
    case (data: AppModel, Constants.clearCanvas) => sender ! clearCanvas(data)
  }

  override def addImage(data: AppModel): AppModel = {
    Util.runOnFxThread {
      new FileChooser().showOpenDialog(data.view)
    } match {
      case null => AppModel(Constants.addImage)
      case file: File =>

        val inputStream = new FileInputStream(file)
        try {

          val imageView = new ImageView()
          imageView.fitHeight = data.canvasHeight
          imageView.fitWidth = data.canvasWidth
          imageView.image = new Image(inputStream)

          AppModel(Constants.addImage, Util.runOnFxThread {
            imageView.snapshot(null, null)
          })
        } finally {
          inputStream.close()
        }
    }
  }

  override def clearCanvas(model: AppModel): AppModel = AppModel(Constants.clearCanvas, model.canvasWidth, model.canvasHeight)


}
