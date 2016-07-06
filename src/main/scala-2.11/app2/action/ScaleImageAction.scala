package app2.action

import javafx.scene.image.{Image, ImageView}

import app2.gui.model.{ScaleImageModelParams, ScaleImageModelResult}
import app2.util.{Message, Util}

/**
  * Created by Dragos on 7/6/2016.
  */
class ScaleImageAction extends Action[ScaleImageModelParams, ScaleImageModelResult]{

  val imageView = new ImageView

  override protected def execute(model: ScaleImageModelParams): ScaleImageModelResult = {

    imageView.setFitHeight(model.imageHeight)
    imageView.setFitWidth(model.imageWidth)
    imageView.setImage(model.image)

    val newImage = Util.runOnFxThread { imageView.snapshot(null, null) }

    ScaleImageModelResult(newImage)
  }
}
