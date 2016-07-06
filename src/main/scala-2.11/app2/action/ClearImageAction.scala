package app2.action

import javafx.scene.image.Image
import javafx.stage.Window

import app2.gui.model.{ClearImageModelParams, ClearImageModelResult}
import app2.util.Message

/**
  * Created by Dragos on 7/6/2016.
  */
class ClearImageAction extends Action[ClearImageModelParams, ClearImageModelResult] {


  override protected def execute(params: ClearImageModelParams): ClearImageModelResult = ClearImageModelResult(params.canvasWidth, params.canvasHeight)
}
