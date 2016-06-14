package app.gui.controller.impl

import app.gui.controller.Controller
import app.gui.model.{AppModel, Model}

/**
  * Created by Dragos on 6/14/2016.
  */
class AppController extends Controller{

  override def continueExecute(data: Model, command: String) = {
    case _ =>
      println("-----")
      throw new Exception("Nu stiu!")
  }
}
