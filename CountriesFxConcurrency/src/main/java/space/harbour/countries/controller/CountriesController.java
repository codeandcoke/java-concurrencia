package space.harbour.countries.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import space.harbour.countries.domain.Country;
import space.harbour.countries.task.DataTask;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CountriesController implements Initializable {

    public TextField countrySearchTextField;
    public Button searchButton;
    public TableView<Country> dataTable;
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

        DataTask dataTask = new DataTask();
        dataTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
            statusLabel.setText("Data loaded!");
            progressBar.setVisible(false);
            List<Country> countryList = dataTask.getValue();
            dataTable.setItems(FXCollections.observableArrayList(countryList));
        });
        new Thread(dataTask).start();
    }

    private void prepareTableView() {
        TableColumn<Country, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Country, String> regionColumn = new TableColumn<>("Region");
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        TableColumn<Country, String> subregionColumn = new TableColumn<>("Subregion");
        subregionColumn.setCellValueFactory(new PropertyValueFactory<>("subregion"));
        TableColumn<Country, Integer> populationColumn = new TableColumn<>("Population");
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        // TODO Add as many columns as you need

        dataTable.getColumns().add(nameColumn);
        dataTable.getColumns().add(regionColumn);
        dataTable.getColumns().add(subregionColumn);
        dataTable.getColumns().add(populationColumn);

        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void refreshData(ActionEvent event) {
        loadData();
    }

    @FXML
    public void removeCountries(ActionEvent event) {
        ObservableList<Country> selectedItems = dataTable.getSelectionModel().getSelectedItems();
        dataTable.getItems().removeAll(selectedItems);
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
    public void searchCountry(ActionEvent event) {
        String searchText = countrySearchTextField.getText();
        statusLabel.setText("Looking for " + searchText);
        progressBar.setVisible(true);

        DataTask dataTask = new DataTask(searchText);

        dataTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
            statusLabel.setText("Search completed");
            progressBar.setVisible(false);
            List<Country> countryList = dataTask.getValue();
            dataTable.setItems(FXCollections.observableArrayList(countryList));
        });
        new Thread(dataTask).start();
    }

    @FXML
    public void tableViewMouseClicked(MouseEvent event) {
        // TODO If the user double click, call API to get the information about the selected country
        if (event.getClickCount() == 2) {
            Country selectedCountry = dataTable.getSelectionModel().getSelectedItem();
            // Only for test purpose
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have selected " + selectedCountry.getName());
            alert.show();
        }
    }
}
