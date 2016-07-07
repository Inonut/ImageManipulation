package app2.action

import java.io.File
import java.util.Optional
import javafx.scene.image.Image
import javafx.stage.{FileChooser, Window}

import app2.gui.model.{InportImageModelParams, InportImageModelResult, Model}
import app2.util.{Message, Util}

/**
  * Created by Dragos on 7/5/2016.
  */
class InportImageAction extends Action[InportImageModelParams, InportImageModelResult]{

  val fileChooser = new FileChooser

  override protected def execute(model: InportImageModelParams): InportImageModelResult = {
    val image = Util.getImageFromFile(Util.runOnFxThread{fileChooser.showOpenDialog(model.window)})

    if(image == null){
      return null
    }

    InportImageModelResult(image)
  }
}
