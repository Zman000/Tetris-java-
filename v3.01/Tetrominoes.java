import java.util.Random;
public class Tetrominoes{
    
    public static final boolean[][] O = {
        {false, false, false, false},
        {false, true,  true,  false},
        {false, true,  true,  false},
        {false, false, false, false}
    };

    public static final boolean[][] T = {
        {false, false, false, false},
        {true,  true,  true,  false},
        {false, true,  false, false},
        {false, false, false, false}
    };

    public static final boolean[][] I = {
        {false, false, false, false},
        {true,  true,  true,  true },
        {false, false, false, false},
        {false, false, false, false}
    };

    public static final boolean[][] L = {
        {false, false, true,  false},
        {true,  true,  true,  false},
        {false, false, false, false},
        {false, false, false, false}
    };

    public static final boolean[][] J = {
        {true,  false, false, false},
        {true,  true,  true,  false},
        {false, false, false, false},
        {false, false, false, false}
    };

    public static final boolean[][] Z = {
        {true,  true,  false, false},
        {false, true,  true,  false},
        {false, false, false, false},
        {false, false, false, false}
    };

    public static final boolean[][] S = {
        {false, true,  true,  false},
        {true,  true,  false, false},
        {false, false, false, false},
        {false, false, false, false}
    };

    private static final TetrominoType[] TYPES = TetrominoType.values();
    private static final Random rand = new Random();

    public static boolean[][] getShape(TetrominoType type) {
        switch (type) {
            case O: return O;
            case T: return T;
            case I: return I;
            case L: return L;
            case J: return J;
            case Z: return Z;
            case S: return S;
            default: return I;
        }
    }

    public static TetrominoType getRandomType() {
        return TYPES[rand.nextInt(TYPES.length)];
    }
}