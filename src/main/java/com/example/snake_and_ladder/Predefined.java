package com.example.snake_and_ladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

abstract class Predefined implements Methods{
    @Override
    public <T extends String, K extends Button> void setimg(T s, K b) {
        Image img = new Image(getClass().getResourceAsStream(s));
        ImageView view = new ImageView(img);
        view.setFitHeight(b.getPrefHeight());
        view.setFitWidth(b.getPrefWidth());
        view.setPreserveRatio(false);
        b.setGraphic(view);
    }

    @Override
    public void changescene(ActionEvent event, String path){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
