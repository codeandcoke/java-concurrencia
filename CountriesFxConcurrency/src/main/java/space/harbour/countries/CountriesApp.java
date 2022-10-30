package space.harbour.countries;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import space.harbour.countries.controller.CountriesController;
import space.harbour.countries.domain.Country;
import space.harbour.countries.service.CountriesService;
import space.harbour.countries.util.Resources;

import java.util.List;

/**
 * Demo application
 * @author Santiago Faci
 * @version 2021
 */
public class CountriesApp extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("Countries.fxml"));
        loader.setController(new CountriesController());
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
