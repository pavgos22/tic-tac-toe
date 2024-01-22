package com.game.toe;

public class Computer implements Player {
    public Computer(char token) {
        this.token = token;
    }

    public void setName(int difficulty) {
        if(difficulty == 1)
            this.name = "Computer (random)";
        if(difficulty == 2)
            this.name = "Computer (medium)";
        if(difficulty == 3)
            this.name = "Computer (hard)";
    }

    private String name = "Computer";

    @Override
    public void setToken(char token) {
        this.token = token;
    }

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

    public Computer(String name, char token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public void move() {
        int posX, posY;
        do {
            posX = getRandomPosition();
            posY = getRandomPosition();
        } while (!Board.checkField(posX, posY));

        Board.setField(posX, posY, token);
    }

    private int getRandomPosition() {
        return (int) (Math.random() * 3);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getToken() {
        return token;
    }
}