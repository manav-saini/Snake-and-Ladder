package com.example.snake_and_ladder;

import java.util.Objects;

public class tile {
    String type;
    int pos;

    public tile(String type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean special(){
        return Objects.equals(type, "NONE");
    }
}
