
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
public class PlayGame {

    private Board board;
    private Ship[] ships;

    public PlayGame() {
        //create 10 x 10 board
        this.board = new Board(10, 10);

        //collision detection of ships
        boolean[][] grid = new boolean[10][10];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = false;
            }
        }
        this.ships = new Ship[2];
        for (int i = 0; i < this.ships.length; i++) {
            int shipSize = (int) (Math.random() * 4 + 2);
            //boolean to control ship generation
            boolean success = false;
            while (!success) {
                int direction = (int) (Math.random() * 2);
                //generate row and number
                int startRow = (int) (Math.random() * 10);
                int startCol = (int) (Math.random() * 10);
                //make sure it fits right left
                if (direction == 0 && startCol + shipSize < 10) {
                    //check it doesnt hit another ship
                    boolean allGood = true;
                    for (int j = 0; j < shipSize; j++) {
                        int row = startRow;
                        int col = startCol + j;
                        if (grid[row][col]) {
                            allGood = false;
                            break;
                        }
                    }
                    if (allGood) {
                        Coordinate[] spots = new Coordinate[shipSize];
                        for (int j = 0; j < shipSize; j++) {
                            if (direction == 0) {
                                //determine row and col
                                int row = startRow;
                                int col = startCol;
                                //flag the spot as used
                                grid[row][col] = true;
                                //set the coordinate of the ship
                                Coordinate c = new Coordinate(row, col);
                                spots[j] = c;
                            }
                        }
                        //make ship
                        Ship s = new Ship(spots);
                        //put ship into ship array 
                        this.ships[i] = s;
                        success = true;
                    }
                } else if (direction == 0 && startCol + shipSize < 10) {
                    //check it doesnt hit another ship
                    boolean allGood = true;
                    for (int j = 0; j < shipSize; j++) {
                        int row = startRow + j;
                        int col = startCol;
                        if (grid[row][col]) {
                            allGood = false;
                            break;
                        }
                    }
                    if (allGood) {
                        Coordinate[] spots = new Coordinate[shipSize];
                        for (int j = 0; j < shipSize; j++) {
                            if (direction == 1) {
                                //determine row and col
                                int row = startRow + j;
                                int col = startCol;
                                //flag the spot as used
                                grid[row][col] = true;
                                //set the coordinate of the ship
                                Coordinate c = new Coordinate(row, col);
                                spots[j] = c;
                            }
                        }
                        //make ship
                        Ship s = new Ship(spots);
                        //put ship into ship array 
                        this.ships[i] = s;
                        success = true;
                    }
                }
            }
        }
    }

    public void playGame() {
        boolean win = false;
        while (!win) {
            //get a guess
            Coordinate click = board.getClick();
            int shotRow = click.getRow();
            int shotCol = click.getCol();
            //see if it hit a boat
            //for each ship in the ships array
            boolean hit = false;
            for (Ship s : this.ships) {
                //does the shot hit boat s
                if (s.doesHit(shotRow, shotCol)) {
                    //register a hit
                    s.shoot(shotRow, shotCol);
                    hit = true;
                }
            }
            if (hit) {
                board.putPeg(Color.RED, shotRow, shotCol);
            } else {
                board.putPeg(Color.WHITE, shotRow, shotCol);
            }
            int shipSunk = 0;
            for (Ship s : this.ships) {
                if (s.isSunk()) {
                    shipSunk++;
                }
            }
            if (shipSunk == this.ships.length) {
                win = true;
                board.displayMessage("All ships sank.");
            }
        }
    }
}
