import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KnightTest {
    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    ArrayList<Piece> white = board.getWhitePlayer();
    ArrayList<Piece> black = board.getBlackPlayer();

    /**
     * check the original position
     * @throws Exception
     */
    @Test
    public void OriginalPosition() throws Exception{
        assertEquals(square[0][1].getPiece().getName(), "knight1_black");
        assertEquals(square[0][6].getPiece().getName(), "knight2_black");
        assertEquals(square[7][1].getPiece().getName(), "knight1_white");
        assertEquals(square[7][6].getPiece().getName(), "knight2_white");
    }

    /**
     * check the moves it can take when the board is created
     * @throws Exception
     */
    @Test
    public void testPotential_moves() throws Exception {

        assertEquals(square[0][1].getPiece().potential_moves(board).size(), 2);
        assertEquals(square[0][6].getPiece().potential_moves(board).size(), 2);
        assertEquals(square[7][6].getPiece().potential_moves(board).size(), 2);
        assertEquals(square[7][1].getPiece().potential_moves(board).size(), 2);
    }
}