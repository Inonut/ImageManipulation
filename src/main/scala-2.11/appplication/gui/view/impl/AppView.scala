package appplication.gui.view.impl

import akka.actor.{ActorRef, Props}
import appplication.gui.controller.Controller
import appplication.gui.controller.impl.AppController
import appplication.gui.model.{AppModel, AppNodesModel, Model}
import appplication.gui.view.View
import appplication.util.Constants

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Button
import scalafx.scene.layout._

/**
  * Created by Dragos on 6/14/2016.
  */
class AppView extends GridPane with View {

  override protected implicit val controller: ActorRef = Constants.system.actorOf(Props[AppController], name = "appplication")
  override protected val nodes: AppNodesModel = init()

  override def updateView(modelReceive: Model): Unit = modelReceive match {
    case appModel: AppModel =>
      if (appModel.canvasImage != null && Constants.addImage.equals(appModel.command)){
        nodes.canvas.graphicsContext2D.drawImage(appModel.canvasImage, 0, 0)
      }
      if(Constants.clearCanvas.equals(appModel.command)){
        nodes.canvas.graphicsContext2D.clearRect(0, 0, appModel.canvasWidth, appModel.canvasHeight)
      }
    case _ => Unit
  }

  override def getDataView: AppModel = AppModel(
    Constants.collectData,
    nodes.canvas.snapshot(null, null),
    nodes.canvas.width.value,
    nodes.canvas.height.value,
    this.getScene.getWindow)

  override protected def init(): AppNodesModel = {

    val canvas = new Canvas
    val columnConstraints = new ColumnConstraints
    val rowConstraints = new RowConstraints
    val canvasPane = new Pane {

      styleClass add "pane"

      prefWidth <==> canvas.width
      prefHeight <==> canvas.height

      children add canvas
    }
    val importButton = new Button {

      text = "Import"

      onAction = (e: ActionEvent) => owner execute (owner.getDataView, Constants.addImage)
    }
    val clearButton = new Button {

      text = "Clear"

      onAction = (e: ActionEvent) => owner execute (owner.getDataView, Constants.clearCanvas)
    }
    val hBox = new HBox {
      children addAll(importButton, clearButton)
    }


    this.columnConstraints add columnConstraints

    this.rowConstraints addAll(rowConstraints, rowConstraints)

    this.children addAll(canvasPane, hBox)

    GridPane.setRowIndex(canvasPane, 0)
    GridPane.setColumnIndex(canvasPane, 0)
    GridPane.setRowIndex(hBox, 1)
    GridPane.setColumnIndex(hBox, 0)

    AppNodesModel(Constants.collectData, canvas, canvasPane)
  }

}

object AppView {
  def apply(): AppView = new AppView
}
