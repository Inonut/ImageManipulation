package app2.gui.model

import javafx.scene.Node
import javafx.scene.image.Image
import javafx.stage.Window

/**
  * Created by Dragos on 7/5/2016.
  */
sealed trait Model

case class ClearImageModelParams(canvasWidth: Double, canvasHeight: Double) extends Model
case class ClearImageModelResult(canvasWidth: Double, canvasHeight: Double) extends Model

case class InportImageModelParams(window: Window) extends Model
case class InportImageModelResult(image: Image) extends Model

case class ResetValuesModelParams() extends Model
case class ResetValuesModelResult(percentRed: Double, percentBlue: Double, percentGreen: Double, opacity: Double, contrast: Double, brightness: Double, hue: Double, saturation: Double) extends Model

case class ScaleImageModelParams(image: Image, imageWidth: Double, imageHeight: Double) extends Model
case class ScaleImageModelResult(image: Image) extends Model

case class AjustImageModelParams(image: Image, imageWidth: Double, imageHeight: Double, percentRed: Double, percentBlue: Double, percentGreen: Double, opacity: Double, contrast: Double, brightness: Double, hue: Double, saturation: Double) extends Model
case class AjustImageModelResult(image: Image) extends Model
