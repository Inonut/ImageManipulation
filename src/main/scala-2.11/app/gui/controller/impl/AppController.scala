package app.gui.controller.impl


import java.io.{File, FileInputStream}

import app.gui.controller.Controller
import app.gui.model.{AppModel, Model}
import app.util.Constants

import scalafx.scene.image.Image
import scalafx.stage.FileChooser


/**
  * Created by Dragos on 6/14/2016.
  */
class AppController extends Controller{

  override def continueExecute: PartialFunction[Any, Any] = {
    case (data: AppModel, Constants.addImage) =>

      val file = new FileChooser().showOpenDialog(null)

      AppModel(new Image(new FileInputStream(file)))
  }
}
