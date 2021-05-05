/**
 * Author: Ayed Naber
 * Revised: April 7th, 2021
 *
 * Description: A class that represents the 2048 game board.
 */

package src;

import java.util.ArrayList;
import java.util.Random;

/**
* @brief An abstract data type (ADT) representing the 2048 game board.
* @details This ADT represents the game board, which is made up of tiles
* (TileT objects). It is assumed that the value entered for the boardSize
* will be greater than one, in order to have a functional game board.
*/
public class BoardT {
    private int boardSize;
    private int score;
    private boolean isGameOver;
    private ArrayList<ArrayList<TileT>> board = new ArrayList<ArrayList<TileT>>();
    private int INITIAL_VALUES = 2;

    /**
    * @brief BoardT Constructor
    * @details Initializes a BoardT object by setting the board size to the
    * the given integer argument as long as it is less than 4. If the board size
    * as an argument is less than 4, then an exception is raised.
    * Also, the isGameOver boolean variable is set
    * to false, as the game just started. This boolean variable will eventually
    * be updated to true when the user wins or loses, which indicates the end of
    * the game.
    * @param boardSize Integer value representing the size of the board.
    * @throws IllegalArgumentException
    */
    public BoardT(int boardSize) {
        if (boardSize < 4) {
            throw new IllegalArgumentException("Board Size cannot be less than 4!");
        }
        this.boardSize = boardSize;
        this.isGameOver = false;
    }

      /**
     * @brief Getter method for the Board's size.
     * @return Integer representing the board's size.
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * @brief Setter method for the Board's size.
     * @param boardSize Integer representing the updated board size.
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * @brief Getter method for the game score.
     * @return Integer representing the game score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @brief Setter method for the game score.
     * @param score Integer representing the updated game score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @brief Getter method for the boolean state variable isGameOver.
     * @return Returns true if the game is over, and false if the game is still going.
     */
    public boolean isGameOver() {
        return this.isGameOver;
    }

    /**
     * @brief Setter method for the boolean state variable isGameOver.
     * @param gameOver Boolean value to determine whether the game is over or not.
     */
    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    /**
     * @brief Getter method for the board.
     * @return The nested list representing the game board.
     */
    public ArrayList<ArrayList<TileT>> getBoard() {
        return this.board;
    }

    /**
     * @brief Setter method for the board.
     * @details This method is used to update the board.
     * @param board A nested list representing the updated game board.
     */
    public void setBoard(ArrayList<ArrayList<TileT>> board) {
        this.board = board;
    }

    /**
     * @brief This function prepares the board for two tiles to be inserted at random locations.
     * @details This function goes through the board, and adds tiles to all positions with values equal
     * to zero. This is important in order for the next init function to change the value of the tiles
     * as needed. It is assumed that this function will be called before init2. It is also assumed that
     * when a tile has a value of zero, then it represents an empty space on the board.
     */
    public void init1() {
        for (int i = 0; i < boardSize; i++) {
            ArrayList<TileT> row = new ArrayList<TileT>();
            for (int j = 0; j < boardSize; j++) {
                row.add(new TileT(0));
            }
            this.board.add(row);
        }
    }

    /**
     * @brief This function prepares the board for the beginning of the 2048 game.
     * @details For the 2048 game to start, there must be two tiles on the board.
     * Therefore, with implementing the nextInt function from the random library,
     * the init function selects two random rows and columns, and changes the value
     * of the tile corresponding to that row and column to a value which is 9 times
     * out of 10 going to be a 2, and 1 time out of 10 going to be a 4. After the init
     * function is called on the board, then this means that the board is ready for the game
     * to start.
     */
    public void init2() {
        while (INITIAL_VALUES > 0) {
            Random random = new Random();
            int rowVal = random.nextInt(boardSize - 1);
            int colVal = random.nextInt(boardSize - 1);

            if (this.board.get(rowVal).get(colVal).getValue() == 0) {
                this.board.get(rowVal).get(colVal).setValue(pickNewValue());
                INITIAL_VALUES -= 1;
            }
        }
        this.setScore(0);
    }

    public int pickNewValue() {
        Random random = new Random();
        int randomIndex = random.nextInt(10);
        int[] possibleVals = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
        return possibleVals[randomIndex];
    }

