package com.svalero.contador.controller;

import com.svalero.contador.task.TimerTask;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {

    public TextField tfValue;
    public ProgressBar progressBar;
    public Label statusLabel;

    private TimerTask timerTask;

    public AppController() {

    }

    @FXML
    public void startCounter(Event event) {
        int count = Integer.parseInt(tfValue.getText());

        timerTask = new TimerTask(count);
        timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("La tarea ha terminado con éxito");
                alert.show();
            } else if (newState == Worker.State.CANCELLED) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("La tarea ha sido cancelada por el usuario");
                alert.show();
            }
        });

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(timerTask.progressProperty());

        timerTask.valueProperty().addListener((observableValue, oldValue, newValue) ->
            statusLabel.setText(newValue.toString()));

        new Thread(timerTask).start();
    }

    @FXML
    public void stopCounter(Event event) {
        if (timerTask != null)
            timerTask.cancel();
    }
}
