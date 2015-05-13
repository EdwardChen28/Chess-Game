import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/19/2015.
 */
public class Ironman extends Piece {
    /**
     * Constructor to build a Rook piece with color and square position
     * @param color
     * @param newSquare
     */
    public Ironman(Color color, Square newSquare, String name) {
        super(color, newSquare, name);
    }

    /**
     * Rook can move horizontally, vertically by two squares
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
        int i;
        // vertical check in the row
        for( i=currX+1; i < height; i+=2){ //check the down side in the row
            if( !square[i][currY].isOccupied()){ possibleMove.add(square[i][currY]); }
            else{
                if(!this.color.equals(square[i][currY].getPiece().getColor())) {
                    possibleMove.add(square[i][currY]);  //capture the piece and take its square
                }
                break;
            }
        }
        for(i = currX-1; i>=0; i-=2){ //check the up side in the row
            if( !square[i][currY].isOccupied()){ possibleMove.add(square[i][currY]); }
            else{
                if(!this.color.equals(square[i][currY].getPiece().getColor())) {
                    possibleMove.add(square[i][currY]);
                }
                break;
            }
        }
        // horizontally check in the column
        for( i=currY+1; i < width; i+=2){ //check the right side in the column
            if( !square[currX][i].isOccupied()){ possibleMove.add(square[currX][i]); }
            else{
                if(!this.color.equals(square[currX][i].getPiece().getColor())) {
                    possibleMove.add(square[currX][i]);
                }
                break;
            }
        }
        for(i = currY-1; i>=0; i-=2){ //check the down side in the column
            if( !square[currX][i].isOccupied()){ possibleMove.add(square[currX][i]); }
            else{
                if(!this.color.equals(square[currX][i].getPiece().getColor())) {
                    possibleMove.add(square[currX][i]);
                }
                break;
            }
        }

        return possibleMove;
    }


}
