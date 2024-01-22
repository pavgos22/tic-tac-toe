package com.game.toe;

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
    @Override
    public void setToken(char token) {
        this.token = token;
    }

    @Override
    public void setName(int difficulty) {
        this.name = name;
    }

    public Human(String name, char token) {
        this.name = name;
        this.token = token;
    }
    @Override
    public void move() {
        boolean moveDone = false;
        while (!moveDone) {
            System.out.print("Choose x position for " + this.name + "[" + this.token + "]: ");
            int posX = readValidPosition();
            System.out.print("Choose y position for " + this.name + "[" + this.token + "]: ");
            int posY = readValidPosition();

            if (Board.checkField(posX, posY)) {
                Board.setField(posX, posY, token);
                moveDone = true;
            } else {
                System.out.println("This field is taken, please choose another");
            }
        }
    }

    private int readValidPosition() {
        while (true) {
            if (Game.input.hasNextInt()) {
                int position = Game.input.nextInt();
                if (position >= 0 && position <= 2)
                    return position;
                else
                    System.out.println("Invalid input. Please enter 0, 1, or 2.");

            } else {
                System.out.println("Invalid input. Please enter a number.");
                Game.input.next(); // Clear the invalid input
            }
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
}
