/**
 * Author: Ayed Naber
 * Revised: April 11th, 2021
 *
 * Description: Testing BoardT class
 */

package src;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestBoardT {
    private BoardT board1;
    private BoardT board2;
    private BoardT board3;
    private BoardT board4;
    private BoardT board5;

    @Before
    public void setUp() {
        board1 = new BoardT(4);
        board2 = new BoardT(6);
        board3 = new BoardT(8);
        board1.init1(); board1.init2();
        board2.init1(); board2.init2();
        board3.init1(); board3.init2();
    }

    @After
    public void tearDown() {
        board1 = null;
        board2 = null;
        board3 = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void testForInvalidConstrcutor1() {
        board4 = new BoardT(2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testForInvalidConstrcutor2() {
        board5 = new BoardT(3);
    }

    @Test
    public void getBoardSizeTest1() {
        assertEquals(4, board1.getBoardSize());
    }

    @Test
    public void getBoardSizeTest2() {
        assertEquals(6, board2.getBoardSize());
    }

    @Test
    public void getBoardSizeTest3() {
        assertEquals(8, board3.getBoardSize());
    }

    @Test
    public void setBoardSizeTest1() {
        board1.setScore(10);
        assertEquals(10, board1.getScore());
        board1.setScore(4);
    }

    @Test
    public void setBoardSizeTest2() {
        board2.setScore(4);
        assertEquals(4, board2.getScore());
        board2.setScore(6);
    }

    @Test
    public void setBoardSizeTest3() {
        board3.setScore(7);
        assertEquals(7, board3.getScore());
        board3.setScore(8);
    }

    @Test
    public void getScoreTest() {
        assertEquals(0, board1.getScore());
    }

    @Test
    public void setScoreTest1() {
        board1.setScore(73);
        assertEquals(73, board1.getScore());
        board1.setScore(0);
    }

    @Test
    public void setScoreTest2() {
        board2.setScore(87);
        assertEquals(87, board2.getScore());
        board2.setScore(0);
    }

    @Test
    public void setScoreTest3() {
        board3.setScore(194);
        assertEquals(194, board3.getScore());
        board3.setScore(0);
    }

    @Test
    public void isGameOverTest() {
        assertFalse(board1.isGameOver());
    }

    @Test
    public void setGameOverTest() {
        board2.setGameOver(true);
        assertTrue(board2.isGameOver());
        board2.setGameOver(false);
    }

    @Test
    public void getAndSetBoardTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = board1.getBoard();
        ArrayList<ArrayList<TileT>> board = new ArrayList<ArrayList<TileT>>();
        for (int i = 0; i < board1.getBoardSize(); i++) {
            ArrayList<TileT> row = new ArrayList<TileT>();
            for (int j = 0; j < board1.getBoardSize(); j++) {
                row.add(new TileT(0));
            }
            board.add(row);
        }

        board.get(0).get(0).setValue(2);
        board.get(0).get(1).setValue(4);
        board1.setBoard(board);
        assertArrayEquals(board.toArray(), board1.getBoard().toArray());
        board1.setBoard(oldBoard);
    }

    @Test
    public void getAndSetBoardTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = board2.getBoard();
        ArrayList<ArrayList<TileT>> board = new ArrayList<ArrayList<TileT>>();
        for (int i = 0; i < board2.getBoardSize(); i++) {
            ArrayList<TileT> row = new ArrayList<TileT>();
            for (int j = 0; j < board2.getBoardSize(); j++) {
                row.add(new TileT(0));
            }
            board.add(row);
        }

        board.get(2).get(3).setValue(4);
        board.get(0).get(1).setValue(16);
        board2.setBoard(board);
        assertArrayEquals(board.toArray(), board2.getBoard().toArray());
        board2.setBoard(oldBoard);
    }

    @Test
    public void getAndSetBoardTest3() {
        ArrayList<ArrayList<TileT>> oldBoard = board3.getBoard();
        ArrayList<ArrayList<TileT>> board = new ArrayList<ArrayList<TileT>>();
        for (int i = 0; i < board3.getBoardSize(); i++) {
            ArrayList<TileT> row = new ArrayList<TileT>();
            for (int j = 0; j < board3.getBoardSize(); j++) {
                row.add(new TileT(0));
            }
            board.add(row);
        }

        board.get(1).get(2).setValue(4);
        board.get(1).get(0).setValue(8);
        board3.setBoard(board);
        assertArrayEquals(board.toArray(), board3.getBoard().toArray());
        board3.setBoard(oldBoard);
    }

    @Test
    public void mergeRowToTheLeftTest1() {
        ArrayList<ArrayList<TileT>> oldBoard = board1.getBoard();
        ArrayList<TileT> testRow = new ArrayList<TileT>();
        testRow.add(new TileT(0));
        testRow.add(new TileT(2));
        testRow.add(new TileT(2));
        testRow.add(new TileT(0));
        board1.getBoard().set(0, testRow);
        board1.mergeRowToTheLeft(board1.getBoard().get(0));
        int[] expected = new int[]{4, 0, 0, 0};
        int[] actual = new int[board1.getBoardSize()];
        for (int i = 0; i < board1.getBoardSize(); i++) {
            actual[i] = board1.getBoard().get(0).get(i).getValue();
        }
        assertArrayEquals(expected, actual);
        board1.setBoard(oldBoard);
    }

    @Test
    public void mergeRowToTheLeftTest2() {
        ArrayList<ArrayList<TileT>> oldBoard = board2.getBoard();
        ArrayList<TileT> testRow = new ArrayList<TileT>();
        testRow.add(new TileT(4));
        testRow.add(new TileT(4));
        testRow.add(new TileT(0));
        testRow.add(new TileT(0));
        testRow.add(new TileT(2));
        testRow.add(new TileT(2));
        board2.getBoard().set(0, testRow);
        board2.mergeRowToTheLeft(board2.getBoard().get(0));
        int[] expected = new int[]{8, 4, 0, 0, 0, 0};
        int[] actual = new int[board2.getBoardSize()];
        for (int i = 0; i < board2.getBoardSize(); i++) {
            actual[i] = board2.getBoard().get(0).get(i).getValue();
        }
        assertArrayEquals(expected, actual);
        board2.setBoard(oldBoard);
    }
















}
