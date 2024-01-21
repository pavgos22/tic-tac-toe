package com.game.toe;

public class Board {
    private static char[][] board = new char[3][3];

    public static char[][] getBoard() {
        return board;
    }

    public static void setBoard(char[][] newBoard) {
        board = newBoard;
    }

    public static void setField(int x, int y, char c) {
        board[x][y] = c;
    }

    public static char getField(int x, int y) {
        return board[x][y];
    }

    public static boolean checkField(int x, int y) {
        if(board[x][y] == ' ')
            return true;
        else return false;
    }

    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static boolean checkWin(char playerToken) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == playerToken && board[i][1] == playerToken && board[i][2] == playerToken) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == playerToken && board[1][j] == playerToken && board[2][j] == playerToken) {
                return true;
            }
        }

        if (board[0][0] == playerToken && board[1][1] == playerToken && board[2][2] == playerToken) {
            return true;
        }
        if (board[0][2] == playerToken && board[1][1] == playerToken && board[2][0] == playerToken) {
            return true;
        }

        return false;
    }

    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
            if (i < 2) {
                System.out.println("-------");
            }
        }
    }
}
