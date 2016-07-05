package app2.util

import javafx.event.{Event, EventHandler}

import com.sun.javafx.application.PlatformImpl

/**
  * Created by Dragos on 7/5/2016.
  */
object Util {

  val defaultPartialFunction: PartialFunction[Any, Any] = {
    case params => params
  }

  implicit def actionEvent[T <: Event](fct: T => Unit): EventHandler[T] = new EventHandler[T] {
    override def handle(event: T): Unit = fct(event)
  }

  def runOnFxThread[T](fct: => T): T = {
    var respons: T = null.asInstanceOf[T]
    PlatformImpl.runAndWait(new Runnable {
      override def run(): Unit = respons = fct
    })
    respons
  }
}