/**
  * Created by Dragos on 19.06.2016.
  */
object Test {

  def main(args: Array[String]) {
    var list = Array[Double](1,2,3,4,5,6,7,8,9,10)

    list = for(e <- list) yield e * 0.5

    list.foreach(println(_))
  }
}
