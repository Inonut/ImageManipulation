package app2.gui.modelView

import java.net.URL
import java.util.{Optional, ResourceBundle}
import javafx.beans.binding.Bindings
import javafx.beans.property.{DoubleProperty, SimpleDoubleProperty}
import javafx.event.ActionEvent
import javafx.util.StringConverter
import javafx.util.converter.NumberStringConverter

import app2.gui.action.{Action, InportAction}
import app2.gui.controller.AppController
import app2.gui.model.{AppModel, Model}
import app2.util.Util
import app2.util.Util._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Dragos on 7/5/2016.
  */
class AppMV(implicit appController: AppController) extends ModelView{

  val converter: StringConverter[Number] = new NumberStringConverter
  val inportImage: Action = new InportAction()

  def binding(): Unit = {

    Bindings.bindBidirectional(appController.redSliderLabel.textProperty, appController.redSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.greenSliderLabel.textProperty, appController.greenSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.blueSliderLabel.textProperty, appController.blueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.opacitySliderLabel.textProperty, appController.opacitySlider.valueProperty, converter)

    Bindings.bindBidirectional(appController.contrastSliderLabel.textProperty, appController.contrastSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.hueSliderLabel.textProperty, appController.hueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.brightnessSliderLabel.textProperty, appController.brightnessSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.saturationSliderLabel.textProperty, appController.saturationSlider.valueProperty, converter)

    appController.inportButton.setOnAction((event: ActionEvent) => inportImage.executeAsync() map onSuccess recover onError)
    //appController.clearButton.setOnAction((event: ActionEvent) => onClear_Click())
    //appController.resetButton.setOnAction((event: ActionEvent) => onReset_Click())
  }

  override val updateView: PartialFunction[Model, Unit] = {
    case appModel: AppModel =>
      println("updated")
  }

}
