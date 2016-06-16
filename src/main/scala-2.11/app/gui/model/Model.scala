package app.gui.model

import app.gui.view.View

import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.Pane
import scalafx.stage.Window

/**
  * Created by Dragos on 6/14/2016.
  */
sealed trait Model {
  val command: String
}

case class AppModel( command: String, canvasImage: Image, canvasWidth: Double, canvasHeight: Double, view: Window) extends Model{
  def this(command: String) = this(command, null, 0, 0, null)
  def this(command: String, canvasWidth: Double, canvasHeight: Double) = this(command, null, canvasWidth, canvasHeight, null)
  def this(command: String, canvasImage: Image) = this(command, canvasImage, 0, 0, null)
  def this(command: String, canvasImage: Image, canvasWidth: Double, canvasHeight: Double ) = this(command, canvasImage, canvasWidth, canvasHeight, null)
}

case class AppNodesModel(command: String, canvas: Canvas, pane: Pane) extends Model{
  def this(command: String) = this(command, null, null)
  def this(command: String, canvas: Canvas) = this(command, canvas, null)
  def this(command: String, pane: Pane) = this(command, null, pane)
}


object AppNodesModel {

  def apply(command: String): AppNodesModel = new AppNodesModel(command)

  def apply(command: String, canvas: Canvas): AppNodesModel = new AppNodesModel(command, canvas)

  def apply(command: String, pane: Pane): AppNodesModel = new AppNodesModel(command, pane)
}


object AppModel {
  def apply(command: String): AppModel = new AppModel(command)

  def apply(command: String, canvasWidth: Double, canvasHeight: Double): AppModel = new AppModel(command, canvasWidth, canvasHeight)

  def apply(command: String, canvasImage: Image): AppModel = new AppModel(command, canvasImage)

  def apply(command: String, canvasImage: Image, canvasWidth: Double, canvasHeight: Double ): AppModel = new AppModel(command, canvasImage, canvasWidth, canvasHeight)
}