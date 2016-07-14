package app2.util.event

import javafx.scene.paint.Color

import scala.collection.mutable.ListBuffer

/**
  * Created by Dragos on 7/8/2016.
  */
class Event[T] {

  protected var listeners: Seq[T => Any] = Nil

  def addListener(f: T => Any) = listeners.synchronized{ listeners ++= Seq(f) }

  def removeListener(f: T => Any) = listeners.synchronized{ listeners ++= Seq(f) }

  def fire(params: T): Unit = listeners.foreach(_(params))

}
