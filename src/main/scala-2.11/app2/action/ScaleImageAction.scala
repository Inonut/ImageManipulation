package app2.action

import javafx.scene.image.{Image, ImageView}

import app2.gui.model.AppModel
import app2.util.{Message, Util}

/**
  * Created by Dragos on 7/6/2016.
  */
class ScaleImageAction extends Action[AppModel, Complete[Image]]{

  val imageView = new ImageView

  override protected def execute(model: AppModel): Complete[Image] = {

    imageView.setFitHeight(model.imageHeight)
    imageView.setFitWidth(model.imageWidth)
    imageView.setImage(model.image)

    val newImage = Util.runOnFxThread { imageView.snapshot(null, null) }

    Complete(Message.ScaleImageAction, newImage)
  }
}
