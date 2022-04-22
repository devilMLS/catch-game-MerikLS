
/** This class models the Doctor in the game. A Doctor has
 *  a position and can move to a new position.
 */
public class Doctor {

    private int row, col;

    /**
     * Initializes the variables for a Doctor.
     *
     * @param theRow The row this Doctor starts at.
     * @param theCol The column this Doctor starts at.
     */
       public Doctor(int theRow, int theCol) {
       row = theRow;
       col = theRow;
    }

    /**
     * Move the Doctor. If the player clicks on one of the squares immediately
     * surrounding the Doctor, the peg is moved to that location. Clicking on
     * the Doctor does not move the peg, but instead allows the Doctor to wait
     * in place for a turn. Clicking on any other square causes the Doctor to
     * teleport to a random square (perhaps by using a �sonic screwdriver�).
     * Teleportation is completely random.
     *
     * @param newRow The row the player clicked on.
     * @param newCol The column the player clicked on.
     */
    public void move(int newRow, int newCol) {
        
        if(this.row - newRow == 1 || this.row - newRow == -1){
            row = newRow;
        }
        if(this.col - newCol == 1|| this.col - newCol == -1){
            col = newCol;
        }
        if(this.row - newRow > 1 || this.row - newRow < -1 || this.col - newCol > 1 || this.col - newCol < -1){
            row = (int) (Math.random() * (11 - 1 + 1) + 1);
            col = (int) (Math.random() * (11 - 1 + 1) + 1);
        }
        if(this.col == newCol){
            col = newCol;
        }
        if(this.row == newRow){
            row = newRow;
        }
    }

    /**
     * Returns the row of this Doctor.
     *
     * @return This Doctor's row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of this Doctor.
     *
     * @return This Doctor's column.
     */
    public int getCol() {
        return col;
    }

}
