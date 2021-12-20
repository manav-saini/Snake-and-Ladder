package com.example.snake_and_ladder;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class board {
    private Tile[] tiles;
    int number_of_rows = 10;
    int number_of_columns = 10;
    int total_Tiles = number_of_columns * number_of_rows;
    dice d ;
    ArrayList<Tile> t;
    HashMap<Integer,Integer> snake_pos;
    HashMap<Integer,Integer> ladder_pos;
    player user1,user2;
    player curr_player;

    public board(){
        t = new ArrayList<>();
        snake_pos = new HashMap<>();
        ladder_pos = new HashMap<>();
        snake_pos_allocator();
        ladder_pos_allocator();
    }

    void createBoard(Bounds b) {
        double wid,hei;
        wid = (b.getMaxX()-b.getMinX())/number_of_rows;
        hei = (b.getMaxY()-b.getMinY())/number_of_columns;
        tiles = new Tile[number_of_columns*number_of_rows];
        for (int i = 0; i < number_of_columns*number_of_rows; i++) {
            tiles[i] = new Tile("NONE");
            tiles[i].setWidth(wid);
            tiles[i].setHeight(hei);
            t.add(tiles[i]);
        }
        Tiles_allocation(b);

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

    public void setD(dice d) {
        this.d = d;
    }

    public dice getD() {
        return d;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public Tile getTiles(int i) {
        try{
            return tiles[i];
        }
        catch (Exception e){
            System.out.println("Tile doesn't exist");
            return null;
        }
    }

    void Tiles_allocation(Bounds b){
        double orgx, orgy, wid, hei , value;
        orgx = b.getMinX();
        orgy = b.getMaxY();
        wid = (b.getMaxX()-b.getMinX())/number_of_columns;
        hei = (b.getMaxY()-b.getMinY())/number_of_rows;
        double colArr[] = new double[number_of_columns];
        double rowArr[]=new double[number_of_rows];
        for (int i = 0; i < number_of_columns; i++) {
            colArr[i] = orgx  + (i * wid) - wid/2;
        }
        for (int i = 0; i < number_of_rows; i++) {
            rowArr[i]= (orgy - (i*hei) - hei/2);
        }
        for (int i = 0; i < number_of_rows; i++) {
            for (int j = 0; j < number_of_columns; j++) {
                int k = i*number_of_columns + j;
                if (ladder_pos.containsKey(k)) {
                    tiles[k].setType("LADDER");
                } else if (snake_pos.containsKey(k)) {
                    tiles[k].setType("SNAKE");
                }
                tiles[k].setLayoutY(rowArr[i]);
                tiles[k].setY(rowArr[i]);
                if(i%2 == 0){
                    tiles[k].setLayoutX(colArr[j]);
                    tiles[k].setX(colArr[j]);
                }
                else{
                    tiles[k].setLayoutX(colArr[9-j]);
                    tiles[k].setX(colArr[9-j]);
                }
                tiles[k].setFill(Color.BLACK);
                System.out.println(k + ": "+tiles[k].getX()+" "+tiles[k].getY());
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

    public ArrayList<Tile> getT() {
        return t;
    }
}
