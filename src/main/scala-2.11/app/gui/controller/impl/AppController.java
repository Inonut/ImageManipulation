package app.gui.controller.impl;

import akka.actor.ActorRef;
import app.action.impl.AppAction;
import app.gui.controller.Controller;
import app.gui.model.AppModel;
import app.gui.model.Model;
import app.util.Constants;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * Created by Dragos on 18.06.2016.
 */
public class AppController extends Controller{
    public Pane canvasPane;
    public Canvas canvas;

    public void onImport_Click(ActionEvent actionEvent) {
        execute(new AppModel(Constants.commandTest()));
    }

    @Override
    public ActorRef controller() {
        return AppAction.getActor();
    }

    @Override
    public void updateView(Model model) {
        System.out.println(model);
    }

    @Override
    public Model getModel() {
        return new AppModel(Constants.commandNone());
    }
}
