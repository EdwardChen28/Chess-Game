import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/11/2015.
 */
public class Bishop extends Piece {
    /**
     * Constructor, to build a Bishop piece with color and square position
     * @param color
     * @param newSquare
     */
    public Bishop(Color color, Square newSquare, String name) {
        super(color, newSquare, name);
    }

    /**
     * Bishop can move diagonally
     * find all the possible square(not occupied) the Bishop can move
     * if there is a square on the paths is occupied
     * this square is also added to the ArrayList because Bishop can capture
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
        int i;
        int j;
        //diagonal " \ "
        for ( i = currX+1, j =currY+1; i<height && j<width; i++,j++){         //right down corner
            if(!square[i][j].isOccupied()){    // square is available
                possibleMove.add(square[i][j]);
            }
            else{
                if( !this.color.equals(square[i][j].getPiece().getColor()) ) {  //square is occupied by opponent
                    possibleMove.add(square[i][j]);                          // capture and take this square
                }
                break;
            }
        }
        for ( i = currX-1, j =currY-1; i>=0 && j >=0; i--,j--){              //left top corner
            if(!square[i][j].isOccupied()){
                possibleMove.add(square[i][j]);
            }
            else{
                if( !this.color.equals(square[i][j].getPiece().getColor()) ) {
                possibleMove.add(square[i][j]);                              // capture and take this square
            }
                break;
            }
        }
        // diagonal " / "
        for ( i = currX+1, j =currY-1; i< height && j>=0; i++,j--){          //left down corner part
            if(!square[i][j].isOccupied()){
                possibleMove.add(square[i][j]);
            }
            else{
                if( !this.color.equals(square[i][j].getPiece().getColor()) ) {
                    possibleMove.add(square[i][j]);                        // capture and take this square
                }
                break;
            }
        }
        for ( i = currX-1, j =currY+1; i>=0 && j <width; i--,j++){           //right top corner
            if(!square[i][j].isOccupied()){
                possibleMove.add(square[i][j]);
            }
            else{
                if( !this.color.equals(square[i][j].getPiece().getColor()) ) {
                    possibleMove.add(square[i][j]);                        // capture and take this square
                }
                break;
            }
        }

        return possibleMove;
    }


}
