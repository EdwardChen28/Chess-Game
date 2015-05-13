import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Edward on 3/4/2015.
 */

public class Controller {
    static int originalX = -1, originalY = -1;
    static int destinationX = -1, destinationY = -1;
    static boolean  inGame, selected = false;
    static int prevX, prevY;
    public static GUI graph;
    public static JButton[][] buttons;
    public static Board gameBoard;
    public static Square[][] squares;
    static boolean turn = true; // white turn = true; black turn = false

    public static void main(String[] args){
        new Controller();


    }
    public Controller(){
        gameInit();
        gameStart();
        buttonListenerSetup();
    }
    //handling player inputs
    public static void buttonListenerSetup(){
        graph.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == graph.restart) {
                    int response = JOptionPane.showConfirmDialog(graph, "A player is requesting to restart", "restart request", JOptionPane.YES_NO_OPTION );
                    if(response == 0){
                       graph.restore();
                    }
                } else if (e.getSource() == graph.undo) {
                    int response = JOptionPane.showConfirmDialog(graph, "A player is requesting to undo", "undo request", JOptionPane.YES_NO_OPTION );
                    if (response == 0){
                        updatePiece(destinationX,destinationY,prevX,prevY);
                    }
                } else {
                    ArrayList<Integer> position = getXY((JButton) e.getSource(), buttons);
                    if (originalY == -1 && originalY == -1) {
                        if(selectedOwnColor(turn, position)) {
                            originalX = position.get(0);
                            originalY = position.get(1);
                        }
                    } else {
                        destinationX = position.get(0);
                        destinationY = position.get(1);
                        selected = true;
                    }
                }
            }
        });
    }


    public static void gameInit(){
        graph = new GUI(8,8);
        gameBoard = new Board(8,8);
        squares = gameBoard.getBoard();
        buttons = graph.getSquare();

    }
    public void gameStart(){
        Thread gameThread = new Thread(){
            public void run(){
                gameLoop();
            }
        };
        gameThread.start();
    }

    private void gameLoop(){
        System.out.print("inside game loop");
        inGame = true;
        while(inGame) {

            while(!selected) {
                try{
                 Thread.sleep(200);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
                // wait for user input
                //check if the move is legal
         if(move_to(gameBoard, squares[originalX][originalY], squares[destinationX][destinationX] )){
                // check if it's checkmate
                if(checkMate(squares[destinationX][destinationX])){
                    inGame = false;
                    break;
                }
            }
            updatePiece(originalX, originalY, destinationX, destinationY);
            selected = false;
            turn = turn ? false : true;

        }
    }

    public static void updatePiece(int currX, int currY, int destX, int destY){
        if(currX==-1 || currY ==-1 || destX==-1 || destY==-1) return;
        buttons[destX][destY].setIcon(buttons[currX][currY].getIcon());
        buttons[currX][currY].setIcon(null);

        prevX = originalX; prevY = originalY;
        originalX = -1; originalY = -1;
    }

    public static ArrayList<Integer> getXY(JButton b, JButton[][] buttonSquare) {
        ArrayList<Integer> retval = new ArrayList<Integer>(2);
        for (int i = 0; i < buttonSquare.length; i++) {
            for (int j = 0; j < buttonSquare[i].length; j++) {
                if (buttonSquare[i][j] == b) {
                    retval.add(i);
                    retval.add(j);
                    return retval;
                }
            }
        }
        return null;
    }

    /**
     *  check whether the player select his own piece
     * @param color if color is true, white piece turn; otherwise, black player's turn
     * @param array the position that user has selected
     * @return return true if player selects his own color piece
     */

    public static boolean selectedOwnColor(boolean color, ArrayList<Integer> array){
        if (color){ // white player's turn
            if(squares[array.get(0)][array.get(1)].getPiece().getColor().equals(Color.WHITE))
                return true;
        }
        else{
            if(squares[array.get(0)][array.get(1)].getPiece().getColor().equals(Color.BLACK))
                return true;
        }
        return false;
    }


    /**
     * move to a new square, if the new square is occupied by opponent
     * capture it and take the place
     * @param board
     * @param square
     * @return true if king is successful move to the new square
     */
    public boolean move_to(Board board, Square currentSquare, Square square){
        Piece piece_in_square = square.getPiece();
        if(move_availability(board,square)){
            if( !square.isOccupied()){
                square.setPiece(piece_in_square);          //move to the new square
                currentSquare.setPiece(null);  //reset the old square
                return true;
            }
            //if the square is occupied by opponent, capture it
            else if(square.isOccupied() ){
                if( piece_in_square.getColor().equals(Color.WHITE)){ //opponent is black
                    board.getBlackPlayer().remove(piece_in_square);
                }
                else{ // opponent is white
                    board.getWhitePlayer().remove(piece_in_square);
                }
                square.setPiece(piece_in_square);          //move to the new square
                currentSquare.setPiece(null);  //reset the old square to NULL
                return true;
            }

        }
        return false;
    }

    /**
     * Check if the square is eligible to move
     * @param board
     * @param square
     * @return true is the given square is available to move to
     */
    public boolean move_availability(Board board,Square square){
        ArrayList<Square> allPossibleMoves = square.getPiece().potential_moves(board);
        for(int i = 0; i < allPossibleMoves.size(); i++){
            if(square.isSameSquare(allPossibleMoves.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     *  check if the game is end. when it's your turn and your king can not move, loose
     *  this should check right after turn switch
     *  method: get a list of all possible move of the king, remove the move from the king's possible move
     *  if the opponent's pieces also has that possible move. at the end, if the king's list is empty, game over
     *
     * @param
     * @return turn if checkmate
     */
    public boolean checkMate(Square square){
        Piece king;
        if(square.getPiece().getColor().equals(Color.WHITE)) { king = gameBoard.getBlackKing(); }
        else{ king = gameBoard.getWhiteKing(); }
        Piece checkingPiece = inCheck_helper(gameBoard, king);  // incheck_helper return a piece that is checking the king
        if(checkingPiece == null){ return false;  }

        if(allMoveInCheck(king, gameBoard) && noPieceCanHelp(king, gameBoard, checkingPiece)) {
            return true;
        }
        return false;
    }

    /**
     * check whether all the moves of the king has being checking by opponent
     * if yes, the king have not move can make to escape
     * @param king
     * @param b  the board
     * @return    true if all moves are being check
     */
    private static boolean allMoveInCheck(Piece king, Board b){
        ArrayList<Square> kingMoves = king.potential_moves(b);
        if(kingMoves.size() == 0){ return true; }
        // get the opponent player remaining pieces
        ArrayList<Piece> opponentPiece;
        if(king.getColor().equals(Color.BLACK)){ opponentPiece = b.getWhitePlayer(); }
        else opponentPiece = b.getBlackPlayer();
        //if the possible moves of king is incheck, remove from possible move list
        for(int i = 0; i < opponentPiece.size(); i++){
            ArrayList<Square> currPieceMoves = opponentPiece.get(i).potential_moves(b);
            for(int j = 0; j < currPieceMoves.size(); j++){
                if(kingMoves.contains(currPieceMoves.get(j))){
                    kingMoves.remove(currPieceMoves.get(j));
                }
            }
        }
        //if the king possible move list is empty, king has no wait to escape
        return kingMoves.isEmpty()? true:false;
    }

    /**
     *  check if any piece in the being checking side can block
     *  the path to checking its king
     * @param king
     * @param b  board
     * @param opponent  the piece is checking the king
     * @return true if no way to block the path
     */
    private static boolean noPieceCanHelp(Piece king, Board b, Piece opponent){
        if(isKnight(opponent)){   //knight can not be blocked, only captured
            // capture the knight isCheck_help returns the piece checking the knight, null otherwise
            if(inCheck_helper(b, opponent) != null) { return false; }
            else { return true;}
        }

        // not a knight, check if capture it
        if(inCheck_helper(b,opponent) != null ) { return false;}

        //check if  the opponent is pawn
        String name = opponent.getName();
        name = name.substring(0,4);
        if(name.equals("pawn")) {
            // only chance to escape if the front piece of the pawn is a knight of the being check player
            Piece frontPiece = b.getBoard()[opponent.getCurrentSquare().getX()-1][opponent.getCurrentSquare().getY()].getPiece();
            if(isKnight(frontPiece)) return false;
        }

        //function canPathToKingBlock

        // check if its path to king can be block
        ArrayList<Square> pathToKing = getToKingPath(king, opponent, b);
        ArrayList<Piece> myPiece;
        if( king.getColor().equals(Color.BLACK) ){ myPiece = b.getBlackPlayer();}
        else{ myPiece = b.getWhitePlayer();}
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

    /**
     * check through the possible moves of opponent's pieces,
     * if any of move can move to the king's square, it is in check
     * @param b board
     * @param king the king to check
     * @return the piece that is checking the king
     * */
    private static Piece inCheck_helper(Board b, Piece king){
        Square kingPosition = king.getCurrentSquare();
        ArrayList<Piece> opponentPiece;
        if(king.getColor().equals(Color.BLACK)){ opponentPiece = b.getWhitePlayer(); }
        else opponentPiece = b.getBlackPlayer();
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

    // 8 directions: N 2, S 6, W 4, E 0, NE 1, NW 3, SE 7, SW 5
    private static ArrayList<Square> getToKingPath(Piece king, Piece p, Board b){
        ArrayList<Square> return_value = null;
        int x = king.getCurrentSquare().getX();  // row
        int y = king .getCurrentSquare().getY(); // column
        int xx = p.getCurrentSquare().getX(); // row
        int yy = p.getCurrentSquare().getY(); // column

        // N and S
        if(y == yy){
            if(x > xx){  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 2, b.getBoard()); }  // N
            else{ return_value = pathBetweenKingAndPiece( x,  y,  xx,  yy, 6, b.getBoard()); } // S
        }
        // W and E
        else if ( x == xx){
            if(y > yy){  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 4, b.getBoard());  } // W
            else{ return_value = pathBetweenKingAndPiece( x, y, xx, yy, 0, b.getBoard()); }// E
        }
        // SW and SE
        else if (xx > x ){
            if( y > yy){   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 5, b.getBoard());  }//sw
            else{   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 7, b.getBoard());   }// SE
        }
        // NW and SW
        else if( xx < x){
            if(y>yy){   return_value = pathBetweenKingAndPiece( x, y, xx, yy, 3, b.getBoard());}// NW
            else{  return_value = pathBetweenKingAndPiece( x, y, xx, yy, 1, b.getBoard());}//NE
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
    private static ArrayList<Square> pathBetweenKingAndPiece(int x, int y, int xx, int yy, int direction, Square[][] board){
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


}
