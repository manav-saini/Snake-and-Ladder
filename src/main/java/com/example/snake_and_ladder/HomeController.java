package com.example.snake_and_ladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    public Button HomeExit;
    @FXML
    public Button HomeStart;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        Image img = new Image(getClass().getResourceAsStream("exit.jpg"));
        ImageView view = new ImageView(img);
        view.setFitHeight(HomeExit.getPrefHeight());
        view.setFitWidth(HomeExit.getPrefWidth());
        view.setPreserveRatio(false);
        HomeExit.setGraphic(view);
        img = new Image(getClass().getResourceAsStream("exit.jpg"));
        view = new ImageView(img);
        view.setFitHeight(HomeStart.getPrefHeight());
        view.setFitWidth(HomeStart.getPrefWidth());
        view.setPreserveRatio(false);
        HomeStart.setGraphic(view);
    }

    @FXML
    void onhomeStartClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onHomeExitClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Exit");
        alert.setHeaderText("Are you sure you want to Exit?");
        alert.setContentText(null);
        alert.showAndWait().ifPresent(response->{
            if(response== ButtonType.OK){
                System.exit(0);
            }
        });
    }



}
