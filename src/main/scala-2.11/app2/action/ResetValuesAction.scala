package app2.action

import app2.gui.model.AppModel
import app2.util.Message

/**
  * Created by Dragos on 7/6/2016.
  */
class ResetValuesAction extends Action[Any, Complete[AppModel]] {

  override protected def execute(params: Any): Complete[AppModel] = Complete(Message.ResetValuesAction, AppModel(
    percentRed = 100,
    percentBlue = 100,
    percentGreen = 100,
    opacity = 100,
    contrast = 0,
    brightness = 0,
    hue = 0,
    saturation = 0)
  )
}
