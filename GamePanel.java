import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

class GamePanel extends JPanel{

    private final int BLOCK_SIZE = 30;
    // private int blockRow = 0;
    // private int currPiece.col = 4;
    private Timer timer;

    Tetromino currPiece;

    public void startGame(){
        currPiece = new Tetromino(Tetrominoes.getRandomShape(), Color.blue);
        
        timer = new Timer(400, e-> {
            
            if (checkCollision(currPiece, currPiece.row+1, currPiece.col)) {

                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(currPiece.shape[i][j] && currPiece.row + i < rows && currPiece.col + j < cols){
                            board[currPiece.row + i][currPiece.col + j] = 1;
                        }
                    }
                }
                
                checkLines();
                currPiece = new Tetromino(Tetrominoes.getRandomShape(), Color.blue);
                currPiece.col = 4;
                currPiece.row = 0;
                
                if (checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
                    placePiece();  // Fix current piece to board
                    repaint();     // Update the board display with the last piece

                    timer.stop();  // Stop the game
                    JOptionPane.showMessageDialog(this, "GAME OVER !");
                    return;
                }
            }
            else
                currPiece.row++;
            repaint();
        });
        timer.start();
    }
    
    public boolean checkCollision(Tetromino currPiece, int newRow, int newCol) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currPiece.shape[i][j]) {
                    int boardRow = newRow + i;
                    int boardCol = newCol + j;

                    // Out of bounds
                    if (boardRow < 0 || boardRow >= rows || boardCol < 0 || boardCol >= cols)
                        return true;

                    // Collides with fixed block
                    if (board[boardRow][boardCol] == 1)
                        return true;
                }
            }
        }
        return false;
    }
    public void checkLines(){
        int lines = 0;
        for(int i=0;i<rows;i++){
            boolean full = true;
            for(int j=0;j<cols;j++){
                if(board[i][j] == 0){
                    full = false;
                    break;
                }
            }
            if(full){
                lines++;
                for(int pi = i;pi>0;pi--)
                    board[pi] = board[pi-1].clone();
                board[0] = new int[cols];
            }
        }
        if(lines > 0)
            System.out.println("Cleared "+lines+" lines !");
    }
    public void placePiece() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currPiece.shape[i][j]) {
                    int r = currPiece.row + i;
                    int c = currPiece.col + j;
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        board[r][c] = 1;
                    }
                }
            }
        }
    }
    private final int rows = 20;
    private final int cols = 10;
    private int[][] board= new int[rows][cols];

    public GamePanel(){
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (currPiece == null) return;

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                if (!checkCollision(currPiece, currPiece.row, currPiece.col - 1))
                    currPiece.col--;
            } else if (key == KeyEvent.VK_RIGHT) {
                if (!checkCollision(currPiece, currPiece.row, currPiece.col + 1))
                    currPiece.col++;
            } else if (key == KeyEvent.VK_DOWN) {
                if (!checkCollision(currPiece, currPiece.row + 1, currPiece.col))
                    currPiece.row++;
            } else if (key == KeyEvent.VK_SPACE) {
                while (!checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
                    currPiece.row++;
                }
                placePiece();
            } else if (key == KeyEvent.VK_UP) {
                currPiece.rotateClkWise();
                if (checkCollision(currPiece, currPiece.row, currPiece.col)) {
                    // Undo rotation if it collides
                    for (int i = 0; i < 3; i++) currPiece.rotateClkWise();
                }
            }

            repaint();
        }
    });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(currPiece != null){
            g.setColor(currPiece.color);
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(currPiece.shape[i][j]){
                        g.fillRect((currPiece.col+j)*BLOCK_SIZE,(currPiece.row+i)*BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE);
                    }
                }
            }
        }
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == 1){
                    g.setColor(Color.RED);
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }
}