import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/19/2015.
 */
public class Superman extends Piece {
    /**
     * Constructor to build a Rook piece with color and square position
     * @param color
     * @param newSquare
     */
    public Superman(Color color, Square newSquare, String name) {
        super(color, newSquare, name);
    }

    /**
     * Superman can move horizontally, vertically by one square only
     * find all the possible square(not occupied) the Rook can move
     * if there is a square on the each of the two paths is occupied
     * this square is also added to the Arraylist because Rook can capture
     * it and take its place if it is opponent
     * @param board
     * @return an arrayList of all the possible move king can take
     */
    public ArrayList<Square> potential_moves( Board board){
        ArrayList<Square> possibleMove = new ArrayList<Square>(); // default size is 10
        int currX = currentSquare.getX();
        int currY = currentSquare.getY();
        int width = board.getWidth();
        int height = board.getHeight();
        Square[][] square = board.getBoard();
        if(currX + 1 < height){
            // if the square is not occupied or is occupied by opponent, it is possible to take the move
            if(!square[currX+1][currY].isOccupied() ||
                    (square[currX+1][currY].isOccupied() && ! this.getColor().equals(square[currX+1][currY].getPiece().getColor())) ) {
                possibleMove.add(square[currX + 1][currY]);
            }

        }
        if(currX - 1 >= 0){
            // if the square is not occupied or is occupied by opponent, it is possible to take the move
            if(!square[currX-1][currY].isOccupied() ||
                    (square[currX-1][currY].isOccupied() && ! this.getColor().equals(square[currX-1][currY].getPiece().getColor())) ) {
                possibleMove.add(square[currX - 1][currY]);
            }

        }
        if(currY - 1 > 0){
           // if the square is not occupied or is occupied by opponent, it is possible to take the move
           if(!square[currX-1][currY-1].isOccupied() ||
               (square[currX-1][currY-1].isOccupied() && ! this.getColor().equals(square[currX-1][currY-1].getPiece().getColor())) ) {
                    possibleMove.add(square[currX - 1][currY - 1]);
                }

        }
        if(currY+1 < width) {
            // if the square is not occupied or is occupied by opponent, it is possible to take the move
            if (!square[currX][currY+1].isOccupied() ||
                    (square[currX][currY+1].isOccupied() && !this.getColor().equals(square[currX][currY+1].getPiece().getColor()))) {
                possibleMove.add(square[currX][currY + 1]);
            }
        }


        return possibleMove;
    }
}
