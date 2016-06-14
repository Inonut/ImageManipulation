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

/**
  * Created by Dragos on 6/14/2016.
  */
class AppView extends GridPane with View{

  override protected val controller: Controller = new AppController
  override protected val nodes: AppNodesModel = init()

  override def init(): AppNodesModel = {

    val canvas = new Canvas{
      id = "canvas"
    }


    this.columnConstraints = List(
      new ColumnConstraints()
    )

    this.rowConstraints = List(
      new RowConstraints(),
      new RowConstraints()
    )

    this.children = List(
      new Pane{
        id = "pane"

        GridPane.setRowIndex(this,0)
        GridPane.setColumnIndex(this,0)

        children = List(

        )
      },
      new Button{
        id = "button"

        GridPane.setRowIndex(this,1)
        GridPane.setColumnIndex(this,0)

        text = "Import"

        onMouseClicked = (e: MouseEvent) => controller.execute(owner, Constants.addImage)

      }
    )

    AppNodesModel(canvas)
  }

  override def updateView(model: Model): Unit = {}

  override def getDataView: AppModel = AppModel(nodes.canvas.snapshot(null,null))

}

object AppView {
  def apply(): AppView = new AppView
}
