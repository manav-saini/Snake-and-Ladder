package com.example.snake_and_ladder;

import javafx.scene.shape.Circle;

public class player {
    private Circle token;
    private String name;
    private int no_of_wins;
    private boolean turn, start;
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

    public void setToken(Circle token) {
        this.token = token;
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
        token.setTranslateX(desTile.getX()- t.getX());
        token.setTranslateY(desTile.getY()-t.getY());
        token.setLayoutY(desTile.getLayoutY());
        token.setLayoutX(desTile.getLayoutX());
        t = desTile;
        currTile = i;
    }

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
