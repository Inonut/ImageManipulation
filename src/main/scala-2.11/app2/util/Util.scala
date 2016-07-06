package app2.util

import java.io.{File, FileInputStream}
import java.util.function.Consumer
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{Event, EventHandler}
import javafx.scene.image.Image

import app2.action.InportImageAction
import com.sun.javafx.application.PlatformImpl

/**
  * Created by Dragos on 7/5/2016.
  */
object Util {

  val defaultPartialFunction: PartialFunction[Any, Any] = {
    case params => params
  }

  implicit def actionEvent[T <: Event](fct: T => Any): EventHandler[T] = new EventHandler[T] {
    override def handle(event: T): Unit = fct(event)
  }

  implicit def toConsumer[T](f: T => Any): Consumer[T] = new Consumer[T] {
    override def accept(t: T): Unit = f(t)
  }

  implicit def toChange[T](f: (ObservableValue[_ <: T], T, T) => Any): ChangeListener[T] = new ChangeListener[T] {
    override def changed(observable: ObservableValue[_ <: T], oldValue: T, newValue: T): Unit = f(observable: ObservableValue[_ <: T], oldValue: T, newValue: T)
  }

  def runOnFxThread[T](fct: => T): T = {
    var respons: T = null.asInstanceOf[T]
    PlatformImpl.runAndWait(new Runnable {
      override def run(): Unit = respons = fct
    })
    respons
  }

  def getImageFromFile(file: File): Image = {

    if(file == null){
      return null
    }

    val inputStream = new FileInputStream(file)
    try new Image(inputStream)
    finally inputStream.close()
  }
}
