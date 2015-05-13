import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    Board board = new Board(8,8);
    ArrayList<Piece> white = board.getWhitePlayer();
    ArrayList<Piece> black = board.getBlackPlayer();

    /**
     * test the number of pieces each player has originally
     * @throws Exception
     */
    @org.junit.Test
    public void testOriginalPiece() throws Exception{
       /* System.out.print(board.getWhitePlayer().size());
        for(int j = 0 ; j < 8; j++){
            System.out.print(white.get(j).getName()+"  ");
        }*/
        assertEquals(white.size(), 16);
        assertEquals(black.size(),16);
    }

    /**
     * Test board initialization.Including width, height, square set up, pieces setup
     * color of the board, color of the piece
     * @throws Exception
     */
    @org.junit.Test
    public void testGetBoard() throws Exception {
        //check if square has set up
        Square[][] tiles = board.getBoard();
        assertEquals(tiles.length,8);
        assertEquals(tiles.length, 8);
        //check if pieces are set up and their colors
        for(int j = 0; j < 8; j++){
            assertEquals(tiles[0][j].isOccupied(), true);
            assertEquals(tiles[0][j].getPiece().getColor(), Color.BLACK);
            assertEquals(tiles[1][j].isOccupied(), true);
            assertEquals(tiles[1][j].getPiece().getColor(), Color.BLACK);
            assertEquals(tiles[6][j].isOccupied(), true);
            assertEquals(tiles[6][j].getPiece().getColor(), Color.WHITE);
            assertEquals(tiles[7][j].isOccupied(), true);
            assertEquals(tiles[7][j].getPiece().getColor(), Color.WHITE);
        }
        // check board color
        Color colorCheck;
        for (int i = 0; i < tiles.length; i++){
            if(i % 2 == 0){ colorCheck = Color.WHITE;}
            else { colorCheck = Color.BLACK; }
            for(int j = 0 ; j < tiles[i].length; j++){
                assertEquals(colorCheck, tiles[i][j].getColor());
                if(colorCheck.equals((Color.WHITE))){
                    colorCheck = Color.BLACK;
                }
                else{
                    colorCheck = Color.WHITE;
                }
            }
        }
    }




    /**
     * check width of the board
     * @throws Exception
     */
    @org.junit.Test
    public void testGetWidth() throws Exception {
        assertEquals(board.getWidth(), 8);

    }


    /**
     * check height of the board
     * @throws Exception
     */
    @org.junit.Test
    public void testGetHeight() throws Exception {
        assertEquals(board.getHeight(), 8);

    }
}