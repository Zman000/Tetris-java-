import java.awt.Color;

public class GameBoard {

    private final int rows = 20;
    private final int cols = 10;
    private int[][] board;  // 0 = empty, 1 = filled

    private GameStats stats;

    private Tetromino currPiece;
    private Tetromino nextPiece;

    public GameBoard() {
        board = new int[rows][cols];
        currPiece = new Tetromino(Tetrominoes.getRandomShape(), Color.yellow);
        currPiece.row = 0;
        currPiece.col = cols / 2 - 2;
        stats = new GameStats();
        nextPiece = new Tetromino(Tetrominoes.getRandomShape(), Color.yellow);
    }
   
    // Returns true if placing piece at (newRow, newCol) causes collision or out-of-bounds
    public boolean checkCollision(Tetromino piece, int newRow, int newCol) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (piece.shape[i][j]) {
                    int r = newRow + i;
                    int c = newCol + j;
                    if (r < 0 || r >= rows || c < 0 || c >= cols)
                        return true;
                    if (board[r][c] == 1)
                        return true;
                }
            }
        }
        return false;
    }

    // Fix current piece to board array
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

    // Clear full lines; returns number of lines cleared
    public int clearLines() {
        int linesCleared = 0;
        for (int i = 0; i < rows; i++) {
            boolean fullLine = true;
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                linesCleared++;
                // Move all rows above down by one
                for (int r = i; r > 0; r--) {
                    board[r] = board[r - 1].clone();
                }
                board[0] = new int[cols]; // Clear top row
            }
        }
        stats.addLines(linesCleared);
        return linesCleared;
    }

    public GameStats getStats(){
        return stats;
    }
    // Spawn next piece; returns false if spawning causes collision (game over)
    public boolean spawnNextPiece() {
        currPiece = nextPiece;
        currPiece.row = 0;
        currPiece.col = cols / 2 - 2;

        nextPiece = new Tetromino(Tetrominoes.getRandomShape(), Color.yellow);

        return !checkCollision(currPiece, currPiece.row, currPiece.col);
    }

    // Getters for rendering
    public int[][] getBoard() {
        return board;
    }

    public Tetromino getCurrentPiece() {
        return currPiece;
    }

    public Tetromino getNextPiece() {
        return nextPiece;
    }

    public Tetromino getGhostPiece(){
        Tetromino ghost = currPiece.copy();
        while(!checkCollision(ghost,ghost.row+1,ghost.col)){
            ghost.row++;
        }
        return ghost;
    }
    // Move left if possible
    public void moveLeft() {
        if (!checkCollision(currPiece, currPiece.row, currPiece.col - 1)) {
            currPiece.col--;
        }
    }

    // Move right if possible
    public void moveRight() {
        if (!checkCollision(currPiece, currPiece.row, currPiece.col + 1)) {
            currPiece.col++;
        }
    }

    // Soft drop (move down) if possible
    public boolean softDrop() {
        if (!checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
            currPiece.row++;
            return true;
        }
        return false;
    }

    // Hard drop (move piece down until collision)
    public void hardDrop() {
        while (!checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
            currPiece.row++;
        }
        placePiece();
        clearLines();
        spawnNextPiece();
    }

    // Rotate clockwise with collision check (undo if collides)
    public void rotate() {
        currPiece.rotateClkWise();
        if (checkCollision(currPiece, currPiece.row, currPiece.col)) {
            // Undo rotation (3 more rotations)
            for (int i = 0; i < 3; i++) {
                currPiece.rotateClkWise();
            }
        }
    }
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}