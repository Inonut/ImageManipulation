package app.gui.view

import app.gui.controller.Controller
import app.gui.model.Model

import scalafx.stage.Window

/**
  * Created by Dragos on 6/14/2016.
  */
trait View {

  protected implicit val owner: View = this

  protected val nodes: Model
  protected val controller: Controller


  protected def init(): Model

  def updateView(model: Model): Unit
  def getDataView: Model
}
