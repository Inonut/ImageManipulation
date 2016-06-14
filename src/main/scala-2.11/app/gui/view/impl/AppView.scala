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
      GridPane.setRowIndex(this,0)
      GridPane.setColumnIndex(this,0)

      styleClass add "pane"

      children add canvas
    }
    val importButton = new Button {
      GridPane.setRowIndex(this,1)
      GridPane.setColumnIndex(this,0)

      text = "Import"

      onAction = (e: ActionEvent) => controller.execute(owner, Constants.addImage)
    }


    this.columnConstraints = List(
      columnConstraints
    )

    this.rowConstraints = List(
      rowConstraints,
      rowConstraints
    )

    this.children = List(
      canvasPane,
      importButton
    )

    AppNodesModel(canvas)
  }

  override def updateView(model: Model): Unit = {
    val appModel = model.asInstanceOf[AppModel]

    val x = nodes.canvas.width.value
    val y = nodes.canvas.height.value
    nodes.canvas.graphicsContext2D.drawImage(appModel.canvasImage, x, y)
  }

  override def getDataView: AppModel = AppModel(nodes.canvas.snapshot(null,null))

}

object AppView {
  def apply(): AppView = new AppView
}
