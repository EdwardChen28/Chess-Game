/**
 * Created by Edward on 2/10/2015.
 */

import java.awt.*;
import java.util.ArrayList;

public class Board{
    private static Color white = Color.WHITE;
    private static Color black = Color.BLACK;
    private static int rows;
    private static int columns;
    private static Square[][] board;
    private static ArrayList<Piece> whitePlayer = new ArrayList<Piece>();
    private static ArrayList<Piece> blackPlayer = new ArrayList<Piece>();
    public static Piece whiteKing;
    public static Piece blackKing;

    /**
     *  constructor: initialize square, set pieces, draw chess board
     * @param row
     * @param column
     */
    public Board( int row, int column){
        rows = row;
        columns = column;
        board = new Square[rows][columns];
        initSquare();
        setPieces();
    }

    /**
     *
     * @return the arrayList which contains all the pieces with player has on the board
     */
    public ArrayList<Piece> getWhitePlayer(){
        return whitePlayer;
    }

    /**
     *
     * @return the arrayList which contains all the pieces black player has on the board
     */
    public ArrayList<Piece> getBlackPlayer(){
        return blackPlayer;
    }

    /**
     *
     * @return 2D array of square which represent the board
     */
    public  Square[][] getBoard(){
        return board;
    }

    public int getWidth(){
        return columns;
    }

    public int getHeight(){
        return rows;
    }

    public Piece getWhiteKing() { return whiteKing;}

    public Piece getBlackKing() { return blackKing;}

    /**
     * initialize each square in the board with color, location
     */
    private static void initSquare(){
        Color colorSwitch;
        for( int i = 0; i < board.length; i++){
            colorSwitch = (i%2 == 0) ? white: black;
            for(int j = 0;j < board[i].length; j++){
                String name = ""+ (rows - i ) + (char)('a'+j); //each square has a name base on rows(number) and column(letter)
                board[i][j] = new Square(i,j,colorSwitch,name );
                colorSwitch = colorSwitch.equals(white) ? black : white;
            }
        }
    }


    /**
     * set all the pieces on board
     * rule for naming piece: if only one piece, name_color
     * if more than one pieces are the same, nameN_color where N is a number start from 1
     */
    private static void setPieces(){
        int j;
        // set up the pawn. pawn is type 0
        for( j = 0; j< columns; j++){
            String name = "pawn"+(j+1)+"_black"; // create name for the piece
            setPiecesHelper(board[1][j], Color.BLACK, name, 0);
        }
        for(j = 0; j < columns; j++){
            String name = "pawn"+(j+1)+"_white";
            setPiecesHelper(board[6][j], Color.WHITE, name, 0);
        }

        //set up rook. rook is type 1
        int number = 1;
        for(j = 0; j < columns; j+=7){
            String name = "rook"+number+"_black";
            setPiecesHelper(board[0][j],black,name, 1);
            name = "rook"+number+"_white";
            setPiecesHelper(board[7][j], white, name, 1);
            number++;
        }

        //set up knight knight is type 2
        number=1;
        for(j =1; j < columns; j+=5){
            String name = "knight"+number+"_black";
            setPiecesHelper(board[0][j], Color.BLACK, name, 2);
            name = "knight"+number+"_white";
            setPiecesHelper(board[7][j], Color.WHITE, name, 2);
            number++;
        }

        //set up bishop. bishop is type 3
        number = 1;
        for(j =2; j < columns; j+=3){
            String name = "bishop"+number+"_black";
            setPiecesHelper(board[0][j],black,name, 3);
            name = "bishop"+number+"_white";
            setPiecesHelper(board[7][j],white,name, 3);
            number++;
        }

        // queen. type 4
        setPiecesHelper(board[0][3], black, "queen_black",4);
        setPiecesHelper(board[7][3], white, "queen_white",4);
        //king type 5
        setPiecesHelper(board[0][4], Color.BLACK, "king_black",5);
        setPiecesHelper(board[7][4], Color.WHITE, "king_white",5);
    }

