import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/11/2015.
 */
public class Knight extends Piece {
    /**
     * Constructor to build a knight piece with color and square position
     * @param color
     * @param newSquare
     */
    public Knight(Color color, Square newSquare, String name) {
        super(color, newSquare, name);
    }

    /**
     * Knight can move as " L " shape
     * find all the possible square(not occupied) the Knight can move
     * if there is a square on any of the possible square is occupied
     * this square is also added to the Arraylist because Knight can capture
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
       // down
        if(currX+2 <height){
            if( currY+1 < width){
                // if the square is not occupied or is occupied by opponent, it is possible to take the move
                if (!square[currX+2][currY+1].isOccupied() ||
                        (square[currX+2][currY+1].isOccupied() && !this.getColor().equals(square[currX+2][currY+1].getPiece().getColor())) )
                {
                    possibleMove.add(square[currX + 2][currY + 1]);
                }
            }
            if( currY-1 >= 0 ){
                // if the square is not occupied or is occupied by opponent, it is possible to take the move
                if (!square[currX+2][currY-1].isOccupied() ||
                        (square[currX+2][currY-1].isOccupied() && !this.getColor().equals(square[currX+2][currY-1].getPiece().getColor())) )
                {
                    possibleMove.add(square[currX + 2][currY - 1]);
                }
            }
        }

        // up
        if(currX-2 >= 0){
            if( currY+1 < width){
                // if the square is not occupied or is occupied by opponent, it is possible to take the move
                if (!square[currX-2][currY+1].isOccupied() ||
                        (square[currX-2][currY+1].isOccupied() && !this.getColor().equals(square[currX-2][currY+1].getPiece().getColor())) ) {
                    possibleMove.add(square[currX - 2][currY + 1]);
                }
            }

            if( currY-1 >= 0 ){
                if (!square[currX-2][currY-1].isOccupied() ||
                        (square[currX-2][currY-1].isOccupied() && !this.getColor().equals(square[currX-2][currY-1].getPiece().getColor())) ) {
                    possibleMove.add(square[currX - 2][currY - 1]);
                }
            }
        }

        // right
        if(currY + 2 < width){
            if( currX+1 < height){
                if (!square[currX+1][currY+2].isOccupied() ||
                        (square[currX+1][currY+2].isOccupied() && !this.getColor().equals(square[currX+1][currY+2].getPiece().getColor())) ) {
                    possibleMove.add(square[currX + 1][currY + 2]);
                }
            }
            if( currX-1 >= 0 ){
                if (!square[currX-1][currY+2].isOccupied() ||
                        (square[currX-1][currY+2].isOccupied() && !this.getColor().equals(square[currX-1][currY+2].getPiece().getColor())) ) {
                    possibleMove.add(square[currX - 1][currY + 2]);
                }
            }
        }

        // left
        if(currY - 2 >= 0){
            if( currX+1 < height){
                if (!square[currX+1][currY-2].isOccupied() ||
                        (square[currX+1][currY-2].isOccupied() && !this.getColor().equals(square[currX+1][currY-2].getPiece().getColor())) ) {
                    possibleMove.add(square[currX + 1][currY - 2]);
                }
            }
            if( currX-1 >= 0 ){
                if (!square[currX-1][currY-2].isOccupied() ||
                        (square[currX-1][currY-2].isOccupied() && !this.getColor().equals(square[currX-1][currY-2].getPiece().getColor())) ) {
                    possibleMove.add(square[currX - 1][currY - 2]);
                }
            }
        }


        return possibleMove;
    }
}
