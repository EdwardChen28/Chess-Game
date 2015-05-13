import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Edward on 2/10/2015.
 */
public abstract class Piece {
    protected Square currentSquare;
    protected Color color;
    protected  String name;

    /**
     * constructor
     * @param col
     * @param newSquare
     * @param pieceName
     */
    public Piece(Color col, Square newSquare, String pieceName){
        color = col;
        name = pieceName;
        currentSquare=newSquare;
    }
    public Color getColor(){
        return color;
    }
    public String getName(){
        return name;
    }
    public void setSquare(Square square){
        currentSquare = square;
    }
    public Square getCurrentSquare(){
        return currentSquare;
    }

    /**
     *  compare two pieces
     * @param otherPiece
     * @return true if they are the same
     */
    public boolean isSame(Piece otherPiece){
        return this.currentSquare.isSameSquare(otherPiece.currentSquare);
    }




    /**
     * move to a new square, if the new square is occupied by opponent
     * capture it and take the place
     * @param board
     * @param square
     * @return true if king is successful move to the new square
     */
 /*   public boolean move_to(Board board, Square square){
        Piece piece_in_square = square.getPiece();
        if(move_availability(board,square)){
            if( !square.isOccupied()){
                square.setPiece(this);          //move to the new square
                this.currentSquare.setPiece(null);  //reset the old square
                return true;
            }
            //if the square is occupied by opponent, capture it
            else if(square.isOccupied() && !piece_in_square.getColor().equals(this.getColor()) ){
                if( this.color.equals(Color.WHITE)){ //opponent is black
                    board.getBlackPlayer().remove(piece_in_square);
                }
                else if (this.color.equals(color.BLACK) ){ // opponent is white
                    board.getWhitePlayer().remove(piece_in_square);
                }
                square.setPiece(this);          //move to the new square
                this.currentSquare.setPiece(null);  //reset the old square to NULL
                return true;
            }

        }
        return false;
    }
*/

    /**
     * Check if the square is eligible to move
     * @param board
     * @param square
     * @return true is the given square is available to move to
     */
/*    public boolean move_availability(Board board,Square square){
        ArrayList<Square> allPossibleMoves = potential_moves(board);
        for(int i = 0; i < allPossibleMoves.size(); i++){
            if(square.isSameSquare(allPossibleMoves.get(i))){
                return true;
            }
        }
        return false;
    }
*/
    /**
     *  find all the possible moves of the piece in the board
     * @param board
     * @return array list of all the possible moves
     */
    public abstract ArrayList<Square> potential_moves( Board board);


}
