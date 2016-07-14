package app2.action

import javafx.scene.effect.ColorAdjust
import javafx.scene.image.{ImageView, PixelFormat, WritableImage}

import app2.action.model.{AjustImageModelParams, AjustImageModelResult}
import app2.util.Util

/**
  * Created by Dragos on 7/6/2016.
  */
class AjustImageAction extends ActionControl[AjustImageModelParams, AjustImageModelResult]{

  override protected def execute(model: AjustImageModelParams): AjustImageModelResult = {

    val oldPixels = new Array[Int](model.imageWidth.toInt * model.imageHeight.toInt)
    model.image.getPixelReader.getPixels(0, 0, model.imageWidth.toInt, model.imageHeight.toInt, PixelFormat.getIntArgbPreInstance, oldPixels, 0, model.imageWidth.toInt)

    val newPixels = for(pixel <- oldPixels) yield {
      val a = (((pixel >> 24) & 255) * (model.opacity / 100)).toInt
      val r = (((pixel >> 16) & 255) * (model.percentRed / 100)).toInt
      val g = (((pixel >> 8) & 255) * (model.percentGreen / 100)).toInt
      val b = ((pixel & 255) * (model.percentBlue / 100)).toInt

      (a << 24) | (r << 16) | (g << 8) | b
    }

    val writableImage = new WritableImage(model.imageWidth.toInt, model.imageHeight.toInt)
    writableImage.getPixelWriter.setPixels(0, 0, model.imageWidth.toInt, model.imageHeight.toInt, PixelFormat.getIntArgbPreInstance, newPixels, 0, model.imageWidth.toInt)


    val colorAdjust = new ColorAdjust()
    colorAdjust.setContrast(model.contrast)
    colorAdjust.setHue(model.hue)
    colorAdjust.setBrightness(model.brightness)
    colorAdjust.setSaturation(model.saturation)

    val imageView = new ImageView(writableImage)
    imageView.setEffect(colorAdjust)

    val newImage = Util.runOnFxThread { imageView.snapshot(null, null) }

    AjustImageModelResult(newImage)
  }
}
