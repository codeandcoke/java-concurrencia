package com.svalero.multihilo;

import com.svalero.multihilo.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    public TextField tfUrl;
    public Button btDownload;
    public VBox panel;
    private List<DownloadController> downloads;
    private int downloadCount;
    private List<String> urlQueue;

    public AppController() {
        downloads = new ArrayList<>();
        urlQueue = new ArrayList<>();
        downloadCount = 0;
    }

    @FXML
    public void launchDownload(ActionEvent event) {
        String urlText = tfUrl.getText();
        tfUrl.clear();
        tfUrl.requestFocus();

        launchDownload(urlText);
    }

    private void launchDownload(String url) {
        if (downloadCount == 10) {
            urlQueue.add(url);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("download.fxml"));
            DownloadController downloadController = new DownloadController(url);
            loader.setController(downloadController);
            VBox cronometro = loader.load();

            panel.getChildren().add(cronometro);

            downloads.add(downloadController);
            downloadCount++;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stopAllDownloads() {
        for (DownloadController downloadController : downloads) {
            downloadController.stop();
        }

        panel.getChildren().clear();
    }

    @FXML
    public void readDLC() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            List<String> urls = Files.readAllLines(file.toPath());
            urls.forEach(this::launchDownload);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
