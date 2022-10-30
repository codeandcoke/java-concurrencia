package space.harbour.thezoo.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import okhttp3.internal.platform.Platform;
import space.harbour.thezoo.domain.Animal;
import space.harbour.thezoo.task.GetAnimalsTask;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnimalsController implements Initializable {

    public TextField animalIdTextField;
    public Button removeButton;
    public TableView<Animal> dataTable;
    public Label statusLabel;
    public ProgressBar progressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareTableView();
        loadData();
    }

    private void loadData() {
        statusLabel.setText("Loading data . . .");
        progressBar.setVisible(true);

        GetAnimalsTask dataTask = new GetAnimalsTask();
        dataTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
            statusLabel.setText("Data loaded!");
            progressBar.setVisible(false);
            List<Animal> animalList = dataTask.getValue();
            dataTable.setItems(FXCollections.observableArrayList(animalList));
        });
        try {
            new Thread(dataTask).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareTableView() {
        TableColumn<Animal, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Animal, String> raceColumn = new TableColumn<>("Race");
        raceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        TableColumn<Animal, Integer> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        // TODO Add as many columns as you need

        dataTable.getColumns().add(idColumn);
        dataTable.getColumns().add(nameColumn);
        dataTable.getColumns().add(raceColumn);
        dataTable.getColumns().add(weightColumn);

        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void refreshData(ActionEvent event) {
        loadData();
    }

    @FXML
    public void closeApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close application");
        alert.setHeaderText("Are you sure?");
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    @FXML
    public void removeAnimal(ActionEvent event) {
        long animalId = Long.parseLong(animalIdTextField.getText());
        statusLabel.setText("Looking for " + animalId);
        progressBar.setVisible(true);

        GetAnimalsTask getAnimalsTask = new GetAnimalsTask(animalId);
        getAnimalsTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
            statusLabel.setText("Animal removed");
            progressBar.setVisible(false);
        });
        Thread removeAnimal = new Thread(getAnimalsTask);
        removeAnimal.start();
        try {

                removeAnimal.join();

        } (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    public void tableViewMouseClicked(MouseEvent event) {
        // TODO If the user double click, call API to get the information about the selected country
        if (event.getClickCount() == 2) {
            Animal selectedAnimal = dataTable.getSelectionModel().getSelectedItem();
            // Only for test purpose
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have selected " + selectedAnimal.getName());
            alert.show();
        }
    }
}
