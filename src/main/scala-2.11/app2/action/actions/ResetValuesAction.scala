package app2.action.actions

import javafx.scene.paint.Color

import app2.action.ActionControl
import app2.action.model.{ResetValuesModelParams, ResetValuesModelResult}

/**
  * Created by Dragos on 7/6/2016.
  */
class ResetValuesAction extends ActionControl[ResetValuesModelParams, ResetValuesModelResult] {

  override protected def executeAsyncControled(params: ResetValuesModelParams): ResetValuesModelResult = ResetValuesModelResult(100,100,100,100,0,0,0,0, Color.RED)
}
