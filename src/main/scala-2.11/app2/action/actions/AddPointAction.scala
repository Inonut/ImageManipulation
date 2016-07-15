package app2.action.actions

import app2.action.ActionControl
import app2.action.model.{AddPointModelParams, AddPointModelResult}

/**
  * Created by Dragos on 7/13/2016.
  */
class AddPointAction extends ActionControl[AddPointModelParams, AddPointModelResult]{
  override protected def executeAsyncControled(params: AddPointModelParams): AddPointModelResult = AddPointModelResult(params.x, params.y, params.color, 3)

}
