import java.awt.*;

public class Tetromino{
    public boolean[][] shape;
    public int row,col;
    public Color color;
    public TetrominoType type;

    public Tetromino(TetrominoType type,Color cl){
        this.shape = Tetrominoes.getShape(type);
        color = cl;
        row = 0;
        col = 4;
        this.type = type;
    }

    public void rotateClkWise(){
        boolean rotated[][] = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                rotated[j][3-i] = shape[i][j];

            }
        }
        shape = rotated;
    }

    public Tetromino copy() {
        
        Tetromino copiedT = new Tetromino(this.type, this.color);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)
                copiedT.shape[i][j] = this.shape[i][j];
        }
        copiedT.row = this.row;
        copiedT.col = this.col;

        return copiedT;
    }
}