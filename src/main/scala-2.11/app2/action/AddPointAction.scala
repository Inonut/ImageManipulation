package app2.action

import app2.action.model.{AddPointModelParams, AddPointModelResult}

/**
  * Created by Dragos on 7/13/2016.
  */
class AddPointAction extends Action[AddPointModelParams, AddPointModelResult]{
  override protected def execute(params: AddPointModelParams): AddPointModelResult = AddPointModelResult(params.x, params.y, params.color, 3)
}
