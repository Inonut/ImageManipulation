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

  appModelView.redSlider.valueProperty.addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => {

        execute( AppModel(
          command = Command.AJUST_IMAGE,
          percentRed = newValue.doubleValue(),
          percentGreen = appModelView.greenSlider.getValue,
          percentBlue = appModelView.blueSlider.getValue,
          opacity = appModelView.opacitySlider.getValue,
          image = appModelView.inportedImage.getImage,
          imageHeight = try appModelView.inportedImage.getImage.getWidth catch{case e: Throwable => 0},
          imageWidth = try appModelView.inportedImage.getImage.getHeight catch{case e: Throwable => 0}
        ))
  })

  appModelView.greenSlider.valueProperty.addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => {

        execute( AppModel(
          command = Command.AJUST_IMAGE,
          percentRed = appModelView.redSlider.getValue,
          percentGreen = newValue.doubleValue(),
          percentBlue = appModelView.blueSlider.getValue,
          opacity = appModelView.opacitySlider.getValue,
          image = appModelView.inportedImage.getImage,
          imageHeight = try appModelView.inportedImage.getImage.getWidth catch{case e: Throwable => 0},
          imageWidth = try appModelView.inportedImage.getImage.getHeight catch{case e: Throwable => 0}
        ))
  })

  appModelView.blueSlider.valueProperty.addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => {

        execute( AppModel(
          command = Command.AJUST_IMAGE,
          percentRed = appModelView.redSlider.getValue,
          percentGreen = appModelView.greenSlider.getValue,
          percentBlue = newValue.doubleValue(),
          opacity = appModelView.opacitySlider.getValue,
          image = appModelView.inportedImage.getImage,
          imageHeight = try appModelView.inportedImage.getImage.getWidth catch{case e: Throwable => 0},
          imageWidth = try appModelView.inportedImage.getImage.getHeight catch{case e: Throwable => 0}
        ))
  })

  appModelView.opacitySlider.valueProperty.addListener((observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) => {

    execute( AppModel(
      command = Command.AJUST_IMAGE,
      percentRed = appModelView.redSlider.getValue,
      percentGreen = appModelView.greenSlider.getValue,
      percentBlue = appModelView.blueSlider.getValue,
      opacity = newValue.doubleValue(),
      image = appModelView.inportedImage.getImage,
      imageHeight = try appModelView.inportedImage.getImage.getWidth catch{case e: Throwable => 0},
      imageWidth = try appModelView.inportedImage.getImage.getHeight catch{case e: Throwable => 0}
    ))
  })
}
