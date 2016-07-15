package app2.action.actions

import javafx.scene.image.ImageView

import app2.action.ActionControl
import app2.action.model.{ScaleImageModelParams, ScaleImageModelResult}
import app2.util.Util

/**
  * Created by Dragos on 7/6/2016.
  */
class ScaleImageAction extends ActionControl[ScaleImageModelParams, ScaleImageModelResult]{

  val imageView = new ImageView

  override protected def executeAsyncControled(model: ScaleImageModelParams): ScaleImageModelResult = {

    imageView.setFitHeight(model.imageHeight)
    imageView.setFitWidth(model.imageWidth)
    imageView.setImage(model.image)

    val newImage = Util.runOnFxThread { imageView.snapshot(null, null) }

    ScaleImageModelResult(newImage)
  }
}
