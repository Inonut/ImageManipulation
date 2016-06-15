package app.gui.model

import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.Pane

/**
  * Created by Dragos on 6/14/2016.
  */
sealed trait Model

case class AppModel(canvasImage: Image, canvasWidth: Double, canvasHeight: Double) extends Model
case class AppNodesModel(canvas: Canvas, pane: Pane) extends Model


object AppNodesModel {
  def apply(): AppNodesModel = new AppNodesModel(null, null)
  def apply(canvas: Canvas): AppNodesModel = new AppNodesModel(canvas, null)
  def apply(pane: Pane): AppNodesModel = new AppNodesModel(null, pane)
}


object AppModel {
  def apply(): AppModel = new AppModel(null, 0, 0)
  def apply(canvasImage: Image): AppModel = new AppModel(canvasImage,0,0)
}