import java.awt.Color;
public class TetrominoFactory {
    private Theme theme;

    public TetrominoFactory(Theme theme) {
        this.theme = theme;
    }

    public Tetromino createRandomTetromino() {
        TetrominoType type = Tetrominoes.getRandomType();
        return createTetromino(type);
    }

    public Tetromino createTetromino(TetrominoType type) {
        boolean[][] shape = Tetrominoes.getShape(type);
        Color color = theme.getColor(type);
        return new Tetromino(type, color);
    }
}