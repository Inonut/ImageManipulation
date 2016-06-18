package app.util

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

}
