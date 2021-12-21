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

import static java.lang.Thread.sleep;

public class HelloController {
    @FXML
    public ImageView diceGif;
    @FXML
    public ImageView dice1;
    @FXML
    public ImageView dice2;
    @FXML
    public ImageView dice3;
    @FXML
    public ImageView dice4;
    @FXML
    public ImageView dice5;
    @FXML
    public ImageView dice6;
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
        Board.createBoard(img.localToScene(img.getBoundsInLocal()));
        setdiceVisibility(false);
        startTile = new Tile("NONE");
        startTile.setWidth(Board.getTiles(0).getWidth());
        startTile.setHeight(Board.getTiles(0).getHeight());
        startTile.setX(Board.getTiles(0).getLayoutX());
        startTile.setY(Board.getTiles(0).getLayoutY()+startTile.getHeight());
        startTile.setLayoutY(Board.getTiles(0).getLayoutY()+startTile.getHeight());
        startTile.setLayoutX(Board.getTiles(0).getLayoutX());
    }

    private void setdiceVisibility(boolean set) {
        diceGif.setVisible(set);
        dice1.setVisible(set);
        dice2.setVisible(set);
        dice3.setVisible(set);
        dice6.setVisible(set);
        dice4.setVisible(set);
        dice5.setVisible(set);
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
        Thread t1 = new Thread(){
            @Override
            public void run() {
                diceGif.setVisible(true);
                int val = Dice.number_generator();
                dice_button.setDisable(true);
                Dice.setThrown(true);
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showface();
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        label_dice.setText(""+Dice.getDice_value());
    }

    private void showface() {
        setdiceVisibility(false);
        switch (Dice.getDice_value()) {
            case 1 -> dice1.setVisible(true);
            case 2 -> dice2.setVisible(true);
            case 3 -> dice3.setVisible(true);
            case 4 -> dice4.setVisible(true);
            case 5 -> dice5.setVisible(true);
            case 6 -> dice6.setVisible(true);
        }
    }

    public void onCheckBox1(ActionEvent actionEvent) {
        chb2.setSelected(false);
    }

    public void onCheckBox2(ActionEvent actionEvent) {
        chb1.setSelected(false);
    }

    public void onStartClicked(ActionEvent actionEvent) {
        if(chb1.isSelected() || chb2.isSelected()){
            Threadclass t1 = new Threadclass();
            t1.start();
        }
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

    public void player(TouchEvent touchEvent) {
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
        double x,y;
        int currT;
        Runableclass(player p,Tile t, int i){
            this.p = p;
            this.t = t;
            this.currT = i;
        }
        Runableclass(player p,double x,double y, int i){
            this.p = p;
            this.x = x;
            this.y = y;
            this.currT = i;
        }
        @Override
        public void run() {
            //p.run(x,y,currT);
            p.run(t, currT);
        }
    }

    private void startgame() {
        Thread t2 = new Thread(){
            @Override
            public void run() {
                setTokensproperties();
                disableTokens(true);
                rungame();
            }
        };
        t2.start();
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
        //player1.run(startTile,-1);
        //player2.run(startTile,-1);
        player1.run(startTile.getX() - startTile.getWidth()/4,startTile.getY(),-1);
        player2.run(startTile.getX() + startTile.getWidth()/4,startTile.getY(),-1);
        Thread t = new Thread(){
            @Override
            public void run() {
                while(!gameover) {
                    dice_button.setDisable(false);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Dice.getThrown()){
                        Dice.setThrown(false);
                        player1.setTurn(!player1.isTurn());
                        player2.setTurn(!player2.isTurn());
                        player p = player1.isTurn()?player1:player2;
                        System.out.println(p.getName()+":"+p.getToken().getLayoutX()+" "+p.getToken().getLayoutY());
                        if(!p.isStart()) {
                            if(Dice.getDice_value()!=1) {
                                continue;
                            }
                            p.setStart(true);
                        }
                        if((p.getCurrTile() + Dice.getDice_value() )<=99){
                            for (int i = 0; i < Dice.getDice_value(); i++) {
                                Platform.runLater(new Runableclass(p, Board.getTiles(p.getCurrTile()+ 1), p.getCurrTile()+ 1));
                                //Platform.runLater(new Runableclass(p,Board.getColPos(p.getCurrTile()+1), Board.getRowPos((p.getCurrTile()+1)), p.getCurrTile()+ 1));
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        int tmp = p .getCurrTile() + 1;
                        if(Board.getLadder_pos().containsKey(tmp)){
                            tmp = Board.getLadder_pos().get(tmp) - 1;
                            Platform.runLater(new Runableclass(p, Board.getTiles(tmp), tmp));
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(Board.getSnake_pos().containsKey(tmp)){
                            tmp = Board.getSnake_pos().get(tmp) - 1;
                            Platform.runLater(new Runableclass(p, Board.getTiles(tmp), tmp));
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(p.getToken().getLayoutX() +" "+p.getToken().getLayoutY());
                        if(p.getCurrTile()>=99){
                            gameover=true;
                        }
                        System.out.println(p.getName()+" "+p.getCurrTile());
                        Dice.setThrown(false);
                    }
                    setdiceVisibility(false);
                    dice_button.setDisable(true);
                }
            }
        };
        t.start();
        //endgame();
        //resetGame();
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
    }

    private void resetGame() {
        player1.reset();
        player2.reset();
        Dice.setThrown(false);
        gameover=false;
    }
}