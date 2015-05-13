import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BishopTest {

    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    //ArrayList<Piece> white = board.getWhitePlayer();
    //ArrayList<Piece> black = board.getBlackPlayer();

    /**
     * check the original position of all the pieces
     * @throws Exception
     */
    @Test
    public void OriginalPosition() throws Exception{
        Assert.assertEquals(square[0][2].getPiece().getName(), "bishop1_black");
        Assert.assertEquals(square[0][5].getPiece().getName(), "bishop2_black");
        Assert.assertEquals(square[7][2].getPiece().getName(), "bishop1_white");
        Assert.assertEquals(square[7][5].getPiece().getName(), "bishop2_white");
    }

    /**
     * check the possible move once the board od created
     * @throws Exception
     */
    @Test
    public void testPotential_moves() throws Exception {
        Assert.assertEquals(square[0][2].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[0][5].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[7][2].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[7][5].getPiece().potential_moves(board).isEmpty(), true);
    }
}