 /*   /**
     *  check if the king is in check after every movement
     * @return true if the king is in check
     */
/*   public boolean inCheck(){
       if( inCheck_helper(this,whiteKing)== null && inCheck_helper(this, blackKing)== null){
           return false;
       }else{
           return true;
       }

    }*/
/*    /**
     *  check if the game is end. when it's your turn and your king can not move, loose
     *  this should check right after turn switch
     *  method: get a list of all possible move of the king, remove the move from the king's possible move
     *  if the opponent's pieces also has that possible move. at the end, if the king's list is empty, game over
     *
     * @param
     * @return turn if checkmate
     */
  /*  public boolean checkMate(){
        Piece king = whiteKing;
        Piece checkingPiece = inCheck_helper(this, whiteKing);
        if(checkingPiece == null){ checkingPiece = inCheck_helper(this, blackKing); king = blackKing; }
        if(checkingPiece == null){ return false;  }

        if(allMoveInCheck(king, this) && noPieceCanHelp(king, this, checkingPiece)) {
            return true;
        }
        return false;
    }
*/
/*    /**
     * check whether all the moves of the king has being checking by opponent
     * if yes, the king have not move can make to escape
     * @param king
     * @param b  the board
     * @return    true if all moves are being check
     */
   /* private static boolean allMoveInCheck(Piece king, Board b){
        ArrayList<Square> kingMoves = king.potential_moves(b);
        ArrayList<Piece> opponentPiece = king_opponent(king);
        for(int i = 0; i < opponentPiece.size(); i++){
            ArrayList<Square> currPieceMoves = opponentPiece.get(i).potential_moves(b);
            for(int j = 0; j < currPieceMoves.size(); j++){
                if(kingMoves.contains(currPieceMoves.get(j))){
                    kingMoves.remove(currPieceMoves.get(j));
                }
            }
        }
        return kingMoves.isEmpty()? true:false;
    }
*/
/*    /**
     *  check if any piece in the being checking side and block
     *  the path to checking its king
     * @param king
     * @param b  board
     * @param opponent  the piece is checking the king
     * @return true if no way to block the path
     */
  /*  private static boolean noPieceCanHelp(Piece king, Board b, Piece opponent){
        if(isKnight(opponent)){
            // capture the knight isCheck_help returns the piece checking the knight, null otherwise
            if(inCheck_helper(b, opponent) != null) { return false; }
            else { return true;}
        }
        // not a knight, check if capture it
        if(inCheck_helper(b,opponent) != null ) { return false;}
//******************check if  the opponent is pawn************************************
        String name = opponent.getName();
        name = name.substring(0,4);
        if(name.equals("pawn")){ // pawn, if left and right pieces belong to king side, see if they can move
            Square left = null, right = null ;
            if(king.getCurrentSquare().getY()-1 >= 0 ){
                left = board[king.getCurrentSquare().getX()][king.getCurrentSquare().getY()-1];
            }
            if(king.getCurrentSquare().getY()+1 <columns ){
                right = board[king.getCurrentSquare().getX()][king.getCurrentSquare().getY()+1];
            }
            //if it's my own color and can move, void in check
            if(  (left.getPiece().getColor().equals(king.getColor()) && !left.getPiece().potential_moves(b).isEmpty()) ||
                    (right.getPiece().getColor().equals(king.getColor()) && !right.getPiece().potential_moves(b).isEmpty()) ){
                return false;
            }
        }
        //********************function canPathToKingBlock *********************************************
        // check if its path to king can be block
        ArrayList<Square> pathToKing = getToKingPath(king, opponent);
        ArrayList<Piece> myPiece;
        if( king.getColor().equals(Color.BLACK) ){ myPiece = blackPlayer;}
        else{ myPiece = whitePlayer;}
        for( int i = 0; i < myPiece.size(); i++){
            ArrayList<Square> moves = myPiece.get(i).potential_moves(b);
            for( int j = 0 ; j < moves.size(); j++){
                if(pathToKing.contains(moves.get(j))){
                    return false; // can be block
                }
            }
        }
        return true;
    }
*/
 /*   /**
     * check through the possible moves of opponent's pieces,
     * if any of move can move to the king's square, it is in check
     * @param b board
     * @param king the king to check
     * @return the piece that is checking the king
     * */
  /*  private static Piece inCheck_helper(Board b, Piece king){
        Square kingPosition = king.getCurrentSquare();
        ArrayList<Piece> opponentPiece = king_opponent(king);
        for( int i = 0 ; i < opponentPiece.size(); i++){
            Piece currP = opponentPiece.get(i);
            ArrayList<Square> possible_more = currP.potential_moves(b);
            for(int j = 0 ; j < possible_more.size(); j++){
                if(kingPosition.isSameSquare(possible_more.get(j))){
                    return currP;
                }
            }
        }
        return null;
    }

*/
    // 8 directions: N 2, S 6, W 4, E 0, NE 1, NW 3, SE 7, SW 5
    private static ArrayList<Square> getToKingPath(Piece king, Piece p){
        ArrayList<Square> return_value = null;
        int x = king.getCurrentSquare().getX();  // row
        int y = king .getCurrentSquare().getY(); // column
        int xx = p.getCurrentSquare().getX(); // row
        int yy = p.getCurrentSquare().getY(); // column

        // N and S
        if(y == yy){
            if(x > xx){  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 2); }  // N
            else{ return_value = pathBetweenKingAndPiece( x,  y,  xx,  yy, 6); } // S
        }
        // W and E
        else if ( x == xx){
            if(y > yy){  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 4);  } // W
            else{ return_value = pathBetweenKingAndPiece( x, y, xx, yy, 0); }// E
        }
        // SW and SE
        else if (xx > x ){
            if( y > yy){   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 5);  }//sw
            else{   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 7);   }// SE
        }
        // NW and SW
        else if( xx < x){
            if(y>yy){   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 3);}// NW
            else{  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 1);}//NE
        }
        return return_value;

    }

    /**
     *
     * @param x  king's row number
     * @param y  king's column number
     * @param xx  opponent's row number
     * @param yy  opponent's column number
     * @param direction  8 directions: N 2, S 6, W 4, E 0, NE 1, NW 3, SE 7, SW 5
     * @return the list of squares that the opponent can move directly to king
     */
    private static ArrayList<Square> pathBetweenKingAndPiece(int x, int y, int xx, int yy, int direction){
        ArrayList<Square> return_value  = new ArrayList<Square>();
        int startX, startY, endX, endY;
        if(direction == 2 || direction == 6){ // y == yy
            if( xx < x ){  startX = xx; endX =x; }// N
            else{ startX = x; endX =xx; } //S
            for(int i = startX+1 ; i <endX ; i++){
                return_value.add(board[i][y]);
            }
        }
        else if(direction == 0 || direction ==4){ // x == xx
            if( yy < y){startY = yy; endY = y;} // W
            else{ startY = y; endY = yy;} // E
            for(int i = startY+1; i < endY; i++){
                return_value.add(board[x][i]);
            }
        }
        else if(direction == 5 ||direction== 7){  // xx > x
            if( yy < y) { //SW
                for (int i = x + 1, j = y - 1; i < xx && j > yy; i++, j--) {
                    return_value.add(board[i][j]);
                }
            }else{ // SE
                for (int i = x + 1, j = y + 1; i < xx && j < yy; i++, j++) {
                    return_value.add(board[i][j]);
                }
            }
        }
        else if( direction ==3 || direction ==1){ // xx < x
            if( yy < y) { //NW
                for (int i = x - 1, j = y - 1; i > xx && j > yy; i--, j--) {
                    return_value.add(board[i][j]);
                }
            }else{ // NE
                for (int i = x - 1, j = y + 1; i > xx && j < yy; i--, j++) {
                    return_value.add(board[i][j]);
                }
            }
        }
        return return_value;
    }

    /**
     * check if the piece who is checking the king is knight
     * if yes, no way to block it. capture only
     * @param p   piece that is checking the king
     * @return    true if it's a knight
     */
    private static boolean isKnight(Piece p){
        if(p.getColor().equals(Color.BLACK)){
            if(p.getName().equals("knight1_black") || p.getName().equals("knight2_black")){
                return true;
            }
        }else{
            if(p.getName().equals("knight1_white") || p.getName().equals("knight2_white")){
                return true;
            }

        }
        return false;
    }
    /**
     * create a new piece in the specific square
     * @param b square that piece will sit
     * @param color color for the piece
     * @param name name for the piece
     * @param type pawn:0; rook:1; knight:2 bishop:3 queen:4 king:5
     */
    private static void setPiecesHelper(Square b, Color color , String name, int type){
        Piece p = null;
        switch (type) {
            case 0:
                p = new Pawn(color, b, name);
                break;
            case 1:
                p = new Rook(color, b, name);
                break;
            case 2:
                p = new Knight(color, b, name);
                break;
            case 3:
                p = new Bishop(color, b, name);
                break;
            case 4:
                p = new Queen(color, b, name);
                break;
            case 5:
                p = new King(color, b, name);
                break;
        }
        if( p != null) { b.setPiece(p);}
        if(color.equals(Color.WHITE)){
            if(p != null ) {whitePlayer.add(p);}
            if(type ==  5) { whiteKing = p; }
        }else{
            if(p != null){blackPlayer.add(p);}
            if(type == 5) { blackKing = p;}
        }
    }

    /**
     * helper function
     * @param king
     * @return a list of pieces of the opponent
     */
    private static ArrayList<Piece> king_opponent(Piece king){
        if(king.getColor().equals(Color.WHITE)){ return blackPlayer;}
        else { return whitePlayer;}

    }




}
