public class GameBoard {

    private final int rows = 20;
    private final int cols = 10;
    private TetrominoType[][] board;
    private Theme theme;
    private GameStats stats;
    private Tetromino currPiece;
    private Tetromino nextPiece;
    private TetrominoFactory generator;

    public GameBoard(Theme theme) {
        board = new TetrominoType[rows][cols];
        generator = new TetrominoFactory(theme);
        stats = new GameStats();

        currPiece = generator.createRandomTetromino();
        currPiece.row = 0;
        currPiece.col = cols / 2 - 2;
        nextPiece = generator.createRandomTetromino();
    }

    public boolean checkCollision(Tetromino piece, int newRow, int newCol) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (piece.shape[i][j]) {
                    int r = newRow + i;
                    int c = newCol + j;
                    if (r < 0 || r >= rows || c < 0 || c >= cols)
                        return true;
                    if (board[r][c] != null)
                        return true;
                }
            }
        }
        return false;
    }

    public void placePiece() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currPiece.shape[i][j]) {
                    int r = currPiece.row + i;
                    int c = currPiece.col + j;
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        board[r][c] = currPiece.type;
                    }
                }
            }
        }
    }

    public int clearLines() {
        int linesCleared = 0;
        for (int i = 0; i < rows; i++) {
            boolean fullLine = true;
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == null) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                linesCleared++;
                for (int r = i; r > 0; r--) {
                    board[r] = board[r - 1].clone();
                }
                board[0] = new TetrominoType[cols];
            }
        }
        stats.addLines(linesCleared);
        return linesCleared;
    }

    public boolean spawnNextPiece() {
        currPiece = nextPiece;
        currPiece.row = 0;
        currPiece.col = cols / 2 - 2;
        nextPiece = generator.createRandomTetromino();
        return !checkCollision(currPiece, currPiece.row, currPiece.col);
    }

    public TetrominoType[][] getBoard() {
        return board;
    }

    public Tetromino getCurrentPiece() {
        return currPiece;
    }

    public Tetromino getNextPiece() {
        return nextPiece;
    }

    public GameStats getStats() {
        return stats;
    }

    public Tetromino getGhostPiece() {
        Tetromino ghost = currPiece.copy();
        while (!checkCollision(ghost, ghost.row + 1, ghost.col)) {
            ghost.row++;
        }
        return ghost;
    }

    public void moveLeft() {
        if (!checkCollision(currPiece, currPiece.row, currPiece.col - 1)) {
            currPiece.col--;
        }
    }

    public void moveRight() {
        if (!checkCollision(currPiece, currPiece.row, currPiece.col + 1)) {
            currPiece.col++;
        }
    }

    public boolean softDrop() {
        if (!checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
            currPiece.row++;
            return true;
        }
        return false;
    }

    public void hardDrop() {
        while (!checkCollision(currPiece, currPiece.row + 1, currPiece.col)) {
            currPiece.row++;
        }
        placePiece();
        clearLines();
        spawnNextPiece();
    }

    public void rotate() {
        currPiece.rotateClkWise();
        if (checkCollision(currPiece, currPiece.row, currPiece.col)) {
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