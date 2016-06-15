package app.gui.view.impl

import app.gui.controller.Controller
import app.gui.controller.impl.AppController
import app.gui.model.{AppModel, AppNodesModel, Model}
import app.gui.view.View
import app.util.css.CSS
import app.util.{Constants, Util}

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Button
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout._
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.image.ImageView

/**
  * Created by Dragos on 6/14/2016.
  */
class AppView extends GridPane with View{

  override protected val controller: Controller = new AppController
  override protected val nodes: AppNodesModel = init()

  override def init(): AppNodesModel = {

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

      onAction = (e: ActionEvent) => controller execute(owner.getDataView, Constants.addImage)
    }


    this.columnConstraints = List(
      columnConstraints
    )

    this.rowConstraints = List(
      rowConstraints,
      rowConstraints
    )

    GridPane.setRowIndex(canvasPane,0)
    GridPane.setColumnIndex(canvasPane,0)
    GridPane.setRowIndex(importButton,1)
    GridPane.setColumnIndex(importButton,0)

    this.children = List(
      canvasPane,
      importButton
    )

    AppNodesModel(canvas,canvasPane)
  }

  override def updateView(modelReceive: Model): Unit = modelReceive match {
    case null => Unit
    case appModel: AppModel =>
      nodes.canvas.graphicsContext2D.drawImage(appModel.canvasImage, 0, 0)
  }

  override def getDataView: AppModel = AppModel(
    nodes.canvas.snapshot(null,null),
    nodes.canvas.width.value,
    nodes.canvas.height.value)

}

object AppView {
  def apply(): AppView = new AppView
}
