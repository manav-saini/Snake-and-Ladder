package com.example.snake_and_ladder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML
    public Button Exitbutton;
    @FXML
    public ImageView arrow;
    boolean gameover = false;
    static dice Dice;
    @FXML
    public Label label_dice;
    @FXML
    public Circle tokenPlayer2;
    @FXML
    public Circle tokenPlayer1;
    @FXML
    public Button startbutton;
    @FXML
    private ImageView img;
    @FXML
    private Button dice_button;
    private board Board;
    private Tile startTile;
    private static player player1, player2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        setimg("exit.jpg",Exitbutton);
        setimg("Start.jpg",startbutton);
        Board = new board();
        Dice = new dice(dice_button);
        player1 = new player();
        player2 = new player();
        dice_button.setDisable(true);
        Board.createBoard(img.localToScene(img.getBoundsInLocal()));
        Board.setD(Dice);
        Board.setUser1(player1);
        Board.setUser2(player2);
        setdiceVisibility(false);
        startTile = new Tile("NONE");
        startTile.setWidth(Board.getTiles(0).getWidth());
        startTile.setHeight(Board.getTiles(0).getHeight());
        startTile.setX(Board.getTiles(0).getLayoutX());
        startTile.setY(Board.getTiles(0).getLayoutY()+startTile.getHeight());
        startTile.setLayoutY(Board.getTiles(0).getLayoutY()+startTile.getHeight());
        startTile.setLayoutX(Board.getTiles(0).getLayoutX());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RULES");
        alert.setHeaderText("1.Need a starting 1 to commence your path on board\n2.Ladders climb you up\n3.Snakes put you down on it's tail\n4.First player to reach 100th position wins the game\n5. You can't move ahead 100 thus any score leading to above 100 gets discarded");
        alert.setContentText("\nPress\nStart button to continue\n Exit to exit");
        alert.showAndWait();
    }

    public void setimg(String s,Button b){
        Image img = new Image(getClass().getResourceAsStream(s));
        ImageView view = new ImageView(img);
        view.setFitHeight(b.getPrefHeight());
        view.setFitWidth(b.getPrefWidth());
        view.setPreserveRatio(false);
        b.setGraphic(view);
    }

    public void setimg(String s,Button b){
        Image img = new Image(getClass().getResourceAsStream(s));
        ImageView view = new ImageView(img);
        view.setFitHeight(b.getPrefHeight());
        view.setFitWidth(b.getPrefWidth());
        view.setPreserveRatio(false);
        b.setGraphic(view);
    }

    public void onScene1StartClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setdiceVisibility(boolean set) {
        arrow.setVisible(!set);
        arrow.setDisable(set);
        diceGif.setVisible(set);
        dice1.setVisible(set);
        dice2.setVisible(set);
        dice3.setVisible(set);
        dice6.setVisible(set);
        dice4.setVisible(set);
        dice5.setVisible(set);
    }
//    void changecolor(player p,String choice){
//        if(choice.compareToIgnoreCase("Blue")==0){
//            p.getToken().setFill(Color.BLUE);
//        }
//        else if(choice.compareToIgnoreCase("Red")==0){
//            p.getToken().setFill(Color.RED);
//        }
//        else if(choice.compareToIgnoreCase("Green")==0){
//            p.getToken().setFill(Color.GREEN);
//        }
//        else if(choice.compareToIgnoreCase("Yellow")==0){
//            p.getToken().setFill(Color.YELLOW);
//        }
//        p.getToken().setFill(Color.BLACK);
//    }TODO : Remove in final code

    @FXML
    void onDiceclicked(ActionEvent event) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                arrow.setVisible(false);
                arrow.setDisable(true);
                diceGif.setVisible(true);
                int val = Dice.number_generator();
                dice_button.setDisable(true);
                Dice.setThrown(true);
                try{
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showface();
                try{
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label_dice.setText(""+Dice.getDice_value());
                    }
                });//TODO : Remove this in final code
            }
        };
        t1.start();
    }

    private void showface() {
        setdiceVisibility(false);
        switch(Dice.getDice_value()){
            case 1: dice1.setVisible(true);break;
            case 2: dice2.setVisible(true);break;
            case 3: dice3.setVisible(true);break;
            case 4: dice4.setVisible(true);break;
            case 5: dice5.setVisible(true);break;
            case 6: dice6.setVisible(true);break;
        }
    }

//    public void onCheckBox1(ActionEvent actionEvent) {
//        chb2.setSelected(false);
//    }
//
//    public void onCheckBox2(ActionEvent actionEvent) {
//        chb1.setSelected(false);
//    }TODO:Remove this in final code

    public void onStartClicked(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Details d = (Details) stage.getUserData();
        player1.setName(d.getPlayer1());
        player2.setName(d.getPlayer2());
        tokenPlayer1.setFill(d.getC1());
        tokenPlayer2.setFill(d.getC2());
        if(d.getCh()==1){
            tokenPlayer1.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("red.png"))));
            tokenPlayer2.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("yellow.png"))));
        }
        else if(d.getCh()==2){
            tokenPlayer1.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("blue.png"))));
            tokenPlayer2.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("green.png"))));
        }
