import org.junit.Test;
import java.awt.*;

import static org.junit.Assert.*;

public class QueenTest {

    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    Queen q1 = new Queen(Color.YELLOW, null, "queen_yellow");
    Queen q2 = new Queen(Color.RED, null, "queen_red");
    @Test
    public void CombinationTest() throws Exception{
        assertEquals(q1.getCurrentSquare(), null);
        assertEquals(q1.getCurrentSquare(), null);
        assertEquals(q1.getColor().equals(q2.getColor()),false );
        assertEquals(q2.getName().equals(q1.getName()), false);
    }


    @Test
    public void OriginalPosition() throws Exception{
        assertEquals(square[0][3].getPiece().getName(), "queen_black");

        assertEquals(square[7][3].getPiece().getName(), "queen_white");
    }
    @Test
    public void testPotential_moves() throws Exception {
        assertEquals(square[0][3].getPiece().potential_moves(board).isEmpty(), true);
        assertEquals(square[7][3].getPiece().potential_moves(board).isEmpty(), true);

    }
}