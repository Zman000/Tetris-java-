import java.awt.*;

public class Tetromino{
    public boolean[][] shape;
    public int row,col;
    public Color color;
    
    public Tetromino(boolean[][] shape,Color cl){
        this.shape = shape;
        color = cl;
        row = 0;
        col = 4;
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
        boolean[][] newShape = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(this.shape[i], 0, newShape[i], 0, 4);
        }

        Tetromino copiedT = new Tetromino(newShape, this.color);
        copiedT.row = this.row;
        copiedT.col = this.col;

        return copiedT;
    }
}