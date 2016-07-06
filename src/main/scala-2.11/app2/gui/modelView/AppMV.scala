package app2.gui.modelView

import javafx.beans.binding.Bindings
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.scene.image.Image
import javafx.util.StringConverter
import javafx.util.converter.NumberStringConverter

import app2.action._
import app2.gui.controller.AppController
import app2.gui.model._
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
  val adjustImageAction = new AjustImageAction()

  def onInport_Click(): Unit =  inportImageAction.executeAsync(InportImageModelParams(appController.grid.getScene.getWindow)) map onSuccess recover onError map scaleImageUtil

  def onClear_Click(): Unit = clearImageAction.executeAsync(ClearImageModelParams(appController.canvas.getWidth, appController.canvas.getHeight)) map onSuccess recover onError

  def onReset_Click() = resetValuesAction.executeAsync(ResetValuesModelParams()) map onSuccess recover onError

  override def binding(): Unit = {

    Bindings.bindBidirectional(appController.redSliderLabel.textProperty, appController.redSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.greenSliderLabel.textProperty, appController.greenSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.blueSliderLabel.textProperty, appController.blueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.opacitySliderLabel.textProperty, appController.opacitySlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.contrastSliderLabel.textProperty, appController.contrastSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.hueSliderLabel.textProperty, appController.hueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.brightnessSliderLabel.textProperty, appController.brightnessSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.saturationSliderLabel.textProperty, appController.saturationSlider.valueProperty, converter)

    appController.redSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.greenSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.blueSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.opacitySlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.contrastSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.hueSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.brightnessSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())
    appController.saturationSlider.valueProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => adjustImageUtil())

    appController.canvas.widthProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => scaleImageUtil())
    appController.canvas.heightProperty().addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => scaleImageUtil())

    appController.inportButton.setOnAction((event: ActionEvent) => onInport_Click())
    appController.clearButton.setOnAction((event: ActionEvent) => onClear_Click())
    appController.resetButton.setOnAction((event: ActionEvent) => onReset_Click())
  }

  override val updateView: PartialFunction[Any, Unit] = {
    case response: ScaleImageModelResult =>
      appController.canvas.getGraphicsContext2D.drawImage(response.image, 0, 0)
    case response: InportImageModelResult =>
      appController.inportedImage = response.image
    case response: ClearImageModelResult =>
      appController.canvas.getGraphicsContext2D.clearRect(0,0,response.canvasWidth, response.canvasHeight)
      appController.inportedImage = null
    case response: ResetValuesModelResult =>
      appController.redSlider.setValue(response.percentRed)
      appController.greenSlider.setValue(response.percentGreen)
      appController.blueSlider.setValue(response.percentBlue)
      appController.opacitySlider.setValue(response.opacity)
      appController.contrastSlider.setValue(response.contrast)
      appController.hueSlider.setValue(response.hue)
      appController.brightnessSlider.setValue(response.brightness)
      appController.saturationSlider.setValue(response.saturation)
    case response: AjustImageModelResult =>
      appController.canvas.getGraphicsContext2D.drawImage(response.image, 0, 0)
    case _ => println("nothing to update")
  }

  val scaleImageUtil: PartialFunction[Any, Unit] = {
    case response: AjustImageModelResult => scaleImage(response.image)
    case _ => scaleImage(appController.inportedImage)
  }

  def scaleImage(image: Image): Unit = {
    if(image != null) {
      scaleImageAction.executeAsync(ScaleImageModelParams(image, appController.canvas.getWidth, appController.canvas.getHeight)) map onSuccess recover onError
    }
  }

  val adjustImageUtil: PartialFunction[Any, Unit] = {
    case _ =>
      if(appController.inportedImage != null){
        adjustImageAction.executeAsync(AjustImageModelParams(
          appController.inportedImage,
          appController.inportedImage.getWidth,
          appController.inportedImage.getHeight,
          appController.redSlider.getValue,
          appController.blueSlider.getValue,
          appController.greenSlider.getValue,
          appController.opacitySlider.getValue,
          appController.contrastSlider.getValue,
          appController.brightnessSlider.getValue,
          appController.hueSlider.getValue,
          appController.saturationSlider.getValue
        )) map scaleImageUtil
      }
  }
}