package com.example.snake_and_ladder;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class board {
    private Tile[] tiles;
    private int number_of_rows = 10;
    private int number_of_columns = 10;
    private final int total_Tiles = number_of_columns * number_of_rows;
    private dice d ;
    private final HashMap<Integer,Integer> snake_pos;
    private final HashMap<Integer,Integer> ladder_pos;
    private double colPos[];
    private double rowPos[];
    private  player user1,user2;
    private player curr_player;

    public board(){
        snake_pos = new HashMap<>();
        ladder_pos = new HashMap<>();
        snake_pos_allocator();
        ladder_pos_allocator();
    }

    public HashMap<Integer, Integer> getLadder_pos() {
        return ladder_pos;
    }

    public HashMap<Integer, Integer> getSnake_pos() {
        return snake_pos;
    }

    void createBoard(Bounds b) {
        double wid,hei;
        wid = (b.getMaxX()-b.getMinX())/number_of_rows;
        hei = (b.getMaxY()-b.getMinY())/number_of_columns;
        tiles = new Tile[number_of_columns*number_of_rows];
        for (int i = 0; i < number_of_columns*number_of_rows; i++) {
            tiles[i] = new Tile();
            tiles[i].setWidth(wid);
            tiles[i].setHeight(hei);
        }
        Tiles_allocation(b);
    }

    public void setNumber_of_columns(int number_of_columns) {
        this.number_of_columns = number_of_columns;
    }

    public void setNumber_of_rows(int number_of_rows) {
        this.number_of_rows = number_of_rows;
    }

    public int getNumber_of_columns() {
        return number_of_columns;
    }

    public int getNumber_of_rows() {
        return number_of_rows;
    }

    void snake_pos_allocator(){
        snake_pos.put(11,9);
        snake_pos.put(36,14);
        snake_pos.put(43,22);
        snake_pos.put(56,18);
        snake_pos.put(75,54);
        snake_pos.put(81,63);
        snake_pos.put(90,50);
        snake_pos.put(94,53);
        snake_pos.put(96,65);
        snake_pos.put(99,78);
    }

    void ladder_pos_allocator(){
        ladder_pos.put(4,25);
        ladder_pos.put(8,31);
        ladder_pos.put(21,60);
        ladder_pos.put(28,46);
        ladder_pos.put(32,48);
        ladder_pos.put(42,80);
        ladder_pos.put(52,68);
        ladder_pos.put(58,77);
        ladder_pos.put(59,93);
        ladder_pos.put(84,98);
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
            colArr[i] = orgx  + (i * wid) + wid/2;
        }
        for (int i = 0; i < number_of_rows; i++) {
            rowArr[i]= (orgy - (i*hei) - hei/2);
        }
        colPos = colArr;
        rowPos = rowArr;
        for (int i = 0; i < number_of_rows; i++) {
            for (int j = 0; j < number_of_columns; j++) {
                int k = i*number_of_columns + j;
                if (ladder_pos.containsKey(k)) {
                    tiles[k].setType(Type.LADDER);
                } else if (snake_pos.containsKey(k)) {
                    tiles[k].setType(Type.SNAKE);
                }
                else{
                    tiles[k].setType(Type.NONE);
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
//                System.out.println(k + ": "+tiles[k].getX()+" "+tiles[k].getY());
            }
        }
    }

    int dice_val(){
        return d.number_generator();
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

    public double[] getRowPos() {
        return rowPos;
    }

    public double[] getColPos() {
        return colPos;
    }

    public double getRowPos(int i) {
        return rowPos[i/10];
    }

    public double getColPos(int i) {
        if((i/10)%2 ==0){
            return(colPos[i%10]);
        }
        return colPos[(i+9)%10];
    }
}
