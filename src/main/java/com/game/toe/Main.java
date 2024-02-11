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

//TODO: Min-max
//TODO: Fix save/load
//TODO: UI
//TODO: Testing
