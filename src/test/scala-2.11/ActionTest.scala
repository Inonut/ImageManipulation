import app2.action.ActionControl

import scala.concurrent._
import scala.concurrent.duration._

import scala.util.Try
import scala.concurrent.Future

import ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/15/2016.
  */
object ActionTest {

  def launch(f: () => Unit, timeout: Int): Future[Try[Unit]] = {

    val aref = new java.util.concurrent.atomic.AtomicReference[Thread]()
    val cdl = new java.util.concurrent.CountDownLatch(1)

    Future {Thread.sleep(timeout); cdl.await(); aref.get().interrupt()}   // 1
    Future {aref.set(Thread.currentThread); cdl.countDown(); Try(f())}  // 2
  }


  def main(args: Array[String]) {

    launch(() => {
      for(i <- 1 until 1000) {
        Thread.sleep(10)
        println(i)
      }
    }, 1000) recover {
      case e => println(e)
    }

    Thread.sleep(100000)
  }

}
