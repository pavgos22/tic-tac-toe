package com.game.toe;

public interface Player {
    int getScore();
    void setScore(int score);
    void setToken(char token);
    void setName(int difficulty);
    void move();
    char getToken();
    String getName();
}
