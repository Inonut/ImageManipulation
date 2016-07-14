package app2.util

import java.util.function.{Consumer, Predicate}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{Event, EventHandler}
import javafx.scene.Node

/**
  * Created by Dragos on 14.07.2016.
  */
object Include {

  implicit def actionEvent[T <: Event](fct: T => Any): EventHandler[T] = new EventHandler[T] {
    override def handle(event: T): Unit = fct(event)
  }

  implicit def predicate[T >: Node](fct: T => Boolean): Predicate[T] = new Predicate[T] {
    override def test(t: T): Boolean = fct(t)
  }

  implicit def toConsumer[T](f: T => Any): Consumer[T] = new Consumer[T] {
    override def accept(t: T): Unit = f(t)
  }

  implicit def toChange[T](f: (ObservableValue[_ <: T], T, T) => Any): ChangeListener[T] = new ChangeListener[T] {
    override def changed(observable: ObservableValue[_ <: T], oldValue: T, newValue: T): Unit = f(observable: ObservableValue[_ <: T], oldValue: T, newValue: T)
  }

  implicit def toChange0[T](f: => Any): ChangeListener[T] = new ChangeListener[T] {
    override def changed(observable: ObservableValue[_ <: T], oldValue: T, newValue: T): Unit = f
  }

}
