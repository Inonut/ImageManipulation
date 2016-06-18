package app.gui.controller.impl;

import akka.actor.ActorRef;
import app.action.impl.AppAction;
import app.gui.controller.Controller;
import app.gui.model.AppModel;
import app.gui.model.Model;
import app.util.Command;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by Dragos on 18.06.2016.
 */
public class AppController extends Controller{
    public Pane canvasPane;
    public Canvas canvas;
    public GridPane grid;



    @Override
    public ActorRef action() {
        return AppAction.getActor();
    }

    @Override
    public void updateView(Model model) {

        if(model == null || !(model instanceof AppModel)){
            return;
        }

        AppModel data = (AppModel) model;

        if(Command.IMPORT_IMAGE.equals(data.command()) && data.canvasImage() != null){
            canvas.getGraphicsContext2D().drawImage(data.canvasImage(), 0, 0);
        }

        if(Command.CLEAR.equals(data.command())){
            canvas.getGraphicsContext2D().clearRect(0, 0, data.canvasWidth(), data.canvasHeight());
        }
    }

    @Override
    public Model getModel(Command command) {
        return new AppModel(
                command,
                canvas.snapshot(null, null),
                canvas.getWidth(),
                canvas.getHeight(),
                grid.getScene().getWindow()
        );
    }

    public void onImport_Click(ActionEvent actionEvent) { execute(getModel(Command.IMPORT_IMAGE)); }

    public void onClear_Click(ActionEvent actionEvent) { execute(getModel(Command.CLEAR)); }
}
