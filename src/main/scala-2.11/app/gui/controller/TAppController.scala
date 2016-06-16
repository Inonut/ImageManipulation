package app.gui.controller

import app.gui.model.AppModel

/**
  * Created by Dragos on 6/15/2016.
  */
trait TAppController extends Controller{

  def addImage(model: AppModel): AppModel

  def clearCanvas(model: AppModel): AppModel
}
