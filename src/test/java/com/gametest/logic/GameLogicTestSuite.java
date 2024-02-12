package com.gametest.logic;
import com.game.toe.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTestSuite {

    @Nested
    class TestsFor3x3Board {
        private Board board;

        @BeforeEach
        void setup() {
            board = new Board(3);
        }
            @Test
            void testHorizontalWinTopRow() {
                board.setField(0, 0, 'X');
                board.setField(0, 1, 'X');
                board.setField(0, 2, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testHorizontalWinMiddleRow() {
                board.setField(1, 0, 'X');
                board.setField(1, 1, 'X');
                board.setField(1, 2, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testHorizontalWinBottomRow() {
                board.setField(2, 0, 'X');
                board.setField(2, 1, 'X');
                board.setField(2, 2, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testVerticalWinFirstColumn() {
                board.setField(0, 0, 'X');
                board.setField(1, 0, 'X');
                board.setField(2, 0, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testVerticalWinSecondColumn() {
                board.setField(0, 1, 'X');
                board.setField(1, 1, 'X');
                board.setField(2, 1, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testVerticalWinThirdColumn() {
                board.setField(0, 2, 'X');
                board.setField(1, 2, 'X');
                board.setField(2, 2, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testDiagonalWinFromTopLeftToBottomRight() {
                board.setField(0, 0, 'X');
                board.setField(1, 1, 'X');
                board.setField(2, 2, 'X');
                assertTrue(board.checkWin('X'));
            }

            @Test
            void testDiagonalWinFromTopRightToBottomLeft() {
                board.setField(0, 2, 'X');
                board.setField(1, 1, 'X');
                board.setField(2, 0, 'X');
                assertTrue(board.checkWin('X'));
            }

    }

    @Nested
    class TestsFor5x5Board {
        private Board board;

        @BeforeEach
        void setup() {
            board = new Board(5);  // Ustaw rozmiar planszy na 5x5
        }

        @Test
        void testHorizontalWinTopRow() {
            for (int i = 0; i < 5; i++) {
                board.setField(0, i, 'X');
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testHorizontalWinMiddleRow() {
            for (int i = 0; i < 5; i++) {
                board.setField(2, i, 'X');  // Środkowy rząd dla planszy 5x5
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testHorizontalWinBottomRow() {
            for (int i = 0; i < 5; i++) {
                board.setField(4, i, 'X');  // Ostatni rząd dla planszy 5x5
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testVerticalWinFirstColumn() {
            for (int i = 0; i < 5; i++) {
                board.setField(i, 0, 'X');
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testVerticalWinMiddleColumn() {
            for (int i = 0; i < 5; i++) {
                board.setField(i, 2, 'X');  // Środkowa kolumna dla planszy 5x5
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testVerticalWinLastColumn() {
            for (int i = 0; i < 5; i++) {
                board.setField(i, 4, 'X');  // Ostatnia kolumna dla planszy 5x5
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testDiagonalWinFromTopLeftToBottomRight() {
            for (int i = 0; i < 5; i++) {
                board.setField(i, i, 'X');
            }
            assertTrue(board.checkWin('X'));
        }

        @Test
        void testDiagonalWinFromTopRightToBottomLeft() {
            for (int i = 0; i < 5; i++) {
                board.setField(i, 4 - i, 'X');
            }
            assertTrue(board.checkWin('X'));
        }
    }

    @Nested
    class TieConditions {
        private Board board;

        @BeforeEach
        void setup() {
            board = new Board(3); // Assuming a 3x3 board
        }

        @Test
        void testForTieScenario1() {
            // First tie scenario
            // X|O|X
            // O|X|O
            // O|X|O
            board.setField(0, 0, 'X');
            board.setField(0, 1, 'O');
            board.setField(0, 2, 'X');
            board.setField(1, 0, 'O');
            board.setField(1, 1, 'X');
            board.setField(1, 2, 'O');
            board.setField(2, 0, 'O');
            board.setField(2, 1, 'X');
            board.setField(2, 2, 'O');

            assertFalse(board.checkWin('X'));
            assertFalse(board.checkWin('O'));
            assertTrue(board.checkTie());
        }

        @Test
        void testForTieScenario2() {
            // Second tie scenario
            // O|X|O
            // X|O|X
            // X|O|X
            board.setField(0, 0, 'O');
            board.setField(0, 1, 'X');
            board.setField(0, 2, 'O');
            board.setField(1, 0, 'X');
            board.setField(1, 1, 'O');
            board.setField(1, 2, 'X');
            board.setField(2, 0, 'X');
            board.setField(2, 1, 'O');
            board.setField(2, 2, 'X');

            assertFalse(board.checkWin('X'));
            assertFalse(board.checkWin('O'));
            assertTrue(board.checkTie());
        }
    }
}
