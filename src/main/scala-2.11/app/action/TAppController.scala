package app.action

import app.gui.model.AppModel

/**
  * Created by Dragos on 18.06.2016.
  */
trait TAppController extends Action{

  def scaleImage(model: AppModel): AppModel

  def clearCanvas(model: AppModel): AppModel
  def ajustImage(model: AppModel): AppModel

}
