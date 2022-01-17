package com.example.snake_and_ladder;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public interface Methods {
    <T extends String,K extends Button> void setimg(T s, K b);
    void exit(ActionEvent event);
    void start(ActionEvent event);
    void changescene(ActionEvent event,String s) throws IOException;
}
