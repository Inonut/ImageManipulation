package appplication

import appplication.gui.view.impl.AppView
import appplication.util.Constants
import appplication.util.css.CSS

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

import scala.async.Async._
import scala.concurrent.ExecutionContext.Implicits.global

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
