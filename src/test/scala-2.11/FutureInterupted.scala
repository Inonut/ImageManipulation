import app2.util.event.Event

import scala.concurrent.{CancellationException, ExecutionContext, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.async.Async._


/**
  * Created by Dragos on 14.07.2016.
  */
object FutureInterupted {

  val cancelEvent = new Event[Any]


  def main(args: Array[String]): Unit = {

    val f = async {

      cancelEvent addListener(_ => throw new Exception("STOP!!!"))

      for(i<-1 to 5100){
        Thread.sleep(10)
        println(i)
      }
    }

    val f2 = async {

      cancelEvent addListener(_ => throw new Exception("STOP!!!"))

      for(i<-1 to 5100){
        Thread.sleep(10)
        println(i)
      }
    }

    Thread.sleep(1000)

    cancelEvent.fire()

    Thread.sleep(50000)


  }
}
