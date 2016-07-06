package app2.action

import app2.gui.model.{ResetValuesModelParams, ResetValuesModelResult}
import app2.util.Message

/**
  * Created by Dragos on 7/6/2016.
  */
class ResetValuesAction extends Action[ResetValuesModelParams, ResetValuesModelResult] {

  override protected def execute(params: ResetValuesModelParams): ResetValuesModelResult = ResetValuesModelResult(100,100,100,100,0,0,0,0)
}
