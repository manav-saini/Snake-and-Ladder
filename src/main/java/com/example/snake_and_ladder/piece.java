package com.example.snake_and_ladder;

import javafx.scene.image.ImageView;

public class piece {
    private String color;
    private int position;
    private ImageView piece_img;

    public piece(String color, int position, ImageView piece_img) {
        this.color = color;
        this.position = position;
        this.piece_img = piece_img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageView getPiece_img() {
        return piece_img;
    }

    public void setPiece_img(ImageView piece_img) {
        this.piece_img = piece_img;
    }
}
