package app2.action.algorithm

import app2.action.ActionControl
import app2.action.model.{KmeansModelParams, KmeansModelResult}

/**
  * Created by Dragos on 7/7/2016.
  */
class Kmeans extends ActionControl[KmeansModelParams, KmeansModelResult]{

  //override def prosses(params: KmeansModelParams): KmeansModelResult = ???
  override protected def execute(params: KmeansModelParams): KmeansModelResult = ???
}
