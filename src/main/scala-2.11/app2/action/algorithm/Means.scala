package app2.action.algorithm

import javafx.beans.property.SimpleBooleanProperty

import app2.action.Action
import app2.event.Event

/**
  * Created by Dragos on 7/7/2016.
  */
trait Means[T, R] extends Action[T, R]{

  protected val isRunning = new SimpleBooleanProperty(true)

  protected val afterIterationEvent = new Event[T]

  protected val resetEvent = new Event[Any]

  def stop(): Unit = isRunning set false

  def play(): Unit = isRunning set true

  def restart(): Unit = resetEvent.fire()

  def setOnAfterIteration(f: T => Any): Unit = afterIterationEvent addListener f

  def prosses(params: T): R

  override def execute(params: T): R = {
    resetEvent addListener(_ => { prosses(params) })

    prosses(params)
  }
}
