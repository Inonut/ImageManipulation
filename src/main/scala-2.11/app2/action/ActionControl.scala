package app2.action

import app2.util.FutureControl

/**
  * Created by Dragos on 13.07.2016.
  */
trait ActionControl[T, R] extends ActionAsync[T, R] with FutureControl[T]{

  def contiuneExecution(param: T): R

  override protected def execute(params: T): R = {

    //restartEvent addListener (_ => )

    contiuneExecution(params)
  }
}
