package app2.gui.modelView

import java.io.File
import java.net.URL
import java.util.{Optional, ResourceBundle}
import javafx.beans.binding.Bindings
import javafx.beans.property.{DoubleProperty, SimpleDoubleProperty}
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.scene.image.Image
import javafx.stage.Window
import javafx.util.StringConverter
import javafx.util.converter.NumberStringConverter

import app2.action._
import app2.gui.controller.AppController
import app2.gui.model.{AppModel, Model}
import app2.util.{Message, Util}
import app2.util.Util._

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Dragos on 7/5/2016.
  */
class AppMV(implicit appController: AppController) extends ModelView{

  val converter: StringConverter[Number] = new NumberStringConverter
  val inportImageAction = new InportImageAction()
  val scaleImageAction = new ScaleImageAction()
  val clearImageAction = new ClearImageAction()
  val resetValuesAction = new ResetValuesAction()

  def onInport_Click(): Unit =  inportImageAction.executeAsync(appController.grid.getScene.getWindow) map onSuccess map scaleImageUtil recover onError

  def onClear_Click(): Unit = clearImageAction.executeAsync(AppModel(canvasHeight = appController.canvas.getHeight, canvasWidth = appController.canvas.getWidth)) map onSuccess recover onError

  def onReset_Click() = resetValuesAction.executeAsync() map onSuccess recover onError

  override def binding(): Unit = {

    Bindings.bindBidirectional(appController.redSliderLabel.textProperty, appController.redSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.greenSliderLabel.textProperty, appController.greenSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.blueSliderLabel.textProperty, appController.blueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.opacitySliderLabel.textProperty, appController.opacitySlider.valueProperty, converter)

    Bindings.bindBidirectional(appController.contrastSliderLabel.textProperty, appController.contrastSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.hueSliderLabel.textProperty, appController.hueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.brightnessSliderLabel.textProperty, appController.brightnessSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.saturationSliderLabel.textProperty, appController.saturationSlider.valueProperty, converter)

    appController.canvas.widthProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => scaleImageUtil(appController.inportedImage))
    appController.canvas.heightProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => scaleImageUtil(appController.inportedImage))

    appController.inportButton.setOnAction((event: ActionEvent) => onInport_Click())
    appController.clearButton.setOnAction((event: ActionEvent) => onClear_Click())
    appController.resetButton.setOnAction((event: ActionEvent) => onReset_Click())
  }

  override val updateView: PartialFunction[Any, Unit] = {
    case response: Complete[Image] if Message.ScaleImageAction.equals(response.message) =>
      appController.canvas.getGraphicsContext2D.drawImage(response.obj, 0, 0)
    case response: Complete[Image] if Message.InportImageAction.equals(response.message) =>
      appController.inportedImage = response.obj
    case response: Complete[AppModel] if Message.ClearImageAction.equals(response.message) =>
      appController.canvas.getGraphicsContext2D.clearRect(0,0,response.obj.canvasWidth, response.obj.canvasHeight)
      appController.inportedImage = null
    case response: Complete[AppModel] if Message.ResetValuesAction.equals(response.message) =>
      appController.redSlider.setValue(response.obj.percentRed)
      appController.greenSlider.setValue(response.obj.percentGreen)
      appController.blueSlider.setValue(response.obj.percentBlue)
      appController.opacitySlider.setValue(response.obj.opacity)
      appController.contrastSlider.setValue(response.obj.contrast)
      appController.hueSlider.setValue(response.obj.hue)
      appController.brightnessSlider.setValue(response.obj.brightness)
      appController.saturationSlider.setValue(response.obj.saturation)
    case _ => println("nothing to update")
  }


  val scaleImageUtil: PartialFunction[Any, Unit] = {
    case _ =>
      val scaleParams = AppModel(
        image = appController.inportedImage,
        imageWidth = appController.canvas.getWidth,
        imageHeight = appController.canvas.getHeight
      )
      scaleImageAction.executeAsync(scaleParams) map onSuccess recover onError
  }
}
