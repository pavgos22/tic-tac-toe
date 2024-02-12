package com.game.toe;

public class Board {
    private char[][] board;
    private int fieldsLeft;
    private final int size;
    public Board(int size) {
        this.size = size;
        this.fieldsLeft = size * size;
        this.board = new char[size][size];
        clearBoard();
    }

    public int getSize() {
        return size;
    }

    public int getFieldsLeft() {
        return fieldsLeft;
    }

    public void setField(int x, int y, char c) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            board[x][y] = c;
            fieldsLeft--;
        }
    }

    public char getField(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return board[x][y];
        }
        return '\0';
    }

    public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        fieldsLeft = size * size;
    }

    public boolean checkField(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return board[x][y] == ' ';
        }
        return false;
    }

    public boolean checkWin(char playerToken) {
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != playerToken) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                if (board[i][j] != playerToken) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != playerToken) {
                win = false;
                break;
            }
        }
        if (win) return true;

        win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != playerToken) {
                win = false;
                break;
            }
        }
        return win;
    }

    boolean checkTie() {
        if(fieldsLeft <= 0)
            return true;
        else return false;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print("--");
                }
                System.out.println("-");
            }
        }
    }
}
