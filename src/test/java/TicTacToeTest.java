import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private Main test;
    @BeforeEach
    public void TestSetUp(){
        test = new Main();
        test.resetGame();
    }

    @Test
    public void checkBoardInitialization(){
        char[] board = test.getBoard();
        for (char cell : board) {
            assertEquals(' ', cell);
        }

        assertEquals('X', test.getCurrentPlayer());
    }
    @Test
    public void TestOfMoveMaking(){
        int row = 0, col = 0;

        int res = test.makeMove(row, col);
        char[] board = test.getBoard();

        assertEquals('X', board[row * 5 + col]);
        assertEquals(0, res);
        assertEquals('O', test.getCurrentPlayer());
    }

    @Test
    public void WinningConditionTestRows(){
        test.makeMove(0, 0); //x 1
        test.makeMove(1, 0);
        test.makeMove(0, 1); //x 2
        test.makeMove(1, 1);
        test.makeMove(0, 2); //x 3
        test.makeMove(1, 2);
        test.makeMove(0, 3); //x 4
        test.makeMove(1, 3);

        int res = test.makeMove(0,4);
        assertEquals(1, res);
    }

    @Test
    public void WinningConditionTestColumns(){
        test.makeMove(0, 0); //x 1
        test.makeMove(0, 1);
        test.makeMove(1, 0); //x 2
        test.makeMove(0, 2);
        test.makeMove(2, 0); //x 3
        test.makeMove(0, 3);
        test.makeMove(3, 0); //x 4
        test.makeMove(0, 4);

        int res = test.makeMove(4,0);
        assertEquals(1, res);
    }

    @Test
    public void WinningConditionTestDiagonals(){
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
    public void TieConditionTest(){
        test.makeMove(0, 0); // X
        test.makeMove(0, 1); // 0
        test.makeMove(0, 2); // X
        test.makeMove(0, 3); // 0
        test.makeMove(0, 4); // X

        test.makeMove(1, 0); // 0
        test.makeMove(1, 1); // X
        test.makeMove(1, 2); // 0
        test.makeMove(1, 3); // X
        test.makeMove(1, 4); // 0

        test.makeMove(2, 0); // X
        test.makeMove(2, 1); // 0
        test.makeMove(2, 2); // X
        test.makeMove(2, 3); // 0
        test.makeMove(2, 4); // X

        test.makeMove(3, 0); // 0
        test.makeMove(3, 1); // X
        test.makeMove(3, 2); // 0
        test.makeMove(3, 3); // X
        test.makeMove(3, 4); // 0

        test.makeMove(4, 1); // X
        test.makeMove(4, 0); // 0
        test.makeMove(4, 3); // X
        test.makeMove(4, 4); // 0

        int result = test.makeMove(4, 2);
        assertEquals(2, result);
    }

    @Test
    public void TestKeyBoardMoveMaking(){
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

}
