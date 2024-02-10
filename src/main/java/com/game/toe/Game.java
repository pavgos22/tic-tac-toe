package com.game.toe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Player p1, p2;
    private Player activePlayer;
    private Board board;
    private int gameMode = 0;
    private int difficulty;
    private static int moveNumber = 0;
    private boolean exit = false;
    private boolean pause = false;
    public static Scanner input = new Scanner(System.in);

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getMoveNumber() { return moveNumber; }
    public static void incMoveNumber() { moveNumber++; }

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

    private void setActivePlayer() {
        activePlayer = (p1.getToken() == 'O') ? p1 : p2;
    }


    public Game() {

    }

    public static void swapTokens(Player p1, Player p2) {
        char temp = p1.getToken();
        p1.setToken(p2.getToken());
        p2.setToken(temp);
    }

    public void printGameIntro() {
        System.out.println("Tic-Tac-Toe Game");
        System.out.println("-----------------");
        System.out.println("Select Mode: ");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");
        System.out.println("3. Load Game");
        System.out.println("4. Exit");
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
                gameMode = 3;
                break;
            case 4:
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

        p1 = new Human(pl1, firstToken, this);
        p2 = new Human(pl2, secondToken, this);

        setActivePlayer();

        printPlayersToken();
    }

    void pvrandomIntro() {
        System.out.print("Enter your name: ");
        String pl1 = input.next();
        char firstToken = chooseToken(pl1);
        char secondToken = (firstToken == 'X') ? 'O' : 'X';

        p1 = new Human(pl1, firstToken, this);
        p2 = new Computer(secondToken, this);
        p2.setName(difficulty);

        setActivePlayer();

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

    void endOfGame() {
        printScore();
        System.out.println();
        System.out.println("Press N to start new game");
        System.out.println("Press M to go back to menu");
        System.out.println("Press X to exit");

        char choice = input.next().charAt(0);
        if (choice == 'n' || choice == 'N')
            newGame();
        else if (choice == 'm' || choice == 'M')
            setup();
        else if (choice == 'x' || choice == 'X')
            exit = true;

    }

    void newGame() { // starting another game
        moveNumber = 0;
        board.clearBoard();
        swapTokens(p1, p2);
        System.out.println("The tokens have switched");
        setActivePlayer();
        exit = false;
        run();
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

    void boardSize() {
        System.out.print("Select board size: ");
        int size = input.nextInt();
        Board board = new Board(size);
        setBoard(board);
    }
    void setup() {
        gameMode = 0; difficulty = 0;
        boardSize();
        printGameIntro();
        gameIntro();
        if(gameMode == 1) {
            difficultyChoice();
        }
        else if(gameMode == 2) {
            pvpIntro();
        }
        else if(gameMode == 3) {
            loadGame("game_save.txt");
            System.out.println("Game loaded.");
        }
        else
            exit = true;
    }

    public void run() {
        while (!exit) {
            if(activePlayer instanceof Human)
                System.out.print("Enter 'S' to save game, 'L' to load game, or press Enter to continue: ");
            if(moveNumber == 0)
                System.out.println();

            String inputLine = input.nextLine().trim();
            if (inputLine.equalsIgnoreCase("S")) {
                saveGame("game_save.txt");
                System.out.println("Game saved.");
            }
            else if (inputLine.equalsIgnoreCase("L")) {
                loadGame("game_save.txt");
                System.out.println("Game loaded.");
            }
            else {
                if(gameMode == 2) board.printBoard();
                activePlayer.move();
                if(activePlayer instanceof Computer) board.printBoard();
                if (checkEndGame(activePlayer))
                    break;

                activePlayer = (activePlayer == p1) ? p2 : p1;
            }
        }
        if (!exit) endOfGame();
    }

    private boolean checkEndGame(Player player) {
        if (board.checkWin(player.getToken())) {
            won(player);
            return true;
        } else if (board.getFieldsLeft() == 0) {
            draw();
            return true;
        }
        return false;
    }

    public void saveGame(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    writer.write(board.getField(i, j) + " ");
                }
                writer.write("\n");
            }

            writer.write(gameMode +  ", " + difficulty + "\n");

            writer.write(p1.getName() + "," + p1.getToken() + "," + p1.getScore() + "\n");
            writer.write(p2.getName() + "," + p2.getToken() + "," + p2.getScore() + "\n");

            String currentPlayer = (activePlayer == p1) ? p1.getName() : p2.getName();
            writer.write(currentPlayer + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
            e.printStackTrace();
        }
    }

    public void loadGame(String filename) {

        try (Scanner scanner = new Scanner(new File(filename))) {

            for (int i = 0; i < 3; i++) {
                if (scanner.hasNextLine()) {
                    String[] line = scanner.nextLine().trim().split(" ");
                    if (line.length >= 3) {
                        for (int j = 0; j < 3; j++) {
                            board.setField(i, j, line[j].charAt(0));
                        }
                    }
                }
            }

            if (scanner.hasNextLine()) {
                String[] gameStateInfo = scanner.nextLine().split(",");
                if (gameStateInfo.length >= 2) {
                    gameMode = Integer.parseInt(gameStateInfo[0].trim());
                    difficulty = Integer.parseInt(gameStateInfo[1].trim());
                }
            }

            if (scanner.hasNextLine()) {
                String[] p1Info = scanner.nextLine().split(",");
                if (p1Info.length >= 3) {
                    p1 = new Human(p1Info[0].trim(), p1Info[1].charAt(0), this);
                    p1.setScore(Integer.parseInt(p1Info[2].trim()));
                }
            }

            if (scanner.hasNextLine()) {
                String[] p2Info = scanner.nextLine().split(",");
                if (p2Info.length >= 3) {
                    if (gameMode == 1) {
                        p2 = new Computer(p2Info[1].charAt(0), this);
                        p2.setName(difficulty);
                    } else {
                        p2 = new Human(p2Info[0].trim(), p2Info[1].charAt(0), this);
                    }
                    p2.setScore(Integer.parseInt(p2Info[2].trim()));
                }
            }

            if (scanner.hasNextLine()) {
                String currentPlayerName = scanner.nextLine().trim();
                activePlayer = (p1.getName().equals(currentPlayerName)) ? p1 : p2;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Game file not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred while loading the game.");
            e.printStackTrace();
        }
    }
}
