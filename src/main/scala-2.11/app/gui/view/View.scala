package app.gui.view

import app.gui.controller.Controller
import app.gui.model.Model

/**
  * Created by Dragos on 6/14/2016.
  */
trait View {

  protected val controller: Controller
  protected val owner = this
  protected val nodes: Model

  def init(): Model

  def updateView(model: Model): Unit
  def getDataView: Model
}
