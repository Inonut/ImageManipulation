package app.gui.controller.impl


import java.io.{File, FileInputStream}

import app.gui.controller.{Controller, TAppController}
import app.gui.model.{AppModel, Model}
import app.util.{Constants, Util}

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.FileChooser


/**
  * Created by Dragos on 6/14/2016.
  */
class AppController extends TAppController{

  override def continueExecute: PartialFunction[Any, Model] = {
    case (data: AppModel, Constants.addImage) => addImage(data)
    case (data: AppModel, Constants.clearCanvas) => clearCanvas(data)
  }

  override def addImage(data: AppModel): AppModel = {
    Util.runOnFxThread { new FileChooser().showOpenDialog(null) } match {
      case null => null
      case file: File =>
        val inputStream = new FileInputStream(file)

        try {

          val imageView = new ImageView()
          imageView.fitHeight = data.canvasHeight
          imageView.fitWidth = data.canvasWidth
          imageView.image = new Image(inputStream)

          AppModel(Constants.addImage, Util.runOnFxThread {imageView.snapshot(null,null)})
        } finally {
          inputStream.close()
        }
    }
  }

  override def clearCanvas(model: AppModel): AppModel = AppModel(Constants.clearCanvas, model.canvasWidth, model.canvasHeight)
}
