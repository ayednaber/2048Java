/**
 * Author: Ayed Naber
 * Revised: April 11th, 2021
 *
 * Description: Testing BoardOps class
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class TestBoardOps {
    private BoardOps boardOps1;
    private BoardOps boardOps2;
    private BoardOps boardOps3;
    private BoardT board1;
    private BoardT board2;
    private BoardT board3;

    @Before
    public void setUp() {
        board1 = new BoardT(4);
        board2 = new BoardT(6);
        board3 = new BoardT(8);
        board1.init1(); board1.init2();
        board2.init1(); board2.init2();
        board3.init1(); board3.init2();
        boardOps1 = new BoardOps(board1);
        boardOps2 = new BoardOps(board2);
        boardOps3 = new BoardOps(board3);
    }

    @After
    public void tearDown() {
        board1 = null;
        board2 = null;
        board3 = null;
        boardOps1 = null;
        boardOps2 = null;
        boardOps3 = null;
    }

    @Test
    public void getAndSetBoardTTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = board1.getBoard();
        int[][] expectedBoard = new int[board1.getBoardSize()][board1.getBoardSize()];
        int[][] actualBoard = new int[board1.getBoardSize()][board1.getBoardSize()];
        expectedBoard[0][1] = 2;
        expectedBoard[0][2] = 4;
        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();
        tempBoard.getBoard().get(0).get(1).setValue(2);
        tempBoard.getBoard().get(0).get(2).setValue(4);
        boardOps1.setBoardT(tempBoard);

        for (int i = 0; i < boardOps1.getBoardT().getBoardSize(); i++) {
            for (int j = 0; j < boardOps1.getBoardT().getBoardSize(); j++) {
                actualBoard[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expectedBoard, actualBoard);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testReverse1() {
        int[][] expected = new int[boardOps1.getBoardT().getBoardSize()][boardOps1.getBoardT().getBoardSize()];
        int[][] actual = new int[boardOps1.getBoardT().getBoardSize()][boardOps1.getBoardT().getBoardSize()];

        expected[1][2] = 2;
        expected[2][3] = 2;

        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(1).get(1).setValue(2);
        tempBoard.getBoard().get(2).get(0).setValue(2);
        boardOps1.setBoardT(tempBoard);
        for (int i = 0; i < boardOps1.getBoardT().getBoardSize(); i++) {
            ArrayList<TileT> reversed = boardOps1.reverse(boardOps1.getBoardT().getBoard().get(i));
            boardOps1.getBoardT().getBoard().set(i, reversed);
        }

        for (int i = 0; i < boardOps1.getBoardT().getBoardSize(); i++) {
            for (int j = 0; j < boardOps1.getBoardT().getBoardSize(); j++) {
                actual[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testReverse2() {
        int[][] expected = new int[boardOps2.getBoardT().getBoardSize()][boardOps2.getBoardT().getBoardSize()];
        int[][] actual = new int[boardOps2.getBoardT().getBoardSize()][boardOps2.getBoardT().getBoardSize()];

        expected[1][4] = 2;
        expected[3][3] = 2;

        ArrayList<ArrayList<TileT>> oldBoard = boardOps2.getBoardT().getBoard();
        BoardT tempBoard = new BoardT(board2.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(1).get(1).setValue(2);
        tempBoard.getBoard().get(3).get(2).setValue(2);
        boardOps2.setBoardT(tempBoard);
        for (int i = 0; i < boardOps2.getBoardT().getBoardSize(); i++) {
            ArrayList<TileT> reversed = boardOps2.reverse(boardOps2.getBoardT().getBoard().get(i));
            boardOps2.getBoardT().getBoard().set(i, reversed);
        }

        for (int i = 0; i < boardOps2.getBoardT().getBoardSize(); i++) {
            for (int j = 0; j < boardOps2.getBoardT().getBoardSize(); j++) {
                actual[i][j] = boardOps2.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps2.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testReverse3() {
        int[][] expected = new int[boardOps3.getBoardT().getBoardSize()][boardOps3.getBoardT().getBoardSize()];
        int[][] actual = new int[boardOps3.getBoardT().getBoardSize()][boardOps3.getBoardT().getBoardSize()];

        expected[2][2] = 2;
        expected[3][6] = 2;

        ArrayList<ArrayList<TileT>> oldBoard = boardOps3.getBoardT().getBoard();
        BoardT tempBoard = new BoardT(board3.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(2).get(5).setValue(2);
        tempBoard.getBoard().get(3).get(1).setValue(2);
        boardOps3.setBoardT(tempBoard);
        for (int i = 0; i < boardOps3.getBoardT().getBoardSize(); i++) {
            ArrayList<TileT> reversed = boardOps3.reverse(boardOps3.getBoardT().getBoard().get(i));
            boardOps3.getBoardT().getBoard().set(i, reversed);
        }

        for (int i = 0; i < boardOps3.getBoardT().getBoardSize(); i++) {
            for (int j = 0; j < boardOps3.getBoardT().getBoardSize(); j++) {
                actual[i][j] = boardOps3.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps3.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testTranspose1() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        int[][] expected = new int[board1.getBoardSize()][board1.getBoardSize()];
        int[][] actual = new int[board1.getBoardSize()][board1.getBoardSize()];

        expected[0][1] = 2;
        expected[3][2] = 2;

        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(1).get(0).setValue(2);
        tempBoard.getBoard().get(2).get(3).setValue(2);
        boardOps1.setBoardT(tempBoard);

        boardOps1.getBoardT().setBoard(boardOps1.transpose(tempBoard.getBoard()));
        for (int i = 0; i < board1.getBoardSize(); i++) {
            for (int j = 0; j < board1.getBoardSize(); j++) {
                actual[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testTranspose2() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps2.getBoardT().getBoard();
        int[][] expected = new int[board2.getBoardSize()][board2.getBoardSize()];
        int[][] actual = new int[board2.getBoardSize()][board2.getBoardSize()];

        expected[0][2] = 2;
        expected[0][3] = 2;

        BoardT tempBoard = new BoardT(board2.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(2).get(0).setValue(2);
        tempBoard.getBoard().get(3).get(0).setValue(2);
        boardOps2.setBoardT(tempBoard);

        boardOps2.getBoardT().setBoard(boardOps2.transpose(tempBoard.getBoard()));
        for (int i = 0; i < board2.getBoardSize(); i++) {
            for (int j = 0; j < board2.getBoardSize(); j++) {
                actual[i][j] = boardOps2.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps2.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void testTranspose3() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps3.getBoardT().getBoard();
        int[][] expected = new int[board3.getBoardSize()][board3.getBoardSize()];
        int[][] actual = new int[board3.getBoardSize()][board3.getBoardSize()];

        expected[2][0] = 2;
        expected[2][4] = 2;

        BoardT tempBoard = new BoardT(board3.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(2).setValue(2);
        tempBoard.getBoard().get(4).get(2).setValue(2);
        boardOps3.setBoardT(tempBoard);

        boardOps3.getBoardT().setBoard(boardOps3.transpose(tempBoard.getBoard()));
        for (int i = 0; i < board3.getBoardSize(); i++) {
            for (int j = 0; j < board3.getBoardSize(); j++) {
                actual[i][j] = boardOps3.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }
        assertArrayEquals(expected, actual);
        boardOps3.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeLeftTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        int[][] expected = new int[board1.getBoardSize()][board1.getBoardSize()];
        int[][] actual = new int[board1.getBoardSize()][board1.getBoardSize()];
        expected[0][0] = 8;
        expected[0][1] = 4;

        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(0).setValue(4);
        tempBoard.getBoard().get(0).get(1).setValue(4);
        tempBoard.getBoard().get(0).get(2).setValue(2);
        tempBoard.getBoard().get(0).get(3).setValue(2);
        boardOps1.setBoardT(tempBoard);
        boardOps1.merge_left();

        for (int i = 0; i < board1.getBoardSize(); i++) {
            for (int j = 0; j < board1.getBoardSize(); j++) {
                actual[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeLeftTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps2.getBoardT().getBoard();
        int[][] expected = new int[board2.getBoardSize()][board2.getBoardSize()];
        int[][] actual = new int[board2.getBoardSize()][board2.getBoardSize()];
        expected[0][0] = 16;
        expected[0][1] = 4;

        BoardT tempBoard = new BoardT(board2.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(0).setValue(8);
        tempBoard.getBoard().get(0).get(1).setValue(8);
        tempBoard.getBoard().get(0).get(2).setValue(4);
        boardOps2.setBoardT(tempBoard);
        boardOps2.merge_left();

        for (int i = 0; i < board2.getBoardSize(); i++) {
            for (int j = 0; j < board2.getBoardSize(); j++) {
                actual[i][j] = boardOps2.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps2.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeRightTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        int[][] expected = new int[board1.getBoardSize()][board1.getBoardSize()];
        int[][] actual = new int[board1.getBoardSize()][board1.getBoardSize()];
        expected[0][2] = 8;
        expected[0][3] = 16;

        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(0).setValue(8);
        tempBoard.getBoard().get(0).get(1).setValue(16);
        boardOps1.setBoardT(tempBoard);
        boardOps1.merge_right();

        for (int i = 0; i < board1.getBoardSize(); i++) {
            for (int j = 0; j < board1.getBoardSize(); j++) {
                actual[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeRightTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps3.getBoardT().getBoard();
        int[][] expected = new int[board3.getBoardSize()][board3.getBoardSize()];
        int[][] actual = new int[board3.getBoardSize()][board3.getBoardSize()];
        expected[0][7] = 32;

        BoardT tempBoard = new BoardT(board3.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(0).setValue(16);
        tempBoard.getBoard().get(0).get(3).setValue(16);
        boardOps3.setBoardT(tempBoard);
        boardOps3.merge_right();

        for (int i = 0; i < board3.getBoardSize(); i++) {
            for (int j = 0; j < board3.getBoardSize(); j++) {
                actual[i][j] = boardOps3.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps3.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeUpTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        int[][] expected = new int[board1.getBoardSize()][board1.getBoardSize()];
        int[][] actual = new int[board1.getBoardSize()][board1.getBoardSize()];
        expected[0][2] = 32;
        expected[0][3] = 16;

        BoardT tempBoard = new BoardT(board1.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(2).setValue(16);
        tempBoard.getBoard().get(1).get(2).setValue(16);
        tempBoard.getBoard().get(2).get(3).setValue(8);
        tempBoard.getBoard().get(3).get(3).setValue(8);
        boardOps1.setBoardT(tempBoard);
        boardOps1.merge_up();

        for (int i = 0; i < board1.getBoardSize(); i++) {
            for (int j = 0; j < board1.getBoardSize(); j++) {
                actual[i][j] = boardOps1.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeUpTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps2.getBoardT().getBoard();
        int[][] expected = new int[board2.getBoardSize()][board2.getBoardSize()];
        int[][] actual = new int[board2.getBoardSize()][board2.getBoardSize()];
        expected[0][2] = 64;
        expected[0][3] = 16;
        expected[1][3] = 8;

        BoardT tempBoard = new BoardT(board2.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(2).setValue(32);
        tempBoard.getBoard().get(1).get(2).setValue(32);
        tempBoard.getBoard().get(2).get(3).setValue(16);
        tempBoard.getBoard().get(3).get(3).setValue(8);
        boardOps2.setBoardT(tempBoard);
        boardOps2.merge_up();

        for (int i = 0; i < board2.getBoardSize(); i++) {
            for (int j = 0; j < board2.getBoardSize(); j++) {
                actual[i][j] = boardOps2.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps2.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeDownTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps3.getBoardT().getBoard();
        int[][] expected = new int[board3.getBoardSize()][board3.getBoardSize()];
        int[][] actual = new int[board3.getBoardSize()][board3.getBoardSize()];
        expected[7][0] = 4;
        expected[7][2] = 16;

        BoardT tempBoard = new BoardT(board3.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(0).get(0).setValue(2);
        tempBoard.getBoard().get(1).get(0).setValue(2);
        tempBoard.getBoard().get(1).get(2).setValue(8);
        tempBoard.getBoard().get(3).get(2).setValue(8);
        boardOps3.setBoardT(tempBoard);
        boardOps3.merge_down();

        for (int i = 0; i < board3.getBoardSize(); i++) {
            for (int j = 0; j < board3.getBoardSize(); j++) {
                actual[i][j] = boardOps3.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps3.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void mergeDownTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps2.getBoardT().getBoard();
        int[][] expected = new int[board2.getBoardSize()][board2.getBoardSize()];
        int[][] actual = new int[board2.getBoardSize()][board2.getBoardSize()];
        expected[5][0] = 128;
        expected[5][2] = 32;
        expected[4][2] = 16;

        BoardT tempBoard = new BoardT(board2.getBoardSize());
        tempBoard.init1();

        tempBoard.getBoard().get(2).get(0).setValue(64);
        tempBoard.getBoard().get(5).get(0).setValue(64);
        tempBoard.getBoard().get(1).get(2).setValue(16);
        tempBoard.getBoard().get(3).get(2).setValue(32);
        boardOps2.setBoardT(tempBoard);
        boardOps2.merge_down();

        for (int i = 0; i < board2.getBoardSize(); i++) {
            for (int j = 0; j < board2.getBoardSize(); j++) {
                actual[i][j] = boardOps2.getBoardT().getBoard().get(i).get(j).getValue();
            }
        }

        assertArrayEquals(expected, actual);
        boardOps2.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void isWonTest() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        boardOps1.getBoardT().getBoard().get(0).get(0).setValue(2048);
        boardOps1.setBoardT(board1);
        assertTrue(boardOps1.isWon());
        boardOps1.getBoardT().setBoard(oldBoard);
    }

    @Test
    public void cannotMoveTest() {
        ArrayList<ArrayList<TileT>> oldBoard = boardOps1.getBoardT().getBoard();
        BoardT tempBoard = new BoardT(4);
        tempBoard.init1();
        tempBoard.getBoard().get(0).get(0).setValue(2);
        tempBoard.getBoard().get(0).get(1).setValue(64);
        tempBoard.getBoard().get(0).get(2).setValue(4);
        tempBoard.getBoard().get(0).get(3).setValue(2);
        tempBoard.getBoard().get(1).get(0).setValue(16);
        tempBoard.getBoard().get(1).get(1).setValue(32);
        tempBoard.getBoard().get(1).get(2).setValue(128);
        tempBoard.getBoard().get(1).get(3).setValue(16);
        tempBoard.getBoard().get(2).get(0).setValue(8);
        tempBoard.getBoard().get(2).get(1).setValue(16);
        tempBoard.getBoard().get(2).get(2).setValue(32);
        tempBoard.getBoard().get(2).get(3).setValue(8);
        tempBoard.getBoard().get(3).get(0).setValue(2);
        tempBoard.getBoard().get(3).get(1).setValue(8);
        tempBoard.getBoard().get(3).get(2).setValue(4);
        tempBoard.getBoard().get(3).get(3).setValue(2);
        boardOps1.setBoardT(tempBoard);
        assertTrue(boardOps1.cannotMove());
    }









}
