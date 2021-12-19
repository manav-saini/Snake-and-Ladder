package com.example.snake_and_ladder;
public class dice {
    public dice() {
    }
    int number_generator(){
        int min =1;
        int max = 6;
        return (int) (Math.random()*(max - min))+min;
    }
}
