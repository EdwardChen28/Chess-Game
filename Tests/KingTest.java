import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KingTest {
    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    ArrayList<Piece> white = board.getWhitePlayer();
    ArrayList<Piece> black = board.getBlackPlayer();
    @Test
    public void OriginalPosition() throws Exception{
        assertEquals(square[0][4].getPiece().getName(), "king_black");
        assertEquals(square[7][4].getPiece().getName(), "king_white");
    }

    @Test
    public void testPotential_moves() throws Exception {
        assertEquals(square[0][4].getPiece().potential_moves(board).isEmpty(), true);
        assertEquals(square[7][4].getPiece().potential_moves(board).isEmpty(), true);
    }
}