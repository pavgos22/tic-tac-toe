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
            this.name = "Computer (min-max)";
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
        if(game.getDifficulty() == 1)
            randomMove();
        else
            bestMove();
    }

    public void randomMove() {
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

    private void bestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestX = -1;
        int bestY = -1;
        for(int i=0; i<game.getBoard().getSize(); i++) {
            for(int j=0; j<game.getBoard().getSize(); j++) {
                if(game.getBoard().getField(i, j) == ' ') {
                    game.getBoard().setField(i, j, token);
                    int score = minimax(0, true);
                    game.getBoard().setField(i, j, ' ');
                    if(score > bestScore) {
                        bestScore = score;
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }
        game.getBoard().setField(bestX, bestY, this.token);
    }

    int minimax(int depth, boolean isMaximizing) {
        char opponentToken = (this.token == 'X') ? 'O' : 'X';

        if (game.getBoard().checkWin(this.token)) return 1;
        if (game.getBoard().checkWin(opponentToken)) return -1;
        if (game.getBoard().checkTie()) return 0;

        if(isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for(int i=0; i<game.getBoard().getSize(); i++) {
                for(int j=0; j<game.getBoard().getSize(); j++) {
                    if(game.getBoard().getField(i, j) == ' ') {
                        game.getBoard().setField(i, j, this.token);
                        int score = minimax(depth + 1, false);
                        game.getBoard().setField(i, j, ' ');
                            if(score > bestScore)
                                bestScore = score;
                    }
                }
            }
            return bestScore;
        }

        else {
            int bestScore = Integer.MAX_VALUE;
            for(int i=0; i<game.getBoard().getSize(); i++) {
                for(int j=0; j<game.getBoard().getSize(); j++) {
                    if(game.getBoard().getField(i, j) == ' ') {
                        game.getBoard().setField(i, j, game.getP1().getToken());
                        int score = minimax(depth + 1, true);
                        game.getBoard().setField(i, j, ' ');
                        if(score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
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