import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GamePanel extends JPanel {

    private final int BLOCK_SIZE = 30;
    private final GameBoard board;
    private Timer timer;
    private GameStats stats;
    private int delay;
    public GamePanel() {
        this.board = new GameBoard();
        stats = board.getStats();
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        delay = 400;
        setupKeyControls();
    }

    public void startGame() {
        timer = new Timer(delay, e -> {
            boolean moved = board.softDrop(); // try moving down
            if (!moved) {
                board.placePiece();
                int levelP = board.getStats().getLevel();
                board.clearLines();
                if(levelP != board.getStats().getLevel()){
                    delay = getDelayForLevel(levelP);
                    timer.setDelay(delay);
                }
                boolean ok = board.spawnNextPiece();
                if (!ok) {
                    timer.stop();
                    repaint(); // Ensure last piece is shown even if game over
                    JOptionPane.showMessageDialog(this, "GAME OVER!");
                }
            }
            repaint();
        });
        timer.start();
    }
    private int getDelayForLevel(int level) {
        return Math.max(100, 400 - (level - 1) * 30);
    }   
    private void setupKeyControls() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                Tetromino curr = board.getCurrentPiece();
                if (curr == null) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> board.moveLeft();
                    case KeyEvent.VK_RIGHT -> board.moveRight();
                    case KeyEvent.VK_DOWN -> board.softDrop();
                    case KeyEvent.VK_UP -> board.rotate();
                    case KeyEvent.VK_SPACE -> board.hardDrop();
                }

                repaint();
            }
        });
    }
    public Rectangle getBoundingBox(Tetromino piece) {
        int minRow = 4, maxRow = -1, minCol = 4, maxCol = -1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(piece.shape[i][j]) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        if (maxRow == -1) return new Rectangle(0, 0, 0, 0); // No filled cells

            return new Rectangle(minCol, minRow, maxCol - minCol + 1, maxRow - minRow + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw fixed board blocks
        int[][] grid = board.getBoard();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.RED);
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        // Draw current falling piece
        Tetromino piece = board.getCurrentPiece();
        if (piece != null) {
            g.setColor(piece.color);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (piece.shape[i][j]) {
                        int x = (piece.col + j) * BLOCK_SIZE;
                        int y = (piece.row + i) * BLOCK_SIZE;
                        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
        Tetromino ghost = board.getGhostPiece();
        if(ghost!=null){
            g.setColor(new Color(ghost.color.getRed(),ghost.color.getGreen(),ghost.color.getBlue(),80));
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (ghost.shape[i][j]) {
                        int x = (ghost.col + j) * BLOCK_SIZE;
                        int y = (ghost.row + i) * BLOCK_SIZE;
                        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Savate", Font.BOLD, 18));
        int offsetX = board.getCols() * BLOCK_SIZE + 20; // put text to the right of board

        //display stats
        g.drawString("Score: " + stats.getScore(), offsetX, 50);
        g.drawString("Lines: " + stats.getLinesCleared(), offsetX, 80);
        g.drawString("Level: " + stats.getLevel(), offsetX, 110);
        g.drawRect(0, 0, board.getCols() * BLOCK_SIZE, board.getRows() * BLOCK_SIZE);
        
        // --- Next Piece Preview ---
        Tetromino next = board.getNextPiece();
        if (next != null) {
            Rectangle bounds = getBoundingBox(next);

            int cellSize = BLOCK_SIZE / 2;
            int previewBoxSize = 4 * cellSize;

            int previewX = offsetX;
            int previewY = 160;

            int startX = previewX + (previewBoxSize - bounds.width * cellSize) / 2;
            int startY = previewY + (previewBoxSize - bounds.height * cellSize) / 2;

            g.setColor(next.color);
            for (int i = bounds.y; i < bounds.y + bounds.height; i++) {
                for (int j = bounds.x; j < bounds.x + bounds.width; j++) {
                    if (next.shape[i][j]) {
                        int x = startX + (j - bounds.x) * cellSize;
                        int y = startY + (i - bounds.y) * cellSize;
                        g.fillRect(x, y, cellSize, cellSize);
                    }
                }
            }

            g.setColor(Color.WHITE);
            g.drawRect(previewX, previewY, previewBoxSize, previewBoxSize);
            g.drawString("Next:", previewX, previewY - 10);  
        }
    }
}