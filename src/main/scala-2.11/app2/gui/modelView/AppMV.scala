package app2.gui.modelView

import javafx.beans.binding.{Bindings, When}
import javafx.beans.value.ObservableValue
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Node
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Circle
import javafx.util.StringConverter
import javafx.util.converter.NumberStringConverter

import app2.action.actions._
import app2.action.model._
import app2.gui.controller.AppController
import app2.util.Include._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
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
  val addPointAction = new AddPointAction()

  def onInport_Click(): Unit =  inportImageAction.execute(InportImageModelParams(appController.grid.getScene.getWindow)) map onSuccess flatMap scaleImageUtil recover onError

  def onClear_Click(): Unit = clearImageAction.execute(ClearImageModelParams(appController.canvas.getWidth, appController.canvas.getHeight)) map onSuccess recover onError

  def onReset_Click() = resetValuesAction.execute(ResetValuesModelParams()) map onSuccess recover onError

  def onRefresh_Click() = adjustImageUtil()

  def onCanvas_MouseClick(x: Double, y: Double) = addPointAction.execute(AddPointModelParams(x, y, appController.colorPicker.getValue)) map onSuccess recover onError

  override def binding(): Unit = {

    Bindings.bindBidirectional(appController.redSliderLabel.textProperty, appController.redSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.greenSliderLabel.textProperty, appController.greenSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.blueSliderLabel.textProperty, appController.blueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.opacitySliderLabel.textProperty, appController.opacitySlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.contrastSliderLabel.textProperty, appController.contrastSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.hueSliderLabel.textProperty, appController.hueSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.brightnessSliderLabel.textProperty, appController.brightnessSlider.valueProperty, converter)
    Bindings.bindBidirectional(appController.saturationSliderLabel.textProperty, appController.saturationSlider.valueProperty, converter)

    val isStart = new When(appController.kMeansStratButton.disableProperty).then(false).otherwise(true)
    appController.kMeansPlayButton.disableProperty bind isStart
    appController.kMeansStopButton.disableProperty bind isStart
    appController.kMeansRestartButton.disableProperty bind isStart

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

    appController.canvas.setOnMouseClicked((event: MouseEvent) => onCanvas_MouseClick(event.getX, event.getY))

    appController.inportButton.setOnAction((event: ActionEvent) => onInport_Click())
    appController.clearButton.setOnAction((event: ActionEvent) => onClear_Click())
    appController.resetButton.setOnAction((event: ActionEvent) => onReset_Click())
    appController.refreshButton.setOnAction((event: ActionEvent) => onRefresh_Click())

    appController.kMeansStratButton.onMouseClickedProperty.addListener((observable: ObservableValue[_ <: EventHandler[_ >: MouseEvent]], oldValue: EventHandler[_ >: MouseEvent], newValue: EventHandler[_ >: MouseEvent]) => appController.kMeansStratButton setDisable true)

    /*appController.kMeansRestartButton.setOnAction((event: ActionEvent) => kMeandAction.restart())
    appController.kMeansStopButton.setOnAction((event: ActionEvent) => kMeandAction.stop())
    appController.kMeansPlayButton.setOnAction((event: ActionEvent) => kMeandAction.play())
    appController.kMeansStratButton.setOnAction((event: ActionEvent) => kMeandAction.executeAsync(KmeansModelParams()))*/

    //kMeandAction.executeSync(KmeansModelParams())
  }

  override def init(): Unit = onReset_Click()

  override def updateView: PartialFunction[Any, Unit] = {
    case response: ScaleImageModelResult =>
      appController.canvas.getGraphicsContext2D.drawImage(response.image, 0, 0)
    case response: InportImageModelResult =>
      appController.inportedImage = response.image
    case response: ClearImageModelResult =>
      appController.canvas.getGraphicsContext2D.clearRect(0,0,response.canvasWidth, response.canvasHeight)
      appController.inportedImage = null
      appController.canvasPane.getChildren.removeIf((node: Node) => !node.isInstanceOf[Canvas])
    case response: ResetValuesModelResult =>
      appController.redSlider.setValue(response.percentRed)
      appController.greenSlider.setValue(response.percentGreen)
      appController.blueSlider.setValue(response.percentBlue)
      appController.opacitySlider.setValue(response.opacity)
      appController.contrastSlider.setValue(response.contrast)
      appController.hueSlider.setValue(response.hue)
      appController.brightnessSlider.setValue(response.brightness)
      appController.saturationSlider.setValue(response.saturation)
      appController.colorPicker.setValue(response.color)
    case response: AjustImageModelResult =>
      appController.canvas.getGraphicsContext2D.drawImage(response.image, 0, 0)
    case response: AddPointModelResult =>
      appController.canvasPane.getChildren add new Circle(response.x, response.y, response.size, response.color)
    case _ => println("nothing to update")
  }


/**** chesti care nu imi plac... ***/

  def scaleImageUtil: PartialFunction[Any, Future[Model]] = {
    case response: AjustImageModelResult => scaleImage(response.image)
    case _ => scaleImage(appController.inportedImage)
  }

  def scaleImage(image: Image): Future[Model] = {
    scaleImageAction.execute(ScaleImageModelParams(image, appController.canvas.getWidth, appController.canvas.getHeight)) map onSuccess recover onError
  }

  def adjustImageUtil: PartialFunction[Any, Unit] = {
    case _ =>
      if(appController.inportedImage != null){
        adjustImageAction.execute(AjustImageModelParams(
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
        )) flatMap scaleImageUtil recover onError
      }
  }

}