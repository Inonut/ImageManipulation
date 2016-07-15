import app2.action.ActionControl

import scala.concurrent._
import scala.concurrent.duration._

/**
  * Created by Dragos on 7/15/2016.
  */
object ActionTest {

  def main(args: Array[String]) {

    val action = ActionControl[Any, Any] {
      for(i <- 1 until 1000) {
        Thread.sleep(10)
        println(i)
      }
    }

    action.execute()

    Thread.sleep(1000)

    try {
      action.distroyEvent.fire()
    } catch {
      case e: Throwable => println(e)
    }

    Thread.sleep(100000)
  }

}
