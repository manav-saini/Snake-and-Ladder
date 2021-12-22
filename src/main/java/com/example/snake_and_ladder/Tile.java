package com.example.snake_and_ladder;

import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tile extends Rectangle {
    String type;
    public Tile(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
