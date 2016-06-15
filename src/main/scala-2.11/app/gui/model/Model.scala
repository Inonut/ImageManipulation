package app.gui.model

import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.Pane

/**
  * Created by Dragos on 6/14/2016.
  */
sealed trait Model{
  val command: String
}

case class AppModel(command: String, canvasImage: Image, canvasWidth: Double, canvasHeight: Double) extends Model
case class AppNodesModel(command: String, canvas: Canvas, pane: Pane) extends Model


object AppNodesModel {
  def apply(command: String): AppNodesModel = new AppNodesModel(command, null, null)
  def apply(command: String, canvas: Canvas): AppNodesModel = new AppNodesModel(command, canvas, null)
  def apply(command: String, pane: Pane): AppNodesModel = new AppNodesModel(command, null, pane)
}


object AppModel {
  def apply(command: String): AppModel = new AppModel(command, null, 0, 0)
  def apply(command: String, canvasWidth: Double, canvasHeight: Double): AppModel = new AppModel(command, null, canvasWidth, canvasHeight)
  def apply(command: String, canvasImage: Image): AppModel = new AppModel(command, canvasImage,0,0)
}