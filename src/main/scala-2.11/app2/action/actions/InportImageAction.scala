package app2.action.actions

import javafx.stage.FileChooser

import app2.action.ActionControl
import app2.action.model.{InportImageModelParams, InportImageModelResult}
import app2.util.Util

/**
  * Created by Dragos on 7/5/2016.
  */
class InportImageAction extends ActionControl[InportImageModelParams, InportImageModelResult]{

  val fileChooser = new FileChooser

  override protected def executeAsyncControled(model: InportImageModelParams): InportImageModelResult = {
    val image = Util.getImageFromFile(Util.runOnFxThread{fileChooser.showOpenDialog(model.window)})

    if(image == null){
      return null
    }

    InportImageModelResult(image)
  }
}
