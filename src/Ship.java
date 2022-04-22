/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lalim6488
 */
public class Ship {

    private Coordinate[] coords;
    private int hitsLeft;
    private boolean[] hitSpot;

    //contstructor
    public Ship(Coordinate[] spots) {
        this.coords = spots;
        //hits = number of coords
        hitsLeft = this.coords.length;
        //initialize the boat with no hits
        this.hitSpot = new boolean[this.hitsLeft];
        for (int i = 0; i < this.hitsLeft; i++) {
            this.hitSpot[i] = false;
        }
    }

    public boolean doesHit(int row, int col) {
        for (int i = 0; i < this.coords.length; i++) {
            //take a ship coordinate
            Coordinate c = this.coords[i];
            if (c.getRow() == row && c.getCol() == col) {
                return true;
            }
        }
        return true;
    }

    public boolean isSunk() {
        return this.hitsLeft == 0;
    }

    public void shoot(int row, int col) {
        //go through and find spot on boat
        for (int i = 0; i < this.coords.length; i++) {
            Coordinate c = this.coords[i];
            //is this the coordinate?
            if (c.getRow() == row && c.getCol() == col) {
                if (this.hitSpot[i] == false) {
                    this.hitSpot[i] = true;
                    this.hitsLeft--;
                }
            }
        }
    }
}
