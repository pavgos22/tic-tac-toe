package com.game.toe;

import com.game.util.InputScanner;

import java.util.Scanner;

public class Human implements Player {
    private String name;
    private char token;

    private int score = 0;
    @Override
    public int getScore() {
        return score;
    }
    @Override
    public void setScore(int score) {
        this.score = score;
    }

    private Scanner input = InputScanner.getScanner();
    public Human(String name, char token) {
        this.name = name;
        this.token = token;
    }
    @Override
    public void move() {
        int posX = input.nextInt();
        int posY = input.nextInt();
        if (Board.checkField(posX, posY)) {
            Board.setField(posX, posY, token);
        }
        else {
            System.out.println("This field is taken, please choose another");
            move();
        }
    }
    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }
}
