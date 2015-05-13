import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/11/2015.
 */
public class Pawn extends Piece {

    /**
     * Constructor to build a Pawn piece with color and square position
     * @param color
     * @param newSquare
     */
    public Pawn(Color color, Square newSquare, String name ) {
        super(color, newSquare, name);
    }

    /**
     * Pawn can move horizontally, vertically, and diagonally
     * find all the possible square(not occupied) the Queen can move
     * if there is a square on the each of the three paths is occupied
     * this square is also added to the Arraylist because Queen can capture
     * it and take its place
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

        // white color pieces place on the bottom of the board
        if( this.getColor().equals(Color.WHITE)){

                if( currX-1 >=0 && !square[currX-1][currY].isOccupied()) {
                    possibleMove.add(square[currX-1][currY]);
                    if (this.getCurrentSquare().getX() == 6 && !square[currX-2][currY].isOccupied()) { // if at the original position, can move two square
                        possibleMove.add(square[currX-2][currY]);
                    }
                }
                else if ( currX-1 >=0 && square[currX-1][currY].isOccupied()){
                    if(currY-1 >=0 ) { possibleMove.add(square[currX-1][currY-1]);}
                    if(currY+1 < width) { possibleMove.add(square[currX-1][currY+1]);}
                }

        }
        // black color pieces place at the top of the board
        else{

            if( currX+1 < height && !square[currX+1][currY].isOccupied()) {
                possibleMove.add(square[currX+1][currY]);
                if (this.getCurrentSquare().getX() == 1 && !square[currX+2][currY].isOccupied()) {
                    possibleMove.add(square[currX+2][currY]);
                }
            }
            else if ( currX+1 < height && square[currX+1][currY].isOccupied()){
                if(currY-1 >=0 ) { possibleMove.add(square[currX+1][currY-1]);}
                if(currY+1 < width) { possibleMove.add(square[currX+1][currY+1]);}
            }

        }


        return possibleMove;
    }
}
