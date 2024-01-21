package com.game.util;

import java.util.Scanner;

public class InputScanner {
    private static Scanner scanner;

    private InputScanner() {

    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}