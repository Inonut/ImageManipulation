package app2.util

import java.io.{File, FileInputStream}
import javafx.scene.image.Image

import com.sun.javafx.application.PlatformImpl

/**
  * Created by Dragos on 7/5/2016.
  */
object Util {

  val defaultPartialFunction: PartialFunction[Any, Any] = {
    case params => params
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
