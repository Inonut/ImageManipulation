package app.gui.model

import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image

/**
  * Created by Dragos on 6/14/2016.
  */
sealed trait Model

case class AppModel(canvasImage: Image) extends Model
case class AppNodesModel(canvas: Canvas) extends Model
