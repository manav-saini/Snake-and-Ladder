package com.example.snake_and_ladder;

import java.util.ArrayList;
import java.util.HashMap;

public class board {
    int number_of_rows = 10;
    int number_of_columns = 10;
    int total_tiles = number_of_columns * number_of_rows;
    dice d = new dice();
    ArrayList<tile> t;
    HashMap<Integer,Integer> snake_pos;
    HashMap<Integer,Integer> ladder_pos;
    player user1,user2;
    player curr_player;

    public board(){
        t = new ArrayList<>();
        snake_pos = new HashMap<>();
        ladder_pos = new HashMap<>();
    }

    void snake_pos_allocator(){
        snake_pos.put(24,18);
        snake_pos.put(26,16);
        snake_pos.put(28,14);
        snake_pos.put(55,34);
        snake_pos.put(57,36);
        snake_pos.put(59,38);
        snake_pos.put(91,50);
        snake_pos.put(95,74);
        snake_pos.put(97,76);
        snake_pos.put(99,78);
    }

    void ladder_pos_allocator(){
        ladder_pos.put(5,17);
        ladder_pos.put(7,15);
        ladder_pos.put(9,13);
        ladder_pos.put(33,47);
        ladder_pos.put(35,45);
        ladder_pos.put(37,43);
        ladder_pos.put(40,81);
        ladder_pos.put(68,87);
        ladder_pos.put(66,85);
        ladder_pos.put(64,83);

    }

    void tiles_allocation(){
        for(int i=0;i<total_tiles;i++){
            if(ladder_pos.containsKey(i)){
                t.add(new tile("LADDER",i));
            }
            else if(snake_pos.containsKey(i)){
                t.add(new tile("SNAKE",i));
            }
            else{
                t.add(new tile("NONE",i));
            }
        }
    }

    int dice_val(){
        return d.number_generator();
    }

    int update_pos(int current_pos){
        int new_pos = -2;
        if(ladder_pos.containsKey(current_pos)){
            new_pos = ladder_pos.get(current_pos);
        }
        else if(snake_pos.containsKey(current_pos)){
            new_pos = ladder_pos.get(current_pos);
        }
        else{
            new_pos = current_pos;
        }
        return new_pos;
    }

    player getCurr_player(){
        if(user1.isTurn()){
            curr_player = user1;
        }
        else{
            curr_player = user2;
        }
        return curr_player;
    }

    public player getUser1() {
        return user1;
    }

    public void setUser1(player user1) {
        this.user1 = user1;
    }

    public player getUser2() {
        return user2;
    }

    public void setUser2(player user2) {
        this.user2 = user2;
    }

    public ArrayList<tile> getT() {
        return t;
    }
}
