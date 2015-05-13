import java.awt.*;

/**
 * Created by Edward on 2/10/2015.
 */
public class Square {
    private  Color color;
    private  int x;
    private int y;
    private String name;
    private Piece piece; //piece that occupies the square.

   public Square(int xCoor, int yCoor, Color col, String squareName){
       this.x = xCoor;
        this.y = yCoor;
        this.color = col;
        this.name = squareName;
    }

    public boolean isSameSquare(Square other){
        if(this.x == other.getX() && this.y==other.getY() && this.name.equals(other.getName()) && this.color.equals(other.getColor())){
            return true;
        }
        else{ return false;}
    }
    public Color getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPiece(Piece p){
        this.piece = p;
    }
    public Piece getPiece(){
        return this.piece;
    }
    public boolean isOccupied(){
        if(piece == null) {
            return false;
        }
        else{
            return true;
        }
    }
    public String getName(){
        return this.name;
    }
}
