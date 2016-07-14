package app2.util

import app2.util.event.Event

/**
  * Created by Dragos on 14.07.2016.
  */
trait FutureControl[T] {

  //val startEvent = new Event[T]
  val playEvent = new Event[Any]
  val stopEvent = new Event[Any]
  val distroyEvent = new Event[Any]
  val restartEvent = new Event[Any]

  def play(): Unit = playEvent.fire()

  def stop(): Unit = stopEvent.fire()

  def restart(): Unit = restartEvent.fire()

  def distroy(): Unit = distroyEvent.fire()
}
