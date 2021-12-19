package com.example.snake_and_ladder;

import javafx.scene.control.Button;

public class dice {
    private Button bt;

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

    int number_generator(){
        dice_value =  (int) (Math.random()*6)+1;
        return (dice_value);
    }
}
