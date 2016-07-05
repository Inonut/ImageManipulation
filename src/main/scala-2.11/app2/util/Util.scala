package app2.util

import java.io.{File, FileInputStream}
import javafx.event.{Event, EventHandler}
import javafx.scene.image.Image

import app2.action.InportAction
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

  def runOnFxThread[T](fct: => T): T = {
    var respons: T = null.asInstanceOf[T]
    PlatformImpl.runAndWait(new Runnable {
      override def run(): Unit = respons = fct
    })
    respons
  }

  def getImageFromFile(file: File): Image = {

    val inputStream = new FileInputStream(file)
    try new Image(inputStream)
    finally inputStream.close()
  }
}
