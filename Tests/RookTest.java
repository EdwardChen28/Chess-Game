import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RookTest {
    Board board = new Board(8,8);
    Square[][] square = board.getBoard();
    ArrayList<Piece> white = board.getWhitePlayer();
    ArrayList<Piece> black = board.getBlackPlayer();
    @Test
    public void OriginalPosition() throws Exception{
        Assert.assertEquals(square[0][0].getPiece().getName(), "rook1_black");
        Assert.assertEquals(square[0][7].getPiece().getName(), "rook2_black");
        Assert.assertEquals(square[7][0].getPiece().getName(), "rook1_white");
        Assert.assertEquals(square[7][7].getPiece().getName(), "rook2_white");
    }


    @Test
    public void testPotential_moves() throws Exception {
        Assert.assertEquals(square[0][0].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[0][7].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[7][0].getPiece().potential_moves(board).isEmpty(), true);
        Assert.assertEquals(square[7][7].getPiece().potential_moves(board).isEmpty(), true);
    }
}