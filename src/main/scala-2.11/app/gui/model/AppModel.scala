package app.gui.model


import javafx.scene.image.Image
import javafx.stage.Window

import app.util.Command

/**
  * Created by Dragos on 18.06.2016.
  */
class AppModel(val command: Command) extends Model{

  var canvasImage: Image = null
  var canvasWidth: Double = 0
  var canvasHeight: Double = 0
  var window: Window = null


  def this(command: Command, canvasImage: Image, canvasWidth: Double, canvasHeight: Double, window: Window) = {
    this(command)
    this.canvasHeight = canvasHeight
    this.canvasImage = canvasImage
    this.canvasWidth = canvasWidth
    this.window = window
  }

  def this(command: Command, canvasImage: Image) = {
    this(command)
    this.canvasImage = canvasImage
  }

  def this(command: Command, canvasWidth: Double, canvasHeight: Double) = {
    this(command)
    this.canvasHeight = canvasHeight
    this.canvasWidth = canvasWidth
  }

}