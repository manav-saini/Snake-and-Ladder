package com.example.snake_and_ladder;

import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tile extends Rectangle {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
enum Type{
    NONE,
    LADDER,
    SNAKE;
}