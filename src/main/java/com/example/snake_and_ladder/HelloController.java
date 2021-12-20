package com.example.snake_and_ladder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HelloController {
    boolean gameover = false;
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
    private board Board;
    private Tile startTile;

    private player player1, player2;

    public void initialize(){
        Board = new board();
        Dice = new dice(dice_button);
        chb1.setSelected(false);
        chb2.setSelected(false);
        player1 = new player();
        player2 = new player();
        dice_button.setDisable(true);
        startTile = new Tile("NONE");
        Board.createBoard(img.localToScene(img.getBoundsInLocal()));
        startTile.setWidth(Board.getTiles(0).getWidth());
        startTile.setHeight(Board.getTiles(0).getHeight());
        startTile.setX(Board.getTiles(0).getX());
        startTile.setY(Board.getTiles(0).getY()+startTile.getHeight());
        startTile.setLayoutY(Board.getTiles(0).getLayoutY());
        startTile.setLayoutX(Board.getTiles(0).getLayoutX()+startTile.getWidth());
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
        dice_button.setDisable(true);
        Dice.setThrown(true);
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

    private void setTokensproperties() {
        String name1,name2;
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

    class Runableclass implements Runnable{
        player p;
        Tile t;
        int currT;
        Runableclass(player p,Tile t, int i){
            this.p = p;
            this.t = t;
            this.currT = i;
        }
        @Override
        public void run() {
            p.run(t, currT);
        }
    }

    private void startgame() {
        setTokensproperties();
        disableTokens(true);
        rungame();
        endgame();
    }

    private void disableTokens(boolean set) {
        chb1.setDisable(set);
        chb2.setDisable(set);
        player1name1.setDisable(set);
        player1name2.setDisable(set);
        player2name1.setDisable(set);
        player2name2.setDisable(set);
        startbutton.setDisable(set);
    }

    private void rungame() {
        player p ;
        if((int)(Math.random()*2) ==0){
            player1.setTurn(true);
        }
        else{
            player2.setTurn(true);
        }
        player1.run(startTile,-1);
        player2.run(startTile,-1);
//        player1.getToken().setLayoutY(Board.getTiles(0).getLayoutY()-Board.getTiles(0).getHeight());
//        player1.getToken().setLayoutX(Board.getTiles(0).getLayoutX());
//        player1.getToken().setTranslateX(Board.getTiles(0).getLayoutX() - player1.getToken().getLayoutX());
//        player2.getToken().setTranslateX(Board.getTiles(0).getLayoutX() - player2.getToken().getLayoutX());
        while(!gameover) {
            dice_button.setDisable(false);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Dice.getThrown()){
                player1.setTurn(!player1.isTurn());
                player2.setTurn(!player2.isTurn());
                p = player1.isTurn()?player1:player2;
                System.out.println(p.getName()+":"+p.getToken().getLayoutX()+" "+p.getToken().getLayoutY());
                if((p.getCurrTile() + Dice.getDice_value() )<=99){
                    for (int i = 0; i < Dice.getDice_value(); i++) {
                        Platform.runLater(new Runableclass(p, Board.getTiles(p.getCurrTile()+ 1), p.getCurrTile()+ 1));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(p.getToken().getLayoutX() +" "+p.getToken().getLayoutY());
                if(p.getCurrTile()>=99){
                    gameover=true;
                }
                System.out.println(p.getName()+" "+p.getCurrTile());
                Dice.setThrown(false);
            }
            dice_button.setDisable(true);
        }
    }

    private void endgame() {
        disableTokens(false);
        player1name1.setVisible(true);
        player2name1.setVisible(true);
        player1name2.setVisible(true);
        player2name2.setVisible(true);
        tokenGreen.setVisible(true);
        tokenYellow.setVisible(true);
        tokenBlue.setVisible(true);
        tokenRed.setVisible(true);
        resetGame();
    }

    private void resetGame() {
        player1.reset();
        player2.reset();
        Dice.setThrown(false);
        gameover=false;
    }
}