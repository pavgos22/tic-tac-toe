package com.game.toe;
public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.setup();

        while(!game.isExit()) {
            game.run();
        }
    }
}

//TODO: Custom size board
//TODO: Min-max
//TODO: Fix load
//TODO: Testing
