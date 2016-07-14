package app2.action

import javafx.scene.image.Image
import javafx.stage.Window

import app2.action.model.{ClearImageModelParams, ClearImageModelResult}
import app2.util.Message

/**
  * Created by Dragos on 7/6/2016.
  */
class ClearImageAction extends ActionControl[ClearImageModelParams, ClearImageModelResult] {


  override protected def execute(params: ClearImageModelParams): ClearImageModelResult = ClearImageModelResult(params.canvasWidth, params.canvasHeight)
}
