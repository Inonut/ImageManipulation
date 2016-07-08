package app2.event

import javafx.scene.paint.Color

import scala.collection.mutable.ListBuffer

/**
  * Created by Dragos on 7/8/2016.
  */
class Event[T] {

  protected var listeners = ListBuffer[T => Any]()

  def addListener(f: T => Any) = listeners += f

  def removeListener(f: T => Any) = listeners -= f

  def fire(params: T): Unit = listeners.foreach(_(params))

}
