package com.game.toe;

public interface Player {
    String name = null;
    Game game = null;
    char token = 0;
    int score = 0;

    int getScore();
    void setScore(int score);
    void setToken(char token);
    void setName(int difficulty);
    void move();
    char getToken();
    String getName();
}
