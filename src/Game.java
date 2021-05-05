/**
 * Author: Ayed Naber
 * Revised: April 11th, 2021
 *
 * Description: A module which runs the game, and takes input from the user.
 */

package src;
import java.util.Scanner;

/**
* @brief A module responsible for taking input and displaying output to the user.
* @details This module takes in input from the user through the keyboard, and
* on the user input, it performs certain operations on the board. Also, any output
* is also handled in this function and that is by using standard output in Java.
*/
public class Game {
    /**
    * @brief This is the main function, and it is where the whole game runs.
    * @details It starts off by displaying a welcome message, and asks the user
    * to input the size of the board. It then proceeds to build this board and
    * initialize it. It then goes into a while loop, and keeps asking the user
    * for an input to represent a move on the game board, and when it reaches
    * a point where the user wins or loses, then it will exit this loop and 
    * the program ends.
    */
    public static void main(String[] args) {
        System.out.println("Welcome to 2048!");
        System.out.println("To play, enter 'w', 'a', 's, or 'd' to move tiles around the board");
        System.out.println("If you enter 'new game' at any time, the game will restart. \n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of your board: ");
        String boardSize = sc.nextLine();

        BoardT boardT = new BoardT(Integer.parseInt(boardSize));
        boardT.init1();
        boardT.init2();

        BoardOps boardOps = new BoardOps(boardT);
        System.out.println("Score: " + boardT.getScore());
        boardT.showBoard();

        String move = sc.nextLine();

        while (!boardOps.getBoardT().isGameOver()) {
            boolean validInput = true;

            System.out.println("Enter your input to move the board!");
            if (move.equals("d")) {
                boardOps.merge_right();
            } else if (move.equals("w")) {
                boardOps.merge_up();
            } else if (move.equals("a")) {
                boardOps.merge_left();
            } else if (move.equals("s")) {
                boardOps.merge_down();
            } else {
                validInput = false;
            }

            if (!validInput) {
                if (move.equals("newgame")) {
                    boardOps.newGame();
                    System.out.println("Score: " + boardOps.getBoardT().getScore());
                    boardOps.getBoardT().showBoard();
                } else if (move.equals("endgame")) {
                    System.out.println("You ended the game with a score of: " + boardOps.getBoardT().getScore());
                    boardT.setGameOver(true);
                    break;
                } else {
                    System.out.println("Invalid Input!");
                }
            } else {
                if (boardOps.isWon()) {
                    boardT.showBoard();
                    System.out.println("Congratulations! You Won");
                    System.out.println("You Finished the game with a score of: " + boardT.getScore());
                    boardT.setGameOver(true);
                    break;
                } else {
                    boardOps.addNewValue();
                    System.out.println("Score: " + boardT.getScore());
                    boardOps.getBoardT().showBoard();
                }
                if (boardOps.cannotMove()) {
                    System.out.println("You Lost! No more moves can be made.");
                    System.out.println("You Finished the game with a score of: " + boardT.getScore());
                    boardT.setGameOver(true);
                    break;
                }
            }
            move = sc.nextLine();
        }

    }



}