//        Threadclass t1 = new Threadclass();
//        t1.start();
        startbutton.setDisable(true);
        startbutton.setVisible(false);
        startgame();
    }

    private void setTokensproperties() {
//        String name1,name2;
//        if(chb2.isSelected()){
//            name1 = player1name2.getAccessibleText();
//            name2 = player2name2.getAccessibleText();
//            tokenRed.setVisible(false);
//            tokenYellow.setVisible(false);
//            tokenPlayer1.setFill(tokenBlue.getFill());
//            tokenPlayer2.setFill(tokenGreen.getFill());
//            chb1.setVisible(false);
//            player1name1.setVisible(false);
//            player2name1.setVisible(false);
//        }
//        else{
//            name1 = player1name1.getText();
//            name2 = player2name1.getText();
//            tokenBlue.setVisible(false);
//            tokenGreen.setVisible(false);
//            tokenPlayer1.setFill(tokenRed.getFill());
//            tokenPlayer2.setFill(tokenYellow.getFill());
//            chb2.setVisible(false);
//            player1name2.setVisible(false);
//            player2name2.setVisible(false);
//        }
//        if(name1==null || name1 == ""){
//            name1 = "Player 1";
//        }
//        if(name2==null || name2 == ""){
//            name2 = "Player 2";
//        }
//        System.out.println("Player 1 : "+name1+" 2: "+name2);
//        player1.setName(name1);
//        player2.setName(name2);TODO : Remove this in final code
        player1.setToken(tokenPlayer1);
        player2.setToken(tokenPlayer2);
    }

//    public void onScene1ExitClicked(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }TODO : Remove this in final code

    public void onExitClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    class Threadclass extends Thread{

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
                rungame();
                endgame();
            }
        };
        t2.start();
    }

//    private void disableTokens(boolean set) {
//        chb1.setDisable(set);
//        chb2.setDisable(set);
//        player1name1.setDisable(set);
//        player1name2.setDisable(set);
//        player2name1.setDisable(set);
//        player2name2.setDisable(set);
//        startbutton.setDisable(set);
//    }TODO : Remove in final code

    private void rungame() {
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
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                while(!gameover) {
//                    dice_button.setDisable(false);
//                    try{
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(Dice.getThrown()){
//                        Dice.setThrown(false);
//                        player1.setTurn(!player1.isTurn());
//                        player2.setTurn(!player2.isTurn());
//                        player p = player1.isTurn()?player1:player2;
//                        System.out.println(p.getName()+":"+p.getToken().getLayoutX()+" "+p.getToken().getLayoutY());
//                        if(!p.isStart()) {
//                            if(Dice.getDice_value()!=1) {
//                                continue;
//                            }
//                            p.setStart(true);
//                        }
//                        if((p.getCurrTile() + Dice.getDice_value() )<=99){
//                            for (int i = 0; i < Dice.getDice_value(); i++) {
//                                Platform.runLater(new Runableclass(p, Board.getTiles(p.getCurrTile()+ 1), p.getCurrTile()+ 1));
//                                //Platform.runLater(new Runableclass(p,Board.getColPos(p.getCurrTile()+1), Board.getRowPos((p.getCurrTile()+1)), p.getCurrTile()+ 1));
//                                try {
//                                    sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                        int tmp = p .getCurrTile() + 1;
//                        if(Board.getLadder_pos().containsKey(tmp)){
//                            tmp = Board.getLadder_pos().get(tmp) - 1;
//                            Platform.runLater(new Runableclass(p, Board.getTiles(tmp), tmp));
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        if(Board.getSnake_pos().containsKey(tmp)){
//                            tmp = Board.getSnake_pos().get(tmp) - 1;
//                            Platform.runLater(new Runableclass(p, Board.getTiles(tmp), tmp));
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println(p.getToken().getLayoutX() +" "+p.getToken().getLayoutY());
//                        if(p.getCurrTile()>=99){
//                            gameover=true;
//                            p.setNo_of_wins(p.getNo_of_wins()+1);
//                        }
//                        System.out.println(p.getName()+" "+p.getCurrTile());
//                        Dice.setThrown(false);
//                    }
//                    setdiceVisibility(false);
//                    dice_button.setDisable(true);
//                }
//                endgame();
//            }
//        };
//        t.start();
        while(!gameover) {
            dice_button.setDisable(false);
            try{
                sleep(1000);
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
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Board.getSnake_pos().containsKey(tmp)){
                    tmp = Board.getSnake_pos().get(tmp) - 1;
                    Platform.runLater(new Runableclass(p, Board.getTiles(tmp), tmp));
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(p.getToken().getLayoutX() +" "+p.getToken().getLayoutY());
                if(p.getCurrTile()>=99){
                    gameover=true;
                    p.setNo_of_wins(p.getNo_of_wins()+1);
                }
                System.out.println(p.getName()+" "+p.getCurrTile());
                Dice.setThrown(false);
            }
            setdiceVisibility(false);
            dice_button.setDisable(true);
        }
        endgame();
        //resetGame();
    }

    private void endgame() {
        if(gameover) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("GAME OVER");
                    alert.setHeaderText("WINNER");
                    if (player1.getNo_of_wins() != 0) {
                        alert.setContentText(player1.getName());
                    } else {
                        alert.setContentText(player2.getName());
                    }
                    ImageView view = new ImageView(new Image(getClass().getResourceAsStream("winner.gif")));
                    view.setPreserveRatio(true);
                    view.setFitHeight(500);
                    view.setFitWidth(200);
                    alert.setGraphic(view);
                    alert.showAndWait();
                    System.exit(0);
                }
            });
        }
//        disableTokens(false);
//        player1name1.setVisible(true);
//        player2name1.setVisible(true);
//        player1name2.setVisible(true);
//        player2name2.setVisible(true);
//        tokenGreen.setVisible(true);
//        tokenYellow.setVisible(true);
//        tokenBlue.setVisible(true);
//        tokenRed.setVisible(true);
    }

    private void resetGame() {
        player1.reset();
        player2.reset();
        Dice.setThrown(false);
        gameover=false;
    }
}