package space.harbour.thezoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import space.harbour.thezoo.controller.AnimalsController;
import space.harbour.thezoo.util.Resources;

/**
 * Demo application
 * @author Santiago Faci
 * @version 2021
 */
public class AnimalsApp extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("Countries.fxml"));
        loader.setController(new AnimalsController());
        BorderPane pane = loader.load();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("CountriesFX");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
