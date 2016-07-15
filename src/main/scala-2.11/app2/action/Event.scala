package app2.action

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Dragos on 7/15/2016.
  */
class Event[T] {

  protected var listeners: Seq[Action[T, _]] = Nil

  def addListener(l: Action[T, _]) = listeners synchronized { listeners ++= Seq(l) }

  def removeListener(l: Action[T, _]) = listeners synchronized { listeners ++= Seq(l) }

  def fire(params: T): Unit = listeners foreach (_ execute params)

  def clearListeners(): Unit = listeners = Nil

}