package com.game.toe;

public interface Player {
    String name = null;
    char token = 0;
    int score = 0;

    int getScore();
    void setScore(int score);
    void move();
    char getToken();
    String getName();


}
