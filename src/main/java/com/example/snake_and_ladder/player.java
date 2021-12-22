package com.example.snake_and_ladder;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

import static java.lang.Thread.sleep;

public class player {
    private Circle token;
    private String name;
    private int no_of_wins =0 ;
    private boolean turn, start;
    private double orgx, orgy;
    private piece P;
    private Tile t;
    private int currTile=0;

    player(){
        super();
    }

    public player(String name, boolean turn, boolean start, piece P) {
        this.name = name;
        this.turn = turn;
        this.start = start;
        this.P = P;
    }

    public void reset(){
        this.token.setLayoutX(orgx);
        this.token.setLayoutY(orgy);
        this.token.setTranslateX(0);
        this.token.setTranslateY(0);
    }

    public void setToken(Circle token) {
        this.token = token;
        orgx = token.getLayoutX();
        orgy = token.getLayoutY();
    }

    public void setOrgx(double orgx) {
        this.orgx = orgx;
    }

    public void setOrgy(double orgy) {
        this.orgy = orgy;
    }

    public Circle getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_of_wins() {
        return no_of_wins;
    }

    public void setNo_of_wins(int no_of_wins) {
        this.no_of_wins = no_of_wins;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    void run(Tile desTile, int i){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                token.setTranslateX(desTile.getLayoutX()- token.getLayoutX());
//                token.setTranslateY(desTile.getLayoutY()- token.getLayoutY());
                token.setLayoutY(desTile.getLayoutY());
                token.setLayoutX(desTile.getLayoutX());
                orgx = desTile.getLayoutX();
                orgy = desTile.getLayoutY();
                t = desTile;
                currTile = i;
            }
        });
    }

//    void run(double x, double y,int i){
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                token.setTranslateX(x- orgx);
//                token.setTranslateY(y- orgy);
//                token.setLayoutY(y);
//                token.setLayoutX(x);
//                orgx = x;
//                orgy = y;
////                System.out.println("Layout : "+token.getLayoutX()+" "+token.getLayoutY());
//                //t = desTile;
//                currTile = i;
//            }
//        });
////        token.setTranslateX(x- orgx);
////        token.setTranslateY(y- orgy);
////        token.setLayoutY(y);
////        token.setLayoutX(x);
////        orgx = x;
////        orgy = y;
////        System.out.println("Layout : "+token.getLayoutX()+" "+token.getLayoutY());
////        //t = desTile;
////        currTile = i;
//    }TODO : Remove in final code

    public void setT(Tile t) {
        this.t = t;
    }

    public Tile getT() {
        return t;
    }

    public int getCurrTile() {
        return currTile;
    }

    public void setCurrTile(int currTile) {
        this.currTile = currTile;
    }
}
