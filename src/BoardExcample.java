
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lalim6488
 */
public class BoardExcample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Board gameBoard = new Board(12, 12);
        //Put down peg
        gameBoard.putPeg(Color.RED, 5, 11);
        gameBoard.putPeg(Color.RED, 5, 2);
        //remove peg
        gameBoard.removePeg(5, 2);
        //diplay a message on board
        gameBoard.displayMessage("Click to place down peg.");
        while (true) {
            //get a click waits for click
            Coordinate click = gameBoard.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            //use click to place peg
            gameBoard.putPeg(Color.ORANGE, clickRow, clickCol);
        }
    }
}
