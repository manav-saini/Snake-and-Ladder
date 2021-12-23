package com.example.snake_and_ladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Scene1Controller extends Predefined{
    @FXML
    public Button startbutton;
    @FXML
    public CheckBox chb1;
    @FXML
    public CheckBox chb2;
    @FXML
    public TextField player1name1;
    @FXML
    public TextField player2name1;
    @FXML
    public TextField player2name2;
    @FXML
    public TextField player1name2;
    @FXML
    public Circle tokenBlue;
    @FXML
    public Circle tokenGreen;
    @FXML
    public Circle tokenYellow;
    @FXML
    public Circle tokenRed;

    private final Details d = new Details();
    @FXML
    public Button Exitbutton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        chb1.setSelected(false);
        chb2.setSelected(false);
        setimg("exit.jpg",Exitbutton);
        setimg("Okay.png",startbutton);
    }

//    public void setimg(String s,Button b){
//        Image img = new Image(getClass().getResourceAsStream(s));
//        ImageView view = new ImageView(img);
//        view.setFitHeight(b.getPrefHeight());
//        view.setFitWidth(b.getPrefWidth());
//        view.setPreserveRatio(false);
//        b.setGraphic(view);
//    }

    public void onCheckBox1(ActionEvent actionEvent) {
        chb2.setSelected(false);
    }

    public void onCheckBox2(ActionEvent actionEvent) {
        chb1.setSelected(false);
    }

    private void setTokensproperties() {
        String name1,name2;
        if(chb2.isSelected()){
            name1 = player1name2.getText();
            name2 = player2name2.getText();
            tokenRed.setVisible(false);
            tokenYellow.setVisible(false);
            d.setC1(tokenBlue.getFill());
            d.setC2(tokenGreen.getFill());
            d.setCh(2);
            chb1.setVisible(false);
            player1name1.setVisible(false);
            player2name1.setVisible(false);
        }
        else{
            name1 = player1name1.getText();
            name2 = player2name1.getText();
            tokenBlue.setVisible(false);
            tokenGreen.setVisible(false);
            d.setC1(tokenRed.getFill());
            d.setC2(tokenYellow.getFill());
            d.setCh(1);
            chb2.setVisible(false);
            player1name2.setVisible(false);
            player2name2.setVisible(false);
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(name1==null || name1.equals("")){
            name1 = "Player 1";
        }
        if(name2==null || name2.equals("")){
            name2 = "Player 2";
        }
//        System.out.println("Player 1 : "+name1+" 2: "+name2);
        chb1.setDisable(true);
        chb2.setDisable(true);
        player1name1.setDisable(true);
        player1name2.setDisable(true);
        player2name1.setDisable(true);
        player2name2.setDisable(true);
        startbutton.setDisable(true);
        d.setPlayer1(name1);
        d.setPlayer2(name2);
    }

//    public void onScene1StartClicked(ActionEvent event) throws IOException {
//        if(chb1.isSelected() || chb2.isSelected()){
//            setTokensproperties();
//            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            stage.setUserData(d);
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//    }

//    public void onScene1ExitClicked(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void exit(ActionEvent event) {
        changescene(event,"Home.fxml");
    }

    @Override
    public void start(ActionEvent event) {
        if(chb1.isSelected() || chb2.isSelected()){
            setTokensproperties();
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setUserData(d);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Details{
    private String player1,player2;
    private Paint c1,c2;
    private int ch=0;
    Details(){
        player1 = "";
        player2 = "";
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public int getCh() {
        return ch;
    }

    public void setC1(Paint c1) {
        this.c1 = c1;
    }

    public void setC2(Paint c2) {
        this.c2 = c2;
    }

    public Paint getC1() {
        return c1;
    }

    public Paint getC2() {
        return c2;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
