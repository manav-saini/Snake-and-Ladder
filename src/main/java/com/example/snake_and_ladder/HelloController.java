package com.example.snake_and_ladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HelloController {
    static dice Dice;
    @FXML
    public Label label_dice;
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
    public Circle tokenBlue;
    public Circle tokenGreen;
    public Circle tokenYellow;
    public Circle tokenRed;
    public Circle tokenPlayer2;
    public Circle tokenPlayer1;
    public Button startbutton;
    @FXML
    private ImageView img;
    @FXML
    private Button dice_button;

    private player player1, player2;

    public void initialize(){
        Dice = new dice(dice_button);
        chb1.setSelected(false);
        chb2.setSelected(false);
        player1 = new player();
        player2 = new player();
    }

    void changecolor(player p,String choice){
        if(choice.compareToIgnoreCase("Blue")==0){
            p.getToken().setFill(Color.BLUE);
        }
        else if(choice.compareToIgnoreCase("Red")==0){
            p.getToken().setFill(Color.RED);
        }
        else if(choice.compareToIgnoreCase("Green")==0){
            p.getToken().setFill(Color.GREEN);
        }
        else if(choice.compareToIgnoreCase("Yellow")==0){
            p.getToken().setFill(Color.YELLOW);
        }
        p.getToken().setFill(Color.BLACK);
    }

    @FXML
    void onDiceclicked(ActionEvent event) {
        int val = Dice.number_generator();
        label_dice.setText(""+val);
    }

    public void player(TouchEvent touchEvent) {
    }

    public void onCheckBox1(ActionEvent actionEvent) {
        chb2.setSelected(false);
    }

    public void onCheckBox2(ActionEvent actionEvent) {
        chb1.setSelected(false);
    }

    public void onStartClicked(ActionEvent actionEvent) {
        Threadclass t1 = new Threadclass();
        t1.start();
    }

    private void setVisibilityofTokens() {
        String name1,name2;
        chb1.setDisable(true);
        chb2.setDisable(true);
        player1name1.setDisable(true);
        player1name2.setDisable(true);
        player2name1.setDisable(true);
        player2name2.setDisable(true);
        if(chb2.isSelected()){
            name1 = player1name2.getAccessibleText();
            name2 = player2name2.getAccessibleText();
            tokenRed.setVisible(false);
            tokenYellow.setVisible(false);
            tokenPlayer1.setFill(tokenBlue.getFill());
            tokenPlayer2.setFill(tokenGreen.getFill());
            chb1.setVisible(false);
            player1name1.setVisible(false);
            player2name1.setVisible(false);
        }
        else{
            name1 = player1name1.getText();
            name2 = player2name1.getText();
            tokenBlue.setVisible(false);
            tokenGreen.setVisible(false);
            tokenPlayer1.setFill(tokenRed.getFill());
            tokenPlayer2.setFill(tokenYellow.getFill());
            chb2.setVisible(false);
            player1name2.setVisible(false);
            player2name2.setVisible(false);
        }
        if(name1==null || name1 == ""){
            name1 = "Player 1";
        }
        if(name2==null || name2 == ""){
            name2 = "Player 2";
        }
        System.out.println("Player 1 : "+name1+" 2: "+name2);
        player1.setName(name1);
        player2.setName(name2);
        player1.setToken(tokenPlayer1);
        player2.setToken(tokenPlayer2);
    }

    class Threadclass extends Thread{
        player p;

        public void setP(player p) {
            this.p = p;
        }

        public player getP() {
            return p;
        }

        @Override
        public void run() {
            startgame();
        }
    }

    private void startgame() {
        setVisibilityofTokens();
    }
}