package app.gui.controller.impl

import java.io.File
import java.util.Optional
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.stage.FileChooser

import akka.actor.ActorRef
import app.action.impl.AppAction
import app.gui.controller.Controller
import app.gui.model.{AppModel, AppModelView, Model}
import app.util.Util._
import app.util.{Command, Util}

/**
  * Created by Dragos on 19.06.2016.
  */
class AppControllerScala(appModelView: AppModelView) extends Controller {
  override protected implicit val action: ActorRef = AppAction.getActor

  override def updateView(model: Model): Unit = model match {
    case data: AppModel =>
      if (data.canvasImage != null) {
        appModelView.canvas.getGraphicsContext2D.drawImage(data.canvasImage, 0, 0)
      }

      if (data.image != null) {
        appModelView.inportedImage.setImage(data.image)
      }

      if (Command.CLEAR.equals(data.command)) {
        appModelView.canvas.getGraphicsContext2D.clearRect(0, 0, data.canvasWidth, data.canvasHeight)
        appModelView.inportedImage.setImage(null)
      }

      if(Command.RESET.equals(data.command)){
        appModelView.redSlider.setValue(data.percentRed)
        appModelView.greenSlider.setValue(data.percentGreen)
        appModelView.blueSlider.setValue(data.percentBlue)
        appModelView.opacitySlider.setValue(data.opacity)

        appModelView.contrastSlider.setValue(data.contrast)
        appModelView.hueSlider.setValue(data.hue)
        appModelView.brightnessSlider.setValue(data.brightness)
        appModelView.saturationSlider.setValue(data.saturation)
      }
  }

  def onImport_Click(actionEvent: ActionEvent): Unit = {

    Optional.ofNullable(new FileChooser().showOpenDialog(appModelView.grid.getScene.getWindow))
      .ifPresent((file: File) => {
        execute(
          AppModel(
            command = Command.SCALE_IMAGE,
            image = Util.getImageFromFile(file),
            imageWidth = appModelView.canvas.getWidth,
            imageHeight = appModelView.canvas.getHeight
          )
        )
      })
  }


  def onClear_Click(actionEvent: ActionEvent): Unit = execute(AppModel(command = Command.CLEAR, canvasWidth = appModelView.canvas.getWidth, canvasHeight = appModelView.canvas.getHeight))

  def onReset_Click(actionEvent: ActionEvent) {
    execute(AppModel(
      command = Command.RESET
    ))
  }

  def changeImageColor = (observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => {

    execute( AppModel(
      command = Command.AJUST_IMAGE,
      percentRed = appModelView.redSlider.getValue,
      percentGreen = appModelView.greenSlider.getValue,
      percentBlue = appModelView.blueSlider.getValue,
      opacity = appModelView.opacitySlider.getValue,

      contrast = appModelView.contrastSlider.getValue,
      hue = appModelView.hueSlider.getValue,
      brightness = appModelView.brightnessSlider.getValue,
      saturation = appModelView.saturationSlider.getValue,

      image = appModelView.inportedImage.getImage,
      imageHeight = try appModelView.inportedImage.getImage.getWidth catch{case e: Throwable => 0},
      imageWidth = try appModelView.inportedImage.getImage.getHeight catch{case e: Throwable => 0}
    ))
  }

  appModelView.redSlider.valueProperty.addListener(changeImageColor)
  appModelView.blueSlider.valueProperty.addListener(changeImageColor)
  appModelView.greenSlider.valueProperty.addListener(changeImageColor)
  appModelView.opacitySlider.valueProperty.addListener(changeImageColor)

  appModelView.saturationSlider.valueProperty.addListener(changeImageColor)
  appModelView.contrastSlider.valueProperty.addListener(changeImageColor)
  appModelView.hueSlider.valueProperty.addListener(changeImageColor)
  appModelView.brightnessSlider.valueProperty.addListener(changeImageColor)
}
