package com.example.snake_and_ladder;

import javafx.scene.control.Button;

public class dice {
    private Button bt;
    private boolean isThrown = false;
    private int dice_value;

    public dice() {
        dice_value = 0;
    }
    dice(Button bt){
        dice_value = 0;
        this.bt = bt;
    }

    public Button getBt() {
        return bt;
    }

    public void setBt(Button bt) {
        this.bt = bt;
    }

    public int getDice_value() {
        return dice_value;
    }

    int number_generator(){
        dice_value =  (int) (Math.random()*6)+1;
        return (dice_value);
    }

    public void setThrown(boolean thrown) {
        isThrown = thrown;
    }
    public boolean getThrown() {
        return  isThrown;
    }
}
