package app2.action

import javafx.scene.image.Image
import javafx.stage.Window

import app2.gui.model.AppModel
import app2.util.Message

/**
  * Created by Dragos on 7/6/2016.
  */
class ClearImageAction extends Action[AppModel, Complete[AppModel]] {


  override protected def execute(params: AppModel): Complete[AppModel] = Complete(Message.ClearImageAction, AppModel(canvasHeight = params.canvasHeight, canvasWidth = params.canvasWidth))
}
