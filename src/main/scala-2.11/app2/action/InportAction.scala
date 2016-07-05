package app2.action

import app2.gui.model.{AppModel, Model}

/**
  * Created by Dragos on 7/5/2016.
  */
class InportAction extends Action{

  override val execute: PartialFunction[Any, Any] = {
    case model =>
      Thread.sleep(5000)
      AppModel()
  }
}
