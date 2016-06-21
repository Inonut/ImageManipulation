package app.action.impl


import javafx.scene.effect.ColorAdjust
import javafx.scene.image.{ImageView, PixelFormat, WritableImage}

import akka.actor.{ActorRef, Props}
import app.action.TAppController
import app.gui.model.AppModel
import app.util.{Command, Constants, Util}

/**
  * Created by Dragos on 18.06.2016.
  */
class AppAction extends TAppController{

  override def receive: Receive = {
    case data: AppModel if Command.SCALE_IMAGE.equals(data.command) && data.image != null => sender ! scaleImage(data)
    case data: AppModel if Command.CLEAR.equals(data.command) => sender ! clearCanvas(data)
    case data: AppModel if Command.AJUST_IMAGE.equals(data.command) && data.image != null => sender ! ajustImage(data)
    case data: AppModel if Command.RESET.equals(data.command) => sender ! reset(data)
    case _ => sender ! Command.NONE
  }

  override def scaleImage(data: AppModel): AppModel = {

    val imageView = new ImageView
    imageView.setFitHeight(data.imageHeight)
    imageView.setFitWidth(data.imageWidth)
    imageView.setImage(data.image)

    val newImage = Util.runOnFxThread { imageView.snapshot(null, null) }

    AppModel(canvasImage = newImage, image = newImage)
  }

  override def clearCanvas(model: AppModel): AppModel = AppModel(command = Command.CLEAR, canvasWidth = model.canvasWidth, canvasHeight = model.canvasHeight)

  override def ajustImage(model: AppModel): AppModel = {
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

    AppModel(
      canvasImage = newImage,
      percentRed = model.percentRed
    )
  }

  override def reset(model: AppModel): AppModel =
    AppModel(
      command = Command.RESET,
      percentRed = 100,
      percentBlue = 100,
      percentGreen = 100,
      opacity = 100,
      contrast = 0,
      brightness = 0,
      hue = 0,
      saturation = 0
    )
}

object AppAction {
  val getActor: ActorRef = Constants.system.actorOf(Props[AppAction], name = "app")
}
