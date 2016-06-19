package app.util

import java.io.{File, FileInputStream}
import java.util.function.Consumer
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.image.Image

import com.sun.javafx.application.PlatformImpl


/**
  * Created by Dragos on 17.06.2016.
  */
object Util {

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

  implicit def toConsumer[T](f: T => Unit): Consumer[T] = new Consumer[T] {
    override def accept(t: T): Unit = f(t)
  }

  implicit def toChangeListener[T](f: (ObservableValue[_ <: T], T, T) => Unit): ChangeListener[T] = new ChangeListener[T] {
    override def changed(observable: ObservableValue[_ <: T], oldValue: T, newValue: T): Unit = f(observable, oldValue, newValue)
  }
}
