import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Created by Edward on 2/17/2015.
 */
public class GUI extends JFrame{
    JButton[][] square;
    public JButton restart, undo;
    int rows, columns;
    public static void main(String[] args) {
        new GUI(8,8);
    }

    public GUI(int row, int column){
        init(row, column);
        setUpMenu(this);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void init(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        square = new JButton[rows][columns];
        this.setTitle("Chess Game");
        this.setSize(646, 640);
        this.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(3,3,3,3));
        drawBoard( rows, columns, square, mainPanel);
        this.add(mainPanel);
    }
    private static void setCommunicationTool(JPanel panel){
        JTextField text = new JTextField("Type Here", 30);
        text.setColumns(30);
        panel.add(text, BorderLayout.SOUTH);
        JButton send = new JButton("send");
        panel.add(send, BorderLayout.SOUTH);
        JTextArea textArea = new JTextArea("Received messages show here");
        panel. add(textArea, BorderLayout.SOUTH);
    }

    public void addButtonActionListener(ActionListener listener){
        for( int i = 0 ; i < rows; i++){
            for( int j = 0 ; j < columns; j++){
                square[i][j].addActionListener(listener);
            }
        }

        undo.addActionListener(listener);
        restart.addActionListener(listener);
    }
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        undo = new JButton("Undo");
        restart = new JButton("Restart");
        menubar.add(restart);
        menubar.add(undo);
        window.setJMenuBar(menubar);
    }

    /**
     * draw the game board
     * @param rows height of the board
     * @param columns width of the board
     * @param board 2D buttons
     * @param panel Jpanel that will contains the 2D buttons
     */
    private static void drawBoard(int rows, int columns, JButton[][] board, JPanel panel) {

        panel.setLayout(new GridLayout(rows, columns));
        panel.setBackground(Color.gray);
        Color colorSwitch;
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                colorSwitch = Color.LIGHT_GRAY;
            } else {
                colorSwitch = Color.GRAY;
            }
            for (int j = 0; j < columns; j++) {
                board[i][j] = new JButton();
                board[i][j].setBackground(colorSwitch);
                if (colorSwitch.equals(Color.LIGHT_GRAY)) {
                    colorSwitch = Color.GRAY;
                } else {
                    colorSwitch = Color.LIGHT_GRAY;
                }
                panel.add(board[i][j]);
            }
        }
        // set pieces
        for (int j = 0; j < columns; j++) {
            //board[1][j].setBackground(Color.BLACK);
            board[1][j].setIcon(new ImageIcon("src/picture/black_pawn.png"));
            board[6][j].setIcon(new ImageIcon("src/picture/white_pawn.png"));
        }
        board[0][0].setIcon(new ImageIcon("src/picture/black_rook.png"));
        board[0][1].setIcon(new ImageIcon("src/picture/black_knight.png"));
        board[0][2].setIcon(new ImageIcon("src/picture/black_bishop.png"));
        board[0][3].setIcon(new ImageIcon("src/picture/black_queen.png"));
        board[0][4].setIcon(new ImageIcon("src/picture/black_king.png"));
        board[0][5].setIcon(new ImageIcon("src/picture/black_bishop.png"));
        board[0][6].setIcon(new ImageIcon("src/picture/black_knight.png"));
        board[0][7].setIcon(new ImageIcon("src/picture/black_rook.png"));

        board[7][0].setIcon(new ImageIcon("src/picture/white_rook.png"));
        board[7][1].setIcon(new ImageIcon("src/picture/white_knight.png"));
        board[7][2].setIcon(new ImageIcon("src/picture/white_bishop.png"));
        board[7][3].setIcon(new ImageIcon("src/picture/white_queen.png"));
        board[7][4].setIcon(new ImageIcon("src/picture/white_king.png"));
        board[7][5].setIcon(new ImageIcon("src/picture/white_bishop.png"));
        board[7][6].setIcon(new ImageIcon("src/picture/white_knight.png"));
        board[7][7].setIcon(new ImageIcon("src/picture/white_rook.png"));

    }

   public void updatePiece(int currX, int currY, int destX, int destY){
       square[destX][destY].setIcon(square[currX][currY].getIcon());
       square[currX][currY].setIcon(null);

   }

    public void restore(){
        for(int i = 0 ; i < rows; i ++){
            for(int j = 0; j < columns; j++){
                square[i][j].setIcon(null);
            }
        }
        for (int j = 0; j < columns; j++) {
            //board[1][j].setBackground(Color.BLACK);
            square[1][j].setIcon(new ImageIcon("src/picture/black_pawn.png"));
            square[6][j].setIcon(new ImageIcon("src/picture/white_pawn.png"));
        }
        square[0][0].setIcon(new ImageIcon("src/picture/black_rook.png"));
        square[0][1].setIcon(new ImageIcon("src/picture/black_knight.png"));
        square[0][2].setIcon(new ImageIcon("src/picture/black_bishop.png"));
        square[0][3].setIcon(new ImageIcon("src/picture/black_queen.png"));
        square[0][4].setIcon(new ImageIcon("src/picture/black_king.png"));
        square[0][5].setIcon(new ImageIcon("src/picture/black_bishop.png"));
        square[0][6].setIcon(new ImageIcon("src/picture/black_knight.png"));
        square[0][7].setIcon(new ImageIcon("src/picture/black_rook.png"));

        square[7][0].setIcon(new ImageIcon("src/picture/white_rook.png"));
        square[7][1].setIcon(new ImageIcon("src/picture/white_knight.png"));
        square[7][2].setIcon(new ImageIcon("src/picture/white_bishop.png"));
        square[7][3].setIcon(new ImageIcon("src/picture/white_queen.png"));
        square[7][4].setIcon(new ImageIcon("src/picture/white_king.png"));
        square[7][5].setIcon(new ImageIcon("src/picture/white_bishop.png"));
        square[7][6].setIcon(new ImageIcon("src/picture/white_knight.png"));
        square[7][7].setIcon(new ImageIcon("src/picture/white_rook.png"));

    }
    public JButton[][] getSquare(){
        return square;
    }
}
