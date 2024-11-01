import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeTest {

    private Main test;

    @BeforeEach
    public void TestSetUp() {
        test = new Main();
        test.resetGame();
    }

    @Test
    public void ResetGameCheck() {
        test.makeMove(0, 0);
        test.makeMove(0, 1);
        test.resetGame();

        char[] board = test.getBoard();
        for (char ch : board) {
            assertEquals(' ', ch);
        }

        assertEquals('X', test.getCurrentPlayer());
    }

    @Test
    public void checkBoardInitialization() {
        char[] board = test.getBoard();
        for (char cell : board) {
            assertEquals(' ', cell);
        }

        assertEquals('X', test.getCurrentPlayer());
    }

    @Test
    public void TestOfMoveMaking() {
        int row = 0, col = 0;

        int res = test.makeMove(row, col);
        char[] board = test.getBoard();

        assertEquals('X', board[row * 5 + col]);
        assertEquals(0, res);
        assertEquals('O', test.getCurrentPlayer());
    }

    @Test
    public void MovingOnOccupiedFieldTest() {
        test.makeMove(0, 0);
        char[] boardBefore = test.getBoard().clone();
        char currentPlayer = test.getCurrentPlayer();

        int res = test.makeMove(0, 0);
        assertArrayEquals(boardBefore, test.getBoard()); //should be the same
        assertEquals(currentPlayer, test.getCurrentPlayer()); //shouldn't switch it
        assertEquals(0, res);
    }

    @Test
    public void WinningConditionTestRows() {
        test.makeMove(0, 0); //x 1
        test.makeMove(1, 0);
        test.makeMove(0, 1); //x 2
        test.makeMove(1, 1);
        test.makeMove(0, 2); //x 3
        test.makeMove(1, 2);
        test.makeMove(0, 3); //x 4
        test.makeMove(1, 3);

        int res = test.makeMove(0, 4);
        assertEquals(1, res);
    }

    @Test
    public void WinningConditionTestColumns() {
        test.makeMove(0, 0); //x 1
        test.makeMove(0, 1);
        test.makeMove(1, 0); //x 2
        test.makeMove(0, 2);
        test.makeMove(2, 0); //x 3
        test.makeMove(0, 3);
        test.makeMove(3, 0); //x 4
        test.makeMove(0, 4);

        int res = test.makeMove(4, 0);
        assertEquals(1, res);
    }

    @Test
    public void WinningConditionTestFirstDiagonal() {
        test.makeMove(0, 0); //x 1
        test.makeMove(1, 0);
        test.makeMove(1, 1); //x 2
        test.makeMove(2, 0);
        test.makeMove(2, 2); //x 3
        test.makeMove(3, 0);
        test.makeMove(3, 3); //x 4
        test.makeMove(4, 0);

        int res = test.makeMove(4, 4);
        assertEquals(1, res);
    }

    @Test
    public void WinningConditionTestSecondDiagonal() {
        test.makeMove(0, 4); //x 1
        test.makeMove(1, 4);
        test.makeMove(1, 3); //x 2
        test.makeMove(2, 4);
        test.makeMove(2, 2); //x 3
        test.makeMove(3, 4);
        test.makeMove(3, 1); //x 4
        test.makeMove(4, 4);

        int res = test.makeMove(4, 0);
        assertEquals(1, res);
    }

    @Test
    public void TieConditionTest() {
        test.makeMove(0, 0); //x
        test.makeMove(0, 1); //o
        test.makeMove(0, 2); //x
        test.makeMove(0, 3); //o
        test.makeMove(0, 4); //x

        test.makeMove(1, 0); //o
        test.makeMove(1, 1); //x
        test.makeMove(1, 2); //o
        test.makeMove(1, 3); //x
        test.makeMove(1, 4); //o

        test.makeMove(2, 0); //x
        test.makeMove(2, 1); //o
        test.makeMove(2, 2); //x
        test.makeMove(2, 3); //o
        test.makeMove(2, 4); //x

        test.makeMove(3, 0); //o
        test.makeMove(3, 1); //x
        test.makeMove(3, 2); //o
        test.makeMove(3, 3); //x
        test.makeMove(3, 4); //o

        test.makeMove(4, 1); //x
        test.makeMove(4, 0); //o
        test.makeMove(4, 3); //x
        test.makeMove(4, 4); //o

        int result = test.makeMove(4, 2);
        assertEquals(2, result);
    }

    @Test
    public void WinningConditionForO() {
        test.makeMove(0, 0);
        test.makeMove(1, 0); //o 1
        test.makeMove(0, 1);
        test.makeMove(1, 1); //o 2
        test.makeMove(2, 2);
        test.makeMove(1, 2); //o 3
        test.makeMove(3, 3);
        test.makeMove(1, 3); //o 4
        test.makeMove(4, 4);

        int res = test.makeMove(1, 4);
        assertEquals(1, res);
        assertEquals('O', test.getCurrentPlayer());
    }

    @Test
    public void WinningConditionResetCheck() {
        test.makeMove(0, 0);
        test.makeMove(1, 0);
        test.makeMove(0, 1);
        test.makeMove(1, 1);
        test.makeMove(0, 2);
        test.makeMove(1, 2);
        test.makeMove(0, 3);
        test.makeMove(1, 3);

        int res = test.makeMove(0, 4);
        assertEquals(1, res);

        test.resetGame();
        res = test.makeMove(0, 0);
        assertEquals(0, res);

        assertEquals('O', test.getCurrentPlayer());
    }

    @Test
    public void TestKeyBoardMoveMaking() {
        int[] initialCell = test.getActiveCell();
        test.moveActiveCell(0); //up
        int[] movedUp = test.getActiveCell();
        assertEquals((initialCell[0] - 1 + 5) % 5, movedUp[0]);

        test.moveActiveCell(1); //down
        int[] movedDown = test.getActiveCell();
        assertEquals(initialCell[0], movedDown[0]);

        test.moveActiveCell(2); //left
        int[] movedLeft = test.getActiveCell();
        assertEquals((initialCell[1] - 1 + 5) % 5, movedLeft[1]);

        test.moveActiveCell(3); //right
        int[] movedRight = test.getActiveCell();
        assertEquals(initialCell[1], movedRight[1]);
    }

    @Test
    public void EdgeWrappingActiveCellTesting() {
        test.moveActiveCell(2); //move left, should wrap to last column
        assertArrayEquals(new int[]{0, 4}, test.getActiveCell());

        test.moveActiveCell(0); //move up, should wrap to last row
        assertArrayEquals(new int[]{4, 4}, test.getActiveCell());

        test.moveActiveCell(3); //move right, should wrap to first column
        assertArrayEquals(new int[]{4, 0}, test.getActiveCell());

        test.moveActiveCell(1); //move down, should wrap to first row
        assertArrayEquals(new int[]{0, 0}, test.getActiveCell());
    }


}
