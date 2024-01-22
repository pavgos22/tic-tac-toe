package com.game.toe;

public interface Player {
    String name = null;
    char token = 0;
    int score = 0;

    int getScore();
    void setScore(int score);
    void setToken(char token);
    void move();
    char getToken();
    String getName();


}
