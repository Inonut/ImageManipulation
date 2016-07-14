package app2.action

/**
  * Created by Dragos on 7/5/2016.
  */
trait Action[T, R] {

  protected def execute(params: T): R

}