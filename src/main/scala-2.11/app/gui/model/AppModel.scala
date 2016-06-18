package app.gui.model

/**
  * Created by Dragos on 18.06.2016.
  */
class AppModel() extends Model{

  var command: String = null

  def this(command: String) = {
    this()
    this.command = command
  }
}