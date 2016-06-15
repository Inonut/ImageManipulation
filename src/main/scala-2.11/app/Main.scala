package app

import app.gui.view.impl.AppView
import app.util.Constants
import app.util.css.CSS

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

/**
  * Created by Dragos on 6/14/2016.
  */
object Main extends JFXApp {


  stage = new PrimaryStage {
    scene = new Scene {
      stylesheets add CSS.getClass.getResource(Constants.applicationCSS).toExternalForm
      root = AppView()
    }
  }
}
