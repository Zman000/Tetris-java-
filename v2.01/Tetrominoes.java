public class Tetrominoes{
    
    public static final boolean[][] SQUARE = {{false,false,false,false},{false,true,true,false},{false,true,true,false},{false,false,false,false}};
    
    public static final boolean[][] T_SHAPE = {{true,true,true,false},{false,true,false,false},{false,false,false,false},{false,false,false,false}};
    
    public static final boolean[][] LINE = {{true,true,true,true},{false,false,false,false},{false,false,false,false},{false,false,false,false}};

    public static final boolean[][] L_SHAPE = {{false,false,true,false},{true,true,true,false},{false,false,false,false},{false,false,false,false}};

    public static final boolean[][] Z_SHAPE = {{false,true,false,false},{true,true,false,false},{true,false,false,false},{false,false,false,false}};

    public static final boolean[][] S_SHAPE = {{true,false,false,false},{true,true,false,false},{true,false,false,false},{false,false,false,false}};

    public static boolean[][] getRandomShape(){
        int x = (int)((Math.random()*6)+1); //generate random number from 1 to 6
        switch(x) {
            case 1:
                return SQUARE;
            case 2:
                return T_SHAPE;
            case 3:
                return LINE;
            case 4:
                return L_SHAPE;
            case 5:
                return Z_SHAPE;
            case 6:
                return S_SHAPE;
            default:
                return LINE;
        }
    }
}