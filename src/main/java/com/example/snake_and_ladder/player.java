package com.example.snake_and_ladder;

import javafx.scene.shape.Circle;

public class player {
    private Circle token;
    private String name;
    private int no_of_wins;
    private boolean turn, start;
    private piece P;

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
}
