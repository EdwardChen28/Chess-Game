import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PawnTest {

    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    //ArrayList<Piece> white = board.getWhitePlayer();
    //ArrayList<Piece> black = board.getBlackPlayer();

    /**
     * checking the original position
     * @throws Exception
     */
    @Test
    public void OriginalPosition() throws Exception{
        for(int j = 0; j < board.getWidth();j++) {
            assertEquals(square[1][j].getPiece().getName(), "pawn"+(j+1)+"_black");
            assertEquals(square[6][j].getPiece().getName(), "pawn"+(j+1)+"_white");
        }

    }

    /**
     * checking the possible move originally
     * @throws Exception
     */
    @Test
    public void testPotential_moves() throws Exception {
        for(int j = 0; j < board.getWidth();j++) {
            assertEquals(square[1][j].getPiece().potential_moves(board).size(), 2);
            assertEquals(square[6][j].getPiece().potential_moves(board).size(), 2);
        }
    }
}