    /**
     * @brief This function prints out the current state of the 2048 game board.
     * @details This function uses the supporting findLargestValue function to
     * find the largest value on the board, and from it finds the number of characters.
     * This is important in order to know the number of spaces to place between each tile,
     * and allows it to be dynamic. After that, it goes through the tiles on the board,
     * and if the value of a tile is zero, then it does not show that value, but leaves
     * an empty space. In the case that the value is not a zero, then it prints out the tile's
     * value followed by a vertical bar. Once it goes through all of the tiles, then it is finished.
     */
    public void showBoard() {
        int numberOfSpaces = String.valueOf(findLargestValue()).length();

        for (ArrayList<TileT> row : this.board) {
            StringBuilder currentRow = new StringBuilder("|");
            for (TileT element : row) {
                if (element.getValue() == 0) {
                    currentRow.append(repeatNumOfSpaces(numberOfSpaces));
                    currentRow.append("|");
                } else {
                    int elemLength = String.valueOf(element.getValue()).length();
                    currentRow.append(repeatNumOfSpaces(numberOfSpaces - elemLength));
                    currentRow.append(element.getValue());
                    currentRow.append("|");
                }
            }
            System.out.println(currentRow);
        }
        System.out.println();
    }

    /**
     * @brief This function takes in a row, and merges it to the left, and will be the building block for
     * implementing all merging operations across the 2048 game board.
     * @details A row in this case is a list of Tile objects, where this row is taken in as input
     * by the function. The function first goes through the row, and if it finds a tile with value
     * zero, then it sets this tile's value to the value of the tile to the right of it, and makes the current
     * tile's value equal to zero. After that, it goes through that row again, where this time if the function finds
     * two tiles next to each other with the same value, then it sets the value of the left tile to its value multiplied
     * by 2, and the right tile's value to zero. The game score is incremented by the value of one of the tiles.
     * Lastly, the row is visited for one final time, and if it finds a tile with a value of zero, then it sets its
     * value to the value of the tile to the right of it, and sets the value of the right tile to zero. All of these
     * steps will successfully merge a row on the 2048 game board.
     * @param row List of TileT objects representing a row on the 2048 game board.
     */
    public void mergeRowToTheLeft(ArrayList<TileT> row) {
        for (int i = 0; i < this.boardSize - 1; i++) {
            for (int j = this.boardSize - 1; j > 0; j--) {
                if (row.get(j - 1).getValue() == 0) {
                    row.get(j - 1).setValue(row.get(j).getValue());
                    row.get(j).setValue(0);
                }
            }
        }

        for (int i = 0; i < this.boardSize - 1; i++) {
            if (row.get(i).getValue() == row.get(i + 1).getValue()) {
                row.get(i).setValue(row.get(i).getValue() * 2);
                this.score += (row.get(i).getValue());
                row.get(i + 1).setValue(0);
            }
        }

        for (int i = this.boardSize - 1; i > 0; i--) {
            if (row.get(i - 1).getValue() == 0) {
                row.get(i - 1).setValue(row.get(i).getValue());
                row.get(i).setValue(0);
            }
        }
    }

    /**
     * @brief This function finds the tile with the largest value on the board, and returns its value.
     * @return The value of the largest tile on the 2048 game board.
     */
    public int findLargestValue() {
        int largestValue = this.board.get(0).get(0).getValue();
        for (ArrayList<TileT> row : this.board) {
            for (TileT element : row) {
                if (element.getValue() > largestValue) {
                    largestValue = element.getValue();
                }
            }
        }
        return largestValue;
    }

    /**
     * @brief This function is a supporting function to the showBoard function.
     * @details Depending on the number of spaces specified in the showBoard function,
     * this function returns a string with the empty space repeated the same number
     * of times as the number of spaces integer specified as an argument to the function,
     * and which is passed in from the showBoard function.
     * @param numberOfSpaces Integer representing the number of spaces between each tile when printing the board.
     * @return String with the empty space repeated as much times as the number of spaces integer.
     */
    public String repeatNumOfSpaces(int numberOfSpaces) {
        return new String(new char[numberOfSpaces]).replace("\0", " ");
    }
}