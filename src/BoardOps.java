/**
 * Author: Ayed Naber
 * Revised: April 8th, 2021
 *
 * Description: A class that is responsible for performing operations
 * on the 2048 game board.
 */

package src;

import java.util.ArrayList;
import java.util.Random;

/**
* @brief An Abstract Data Type (ADT) that is responsible for performing operations on
* the 2048 game board.
* @details The sole state variable in this ADT is a BoardT object, which represents the
* 2048 game board, and all the necessary operations needed to run a functional 2048 game
* are contained in this module, such as merging tiles, keeping track of the score, deciding
* if a game is won or lost, and more.
*/
public class BoardOps {
    private BoardT boardT;

    /**
    * @brief BoardOps Constructor
    * @details Initializes a BoardOps object, where the constructor takes in a BoardT object
    * and sets the value of the board state variable to the BoardT object given as an argument.
    */ 
    public BoardOps(BoardT boardT) {
        this.boardT = boardT;
    }

    /**
    * @brief Getter method for the BoardT object in BoardOps.
    * @return This method returns the BoardT object associated with the BoardOps object.
    */
    public BoardT getBoardT() {
        return this.boardT;
    }

    /**
    * @brief Setter method for the BoardT object in BoardOps.
    * @param boardT BoardT object to update the BoardT object associated with the BoardOps operation.
    */
    public void setBoardT(BoardT boardT) {
        this.boardT = boardT;
    }

    /**
     * @brief This function returns the reverse of a row in the game board.
     * @details The reverse function is used for the merge right operation, where
     * the function takes in a row, and reverses it, and then returns it.
     * @param row An ArrayList containing TileT objects; representing a row on the game board.
     * @return An ArrayList of TileT objects; representing the reversed row.
     */
    public ArrayList<TileT> reverse(ArrayList<TileT> row) {
        ArrayList<TileT> rev = new ArrayList<TileT>();
        for (int i = boardT.getBoardSize() - 1; i > -1; i--) {
            rev.add(row.get(i));
        }
        return rev;
    }

    /**
     * @brief This function returns the transpose of the game board.
     * @details Since the method of implementation of merge operations will be using
     * merging rows to the left, the transpose method is used in order to perform merging
     * up and down.
     * @param currentBoard A nested ArrayList containing TileT objects, which represents the game board.
     * @return A nested ArrayList containing TileT objects, which is the transpose of the game board given as input.
     */
    public ArrayList<ArrayList<TileT>> transpose(ArrayList<ArrayList<TileT>> currentBoard) {
        ArrayList<ArrayList<TileT>> transposeBoard = new ArrayList<ArrayList<TileT>>();
        for (int i = 0; i < boardT.getBoardSize(); i++) {
            ArrayList<TileT> newTiles = new ArrayList<TileT>();
            for (int j = 0; j < boardT.getBoardSize(); j++) {
                newTiles.add(new TileT(0));
            }
            transposeBoard.add(newTiles);
        }

        for (int i = 0; i < boardT.getBoardSize(); i++) {
            for (int j = 0; j < boardT.getBoardSize(); j++) {
                transposeBoard.get(j).get(i).setValue(currentBoard.get(i).get(j).getValue());
            }
        }
        return transposeBoard;
    }

    /**
     * @brief This function performs a left merge on the 2048 game board.
     * @details The mergeRowToTheLeft method from the BoardT class is being used here,
     * where this function is called on every row in the game board.
     */
    public void merge_left() {
        for (int i = 0; i < this.boardT.getBoardSize(); i++) {
            boardT.mergeRowToTheLeft(boardT.getBoard().get(i));
        }
    }

    /**
     * @brief This function performs a right merge on the 2048 game board.
     * @details Every row in the board is visited and is reversed, then it is
     * merged to the left, then reversed back. Therefore, for each iteration,
     * each row has been merged to the right. The end result is the board after
     * a right merge has been completed.
     */
    public void merge_right() {
        for (int i = 0; i < boardT.getBoardSize(); i++) {
            this.boardT.getBoard().set(i, reverse(boardT.getBoard().get(i)));
            this.boardT.mergeRowToTheLeft(boardT.getBoard().get(i));
            this.boardT.getBoard().set(i, reverse(boardT.getBoard().get(i)));
        }
    }

    /**
     * @brief This function performs an up merge on the 2048 game board.
     * @details The way this function works is that the board is updated to its transpose,
     * then a left merge is completed, and then the current board is updated to its transpose.
     * This sequence of events represents an up merge on the game board.
     */
    public void merge_up() {
        this.boardT.setBoard(transpose(boardT.getBoard()));
        merge_left();
        this.boardT.setBoard(transpose(boardT.getBoard()));
    }

