package co.edu.univalle.poe.mvc;

import co.edu.univalle.poe.mvc.controller.CrapsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class CrapsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CrapsApplication.class.getResource("view/craps-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Juego Craps");
        stage.setScene(scene);
        CrapsController controller = fxmlLoader.getController();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.crearVentanaEmergente(event);
            }
        });
        stage.show();
    }
}
