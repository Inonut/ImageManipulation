package app2.action

import java.io.File
import java.util.Optional
import javafx.scene.image.Image
import javafx.stage.{FileChooser, Window}

import app2.gui.model.{AppModel, Model}
import app2.util.{Message, Util}

/**
  * Created by Dragos on 7/5/2016.
  */
class InportImageAction extends Action[Window, Complete[Image]]{

  val fileChooser = new FileChooser

  override protected def execute(window: Window): Complete[Image] = Complete(Message.InportImageAction , Util.getImageFromFile(Util.runOnFxThread{fileChooser.showOpenDialog(window)}))
}
