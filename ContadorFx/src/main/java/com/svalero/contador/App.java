package com.svalero.contador;

import com.svalero.contador.controller.AppController;
import com.svalero.contador.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppController controller = new AppController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("contador.fxml"));
        loader.setController(controller);
        VBox vBox = loader.load();

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
