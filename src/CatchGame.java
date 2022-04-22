
/** This class manages the interactions between the different pieces of
 *  the game: the Board, the Daleks, and the Doctor. It determines when
 *  the game is over and whether the Doctor won or lost.
 */
import java.awt.Color;

public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private boolean end = false;
    private boolean docCollision = false;
    private Board gameBoard = new Board(12, 12);
    private Doctor doctor;
    private Dalek[] dalek;
    private int collapse = 0;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        this.dalek = new Dalek[3];
        for (int i = 0; i < 3; i++) {
            int col = (int) (Math.random() * (11 - 1 + 1) + 1);
            int row = (int) (Math.random() * (11 - 1 + 1) + 1);
            this.dalek[i] = new Dalek(row, col);
        }
        int rowD = (int) (Math.random() * (11 - 1 + 1) + 1);
        int colD = (int) (Math.random() * (11 - 1 + 1) + 1);
        this.doctor = new Doctor(rowD, colD);
        gameBoard.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());
        for (int i = 0; i < 3; i++) {
            gameBoard.putPeg(Color.BLACK, dalek[i].getRow(), dalek[i].getCol());
        }
        if ((dalek[0].getRow() == dalek[1].getRow() && dalek[0].getCol() == dalek[1].getCol()) || (dalek[0].getRow() == dalek[2].getRow() && dalek[0].getCol() == dalek[2].getCol())) {
            dalek[0].crash();
            gameBoard.removePeg(dalek[0].getRow(), dalek[0].getCol());
            gameBoard.putPeg(Color.RED, dalek[0].getRow(), dalek[0].getCol());
            collapse++;
        }
        if ((dalek[0].getRow() == dalek[1].getRow() && dalek[0].getCol() == dalek[1].getCol()) || (dalek[1].getRow() == dalek[2].getRow() && dalek[1].getCol() == dalek[2].getCol())) {
            dalek[1].crash();
            gameBoard.removePeg(dalek[1].getRow(), dalek[1].getCol());
            gameBoard.putPeg(Color.RED, dalek[1].getRow(), dalek[1].getCol());
            collapse++;
        }
        if ((dalek[2].getRow() == dalek[1].getRow() && dalek[2].getCol() == dalek[1].getCol()) || (dalek[0].getRow() == dalek[2].getRow() && dalek[0].getCol() == dalek[2].getCol())) {
            dalek[2].crash();
            gameBoard.removePeg(dalek[2].getRow(), dalek[2].getCol());
            gameBoard.putPeg(Color.RED, dalek[2].getRow(), dalek[2].getCol());
            collapse++;
        }
    }
        /**
         * The playGame method begins and controls a game: deals with when the
         * user selects a square, when the Daleks move, when the game is
         * won/lost.
         */

    public void playGame() {
        while (!end) {
            gameBoard.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());
            Coordinate click = gameBoard.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            gameBoard.removePeg(doctor.getRow(), doctor.getCol());
            doctor.move(clickRow, clickCol);
            for (int i = 0; i < 3; i++) {
                if (!dalek[i].hasCrashed) {
                    gameBoard.removePeg(dalek[i].getRow(), dalek[i].getCol());
                    dalek[i].advanceTowards(doctor);
                    gameBoard.putPeg(Color.BLACK, dalek[i].getRow(), dalek[i].getCol());
                }
                if (dalek[i].getRow() == doctor.getRow() && dalek[i].getCol() == doctor.getCol()) {
                    docCollision = true;
                }
            }
            if ((dalek[0].getRow() == dalek[1].getRow() && dalek[0].getCol() == dalek[1].getCol()) || (dalek[0].getRow() == dalek[2].getRow() && dalek[0].getCol() == dalek[2].getCol())) {
                dalek[0].crash();
                gameBoard.removePeg(dalek[0].getRow(), dalek[0].getCol());
                gameBoard.putPeg(Color.RED, dalek[0].getRow(), dalek[0].getCol());
                collapse++;
            }
            if ((dalek[0].getRow() == dalek[1].getRow() && dalek[0].getCol() == dalek[1].getCol()) || (dalek[1].getRow() == dalek[2].getRow() && dalek[1].getCol() == dalek[2].getCol())) {
                dalek[1].crash();
                gameBoard.removePeg(dalek[1].getRow(), dalek[1].getCol());
                gameBoard.putPeg(Color.RED, dalek[1].getRow(), dalek[1].getCol());
                collapse++;
            }
            if ((dalek[2].getRow() == dalek[1].getRow() && dalek[2].getCol() == dalek[1].getCol()) || (dalek[0].getRow() == dalek[2].getRow() && dalek[0].getCol() == dalek[2].getCol())) {
                dalek[2].crash();
                gameBoard.removePeg(dalek[2].getRow(), dalek[2].getCol());
                gameBoard.putPeg(Color.RED, dalek[2].getRow(), dalek[2].getCol());
                collapse++;
            }
            if (this.docCollision || collapse == 3) {
                end = true;
                if (this.docCollision) {
                    gameBoard.displayMessage("You have been captured!");
                    for (int i = 0; i < 3; i++) {
                        gameBoard.removePeg(dalek[i].getRow(), dalek[i].getCol());
                    }
                    gameBoard.removePeg(doctor.getRow(), doctor.getCol());
                    gameBoard.putPeg(Color.YELLOW, doctor.getRow(), doctor.getCol());
                }
                if (collapse == 3) {
                    gameBoard.displayMessage("You have killed the daleks hooray!!!");
                    for (int i = 0; i < 3; i++) {
                        gameBoard.removePeg(dalek[i].getRow(), dalek[i].getCol());
                    }
                    gameBoard.putPeg(Color.PINK, doctor.getRow(), doctor.getCol());
                }
            }
            collapse = 0;
        }
    }
}
