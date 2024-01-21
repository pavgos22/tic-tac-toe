package com.game.toe;

import com.game.util.InputScanner;

import java.util.Scanner;

public class Game {
    private Player p1, p2;
    private int gameMode;
    private int difficulty;
    private boolean exit = false;
    private boolean pause = false;

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

    public void printGameIntro() {
        System.out.println("Tic-Tac-Toe Game");
        System.out.println("-----------------");
        System.out.println("Select Mode: ");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");
        System.out.println("3. Exit");
    }

    public void gameIntro() {
        Scanner input = InputScanner.getScanner();
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
        input.close();
    }

    void pvpIntro() {
        Scanner input = InputScanner.getScanner();
        System.out.println("Enter name of a first player: ");
        String pl1 = input.next();
        System.out.println("Enter name of a second player: ");
        String pl2 = input.next();
        System.out.println("Enter " + pl1 + "'s token(O/X): ");
        char firstToken;
        char secondToken;
        while (true) {
            System.out.println("Enter " + pl1 + "'s token (O/X): ");
            firstToken = input.next().toUpperCase().charAt(0);
            if (firstToken == 'X' || firstToken == 'O') {
                break;
            } else
                System.out.println("Invalid token. Please enter 'O' or 'X'.");
        }
        if (firstToken == 'X') secondToken = 'O';
        else secondToken = 'X';

        p1 = new Human(pl1, firstToken);
        p2 = new Human(pl2, secondToken);

        System.out.println(p1.getName() + ": " + p1.getToken());
        System.out.println(p2.getName() + ": " + p2.getToken());
        input.close();
    }

    void won(Player p) {
        p.setScore(p.getScore()+1);
        System.out.println(p.getName() + " won!");
        pause = true;
    }

    void makeMove() {
        p1.move();
        p2.move();
        if(Board.checkWin(p1.getToken())) {
            won(p1);
        }
        if(Board.checkWin(p2.getToken())) {
            won(p2);
        }
    }

    void printScore() {
        System.out.println("-----Score Table-----");
        System.out.println(p1.getName() + ": " + p1.getScore());
        System.out.println(p2.getName() + ": " + p2.getScore());
    }

    void newGame() {
        System.out.println();
        System.out.println("Press N to start new game");
        System.out.println("Press X to exit");

        Scanner input = InputScanner.getScanner();
        char choice = input.next().charAt(0);
        if(choice == 'n' || choice == 'N')
            pause = false;
        else if(choice == 'x' || choice == 'X')
            exit = true;

    }

    void setup() {
        printGameIntro();
        gameIntro();
        //TODO: add more gamemodes
        if(gameMode == 2){
            pvpIntro();
        }
    }

    void run() {
        if(!pause) {
            Board.printBoard();
            makeMove();
            printScore();
            newGame();
        }
    }
}
