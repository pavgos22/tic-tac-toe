package com.gametest.logic;
import com.game.toe.Board;
import com.game.toe.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTestSuite {

    @BeforeEach
    void setup() {
        Board.clearBoard();
    }

    @AfterEach
    void cleanup() {
        Board.clearBoard();
    }

    @Test
    void testHorizontalWinTopRow() {
        Board.setField(0, 0, 'X');
        Board.setField(0, 1, 'X');
        Board.setField(0, 2, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testHorizontalWinMiddleRow() {
        Board.setField(1, 0, 'X');
        Board.setField(1, 1, 'X');
        Board.setField(1, 2, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testHorizontalWinBottomRow() {
        Board.setField(2, 0, 'X');
        Board.setField(2, 1, 'X');
        Board.setField(2, 2, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testVerticalWinFirstColumn() {
        Board.setField(0, 0, 'X');
        Board.setField(1, 0, 'X');
        Board.setField(2, 0, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testVerticalWinSecondColumn() {
        Board.setField(0, 1, 'X');
        Board.setField(1, 1, 'X');
        Board.setField(2, 1, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testVerticalWinThirdColumn() {
        Board.setField(0, 2, 'X');
        Board.setField(1, 2, 'X');
        Board.setField(2, 2, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testDiagonalWinFromTopLeftToBottomRight() {
        Board.setField(0, 0, 'X');
        Board.setField(1, 1, 'X');
        Board.setField(2, 2, 'X');
        assertTrue(Board.checkWin('X'));
    }

    @Test
    void testDiagonalWinFromTopRightToBottomLeft() {
        Board.setField(0, 2, 'X');
        Board.setField(1, 1, 'X');
        Board.setField(2, 0, 'X');
        assertTrue(Board.checkWin('X'));
    }
}