    /**
     * @brief This function performs an down merge on the 2048 game board.
     * @details The way this function works is that the board is updated to its transpose,
     * then a right merge is completed, and then the current board is updated to its transpose.
     * This sequence of events represents a down merge on the game board.
     */
    public void merge_down() {
        this.boardT.setBoard(transpose(boardT.getBoard()));
        merge_right();
        this.boardT.setBoard(transpose(boardT.getBoard()));
    }

    /**
     * @brief This function shows if the game has been won or not.
     * @details In order to win a 2048 game, then there has to be a tile with
     * a value of 2048 on the game board, and once that happens, then the player wins
     * the game. So, this function goes through the board, and if 2048 is found on the board,
     * it returns true. Otherwise, it returns false.
     * @return Boolean value representing whether the game is won or not.
     */
    public boolean isWon() {
        for (ArrayList<TileT> row : this.boardT.getBoard()) {
            for (TileT element : row) {
                if (element.getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief This function determines whether a game has been lost or not.
     * @details This function creates two temporary Board objects, and sets
     * their boards to the current board. Then, the first temporary board has
     * all the merge operations performed on it, and between each operation, it is
     * compared to the second temporary board, and if they are equal, then it moves on.
     * If they are different, then the game is not lost. If all merging operations are done,
     * and the first board is equal to the second board, then this means that no more moves
     * can be done and the game is lost.
     * @return Boolean value representing whether the game is won or lost.
     */
    public boolean cannotMove() {
        BoardT tempBoard1 = new BoardT(this.boardT.getBoardSize());
        BoardT tempBoard2 = new BoardT(this.boardT.getBoardSize());
        tempBoard1.setBoard(this.boardT.getBoard());
        tempBoard2.setBoard(this.boardT.getBoard());
        BoardOps tempBoardOps1 = new BoardOps(tempBoard1);
        BoardOps tempBoardOps2 = new BoardOps(tempBoard2);

        tempBoardOps1.merge_down();
        if (tempBoardOps1.areTwoBoardsEqual(tempBoardOps1, tempBoardOps2)) {
            tempBoardOps1.merge_up();
            if (tempBoardOps1.areTwoBoardsEqual(tempBoardOps1, tempBoardOps2)) {
                tempBoardOps1.merge_left();
                if (tempBoardOps1.areTwoBoardsEqual(tempBoardOps1, tempBoardOps2)) {
                    tempBoardOps1.merge_right();
                    if (tempBoardOps1.areTwoBoardsEqual(tempBoardOps1, tempBoardOps2)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @brief This function adds a new value onto the board.
     * @details Using the random library, this function finds a spot on the board where the value
     * is zero, and places a new value there. This is with the help of the pickNewValue function defined
     * above. This function is called whenever a successful move is performed on the board.
     */
    public void addNewValue() {
        Random random = new Random();
        int rowNumber = random.nextInt(this.boardT.getBoardSize() - 1);
        int colNumber = random.nextInt(this.boardT.getBoardSize() - 1);

        while (!(this.boardT.getBoard().get(rowNumber).get(colNumber).getValue() == 0)) {
            rowNumber = random.nextInt(this.boardT.getBoardSize() - 1);
            colNumber = random.nextInt(this.boardT.getBoardSize() - 1);
        }
        this.boardT.getBoard().get(rowNumber).get(colNumber).setValue(boardT.pickNewValue());
    }

    /**
     * @brief This function restarts the game.
     * @details The way this is done is that a new board is created and initialized, then
     * it is set to the current board. The score is reset back to zero as well.
     */
    public void newGame() {
        BoardT newBoard = new BoardT(this.boardT.getBoardSize());
        newBoard.init1();
        newBoard.init2();
        this.boardT.setBoard(newBoard.getBoard());
        this.boardT.setScore(0);
    }

    /**
     * @brief This function checks whether two boards are equal or not.
     * @details The way this function computes whether two boards are equal is by going through each tile in both
     * boards, and checking if the tiles at the corresponding positions in the two boards are equal. If it finds
     * a tile that is not equal, then it stops iterating and just returns false. In the case that it goes through
     * all of the board, then it returns true, meaning that the two boards are identical.
     * @param b1 BoardOps Object
     * @param b2 BoardOps Object
     * @return Boolean value to determine whether the two boards are equal.
     */
    public boolean areTwoBoardsEqual(BoardOps b1, BoardOps b2) {
        for (int i = 0; i < b1.getBoardT().getBoardSize(); i++) {
            for (int j = 0; j < b1.getBoardT().getBoardSize(); j++) {
                int val1 = b1.getBoardT().getBoard().get(i).get(j).getValue();
                int val2 = b2.getBoardT().getBoard().get(i).get(j).getValue();
                if (!(val1 == val2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
