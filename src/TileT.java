/**
 * Author: Ayed Naber
 * Revised: April 7th, 2021
 *
 * Description: A class that represents a tile on the 2048 game board.
 */

package src;

/**
 * @brief An abstract data type (ADT) representing a tile on the 2048
 * game board.
 * @details This class will be used in the BoardT class, where an object
 * of this instance will represent a tile on the board. Each Tile object
 * will have a integer value representing it. It is assumed that the value
 * of each tile will be initialized when the game is run.
 */
public class TileT {
    private int value;

    /**
    * @brief TileT Constructor
    * @details Initializes a Tile object by setting the Tile's value to the
    * given integer value. It is assumed that the value of the Tile entered
    * will be greater than zero, and will be a multiple of 2.
    * @param value Integer value to be set as the Tile's value.
    */
    public TileT(int value) {
        this.value = value;
    }

    /**
    * @brief A getter for the Tile's value
    * @return The integer representing the value of the tile.
    */
    public int getValue() {
        
        return this.value;
    }

    /**
    * @brief A setter for the Tile's value
    * @details This function is used to update the Tile's value.
    * @param value Integer value to update the Tile's value
    */ 
    public void setValue(int value) {
        this.value = value;
    }
}