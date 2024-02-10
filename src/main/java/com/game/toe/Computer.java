package com.game.toe;

public class Computer implements Player {
    public Computer(char token, Game game) {
        this.token = token;
        this.game = game;
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

    private Game game;

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

    public Computer(String name, char token, Game game) {
        this.name = name;
        this.token = token;
        this.game = game;
    }

    @Override
    public void move() {
        Game.incMoveNumber();
        int posX, posY;
        do {
            posX = getRandomPosition();
            posY = getRandomPosition();
        } while (!game.getBoard().checkField(posX, posY));

        game.getBoard().setField(posX, posY, token);
    }

    private int getRandomPosition() {
        return (int) (Math.random() * game.getBoard().getSize());
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