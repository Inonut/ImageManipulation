package app

import javafx.fxml.FXMLLoader
import javafx.scene.Parent

import app.action.impl.AppAction
import app.gui.controller.impl.AppController
import app.gui.view.FXML
import app.gui.view.css.CSS
import app.util.Constants

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

/**
  * Created by Dragos on 17.06.2016.
  */
object Main extends JFXApp {

  val resource = FXML.getClass.getResource(Constants.applicationFXML)

  stage = new PrimaryStage {
    scene = new Scene {
      stylesheets add CSS.getClass.getResource(Constants.applicationCSS).toExternalForm
      root = FXMLLoader.load(resource).asInstanceOf[Parent]
    }
  }

}
