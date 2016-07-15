package app2.action.actions

import app2.action.ActionControl
import app2.action.model.{ClearImageModelParams, ClearImageModelResult}

/**
  * Created by Dragos on 7/6/2016.
  */
class ClearImageAction extends ActionControl[ClearImageModelParams, ClearImageModelResult] {


  override protected def executeAsyncControled(params: ClearImageModelParams): ClearImageModelResult = ClearImageModelResult(params.canvasWidth, params.canvasHeight)
}
