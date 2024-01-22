package com.game.toe;

import java.util.Scanner;

public class Game {
    private Player p1, p2;
    private int gameMode;
    private int difficulty;
    private boolean exit = false;
    private boolean pause = false;
    public static Scanner input = new Scanner(System.in);

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public Game() {

    }

    public static void swapTokens(Player p1, Player p2) {
        char temp = p1.getToken();
        p1.setToken(p2.getToken());
        p2.setToken(temp);
    }

    public void menu() {
        setup();
    }


    public void printGameIntro() {
        System.out.println("Tic-Tac-Toe Game");
        System.out.println("-----------------");
        System.out.println("Select Mode: ");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");
        System.out.println("3. Exit");
    }

    public void gameIntro() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                gameMode = 1;
                break;
            case 2:
                gameMode = 2;
                break;
            case 3:
                exit = true;
                break;
            default:
                System.out.println(choice + " is not an option, please select 1, 2 or 3");
                break;
        }
    }

    void pvpIntro() {
        System.out.print("Enter name of the first player: ");
        String pl1 = input.next();
        char firstToken = chooseToken(pl1);

        System.out.print("Enter name of the second player: ");
        String pl2 = input.next();
        char secondToken = (firstToken == 'X') ? 'O' : 'X';

        p1 = new Human(pl1, firstToken);
        p2 = new Human(pl2, secondToken);

        printPlayersToken();
    }

    void pvrandomIntro() {
        System.out.print("Enter your name: ");
        String pl1 = input.next();
        char firstToken = chooseToken(pl1);
        char secondToken = (firstToken == 'X') ? 'O' : 'X';

        p1 = new Human(pl1, firstToken);
        p2 = new Computer(secondToken);
        p2.setName(difficulty);

        printPlayersToken();
    }

    private char chooseToken(String playerName) {
        char token;
        while (true) {
            System.out.print("Enter " + playerName + "'s token (O/X): ");
            token = input.next().toUpperCase().charAt(0);
            if (token == 'X' || token == 'O') {
                return token;
            } else {
                System.out.println("Invalid token. Please enter 'O' or 'X'.");
            }
        }
    }

    private void printPlayersToken() {
        System.out.println(p1.getName() + ": " + p1.getToken());
        System.out.println(p2.getName() + ": " + p2.getToken());
    }


    void won(Player p) {
        p.setScore(p.getScore()+1);
        System.out.println(p.getName() + " won!");
        pause = true;
    }

    void draw() {
        System.out.println("Draw!");
    }

    void printScore() {
        System.out.println("-----Score Table-----");
        System.out.println(p1.getName() + ": " + p1.getScore());
        System.out.println(p2.getName() + ": " + p2.getScore());
    }

    void newGame() {
        printScore();
        System.out.println();
        System.out.println("Press N to start new game");
        System.out.println("Press X to exit");

        char choice = input.next().charAt(0);
        if(choice == 'n' || choice == 'N') {
            Board.clearBoard();
            swapTokens(p1, p2);
            System.out.println("The tokens have switched");
            pause = false;
        }
        else if(choice == 'x' || choice == 'X')
            exit = true;
    }

    void difficultyChoice() {
        System.out.println("Select difficulty");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        int choice = input.nextInt();
        switch(choice) {
            case 1:
                difficulty = 1;
                pvrandomIntro();
                break;
            default:
                System.out.println(choice + " is not an option, please type 1, 2 or 3");
                difficultyChoice();
                break;
        }
    }

    void setup() {
        printGameIntro();
        gameIntro();
        if(gameMode == 1) {
            difficultyChoice();
        }
        if(gameMode == 2) {
            pvpIntro();
        }
    }

    public void run() {
        if(gameMode == 2) Board.printBoard();
        while (!exit) {
            makeMove(p1);
            if (checkEndGame(p1)) {
                break;
            }

            makeMove(p2);
            if (checkEndGame(p2)) {
                break;
            }
        }
        newGame();
    }


    private void makeMove(Player player) {
        if(gameMode == 2) {
            Board.printBoard();
            player.move();
        }
        else {
            player.move();
            if(player instanceof Computer) Board.printBoard();
        }
    }

    private boolean checkEndGame(Player player) {
        if (Board.checkWin(player.getToken())) {
            won(player);
            exit = true;
            return true;
        } else if (Board.getFieldsLeft() == 0) {
            draw();
            exit = true;
            return true;
        }
        return false;
    }
}
