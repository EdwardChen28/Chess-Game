import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SquareTest {
    Square s1 = new Square(3, 6, Color.RED, "5g");
    Square s2 = new Square(6, 6, Color.PINK, "3f");
    Square s3 = new Square(1, 2, Color.RED, "Good");
    Square s4 = new Square(3, 6, Color.RED, "5g");
    Square s5 = new Square(6, 6, Color.PINK, "3a");
    Piece king = new King(Color.RED, s1, "king_red");
    Piece queen = new Queen(Color.RED, s3, "king_red");
    Piece knight = new Knight(Color.RED, s5, "king_red");

    @Test
    public void testIsSameSquare() throws Exception {
        assertEquals( s1.isSameSquare(s4), true);
        assertEquals( s2.isSameSquare(s5), false);
        assertEquals( s3.isSameSquare(s4), false);
        assertEquals( s1.isSameSquare(s2), false);
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(s1.getColor(), Color.RED);
        assertEquals(s2.getColor(), Color.PINK);
        assertEquals(s3.getColor(), Color.RED);
        assertEquals(s4.getColor(), Color.RED);
        assertEquals(s5.getColor(), Color.PINK);

    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(s1.getX(), s4.getX());
        assertEquals(s2.getX(), s5.getX());
        assertEquals(s3.getX(), 1);
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(s1.getY(), s2.getY());
        assertEquals(s4.getY(), s5.getY());
        assertEquals(s4.getY(), s1.getY());
        assertEquals(s3.getY(),2);
    }

    @Test
    public void testSetPiece() throws Exception {
        s1.setPiece(king);
        s3.setPiece(queen);
        s5.setPiece(knight);
        assertEquals(king.isSame(s1.getPiece() ), true);
        assertEquals(queen.isSame(s3.getPiece() ), true);
        assertEquals(knight.isSame(s5.getPiece() ), true);
    }

    @Test
    public void testIsOccupied() throws Exception {
        s1.setPiece(king);
        s3.setPiece(queen);
        s5.setPiece(knight);
        assertEquals(s1.isOccupied(), true);
        assertEquals(s3.isOccupied(), true);
        assertEquals(s5.isOccupied(), true);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(s1.getName(), "5g");
        assertEquals(s2.getName(), "3f");
        assertEquals(s3.getName(), "Good");
        assertEquals(s4.getName(), "5g");
        assertEquals(s5.getName(), "3a");
    }
}