import java.awt.Color;
import java.util.EnumMap;

public class Classic implements Theme {
    private EnumMap<TetrominoType, Color> mapping = new EnumMap<>(TetrominoType.class);

    public Classic() {
        mapping.put(TetrominoType.I, Color.CYAN);
        mapping.put(TetrominoType.O, Color.YELLOW);
        mapping.put(TetrominoType.T, Color.MAGENTA);
        mapping.put(TetrominoType.S, Color.GREEN);
        mapping.put(TetrominoType.Z, Color.RED);
        mapping.put(TetrominoType.J, Color.BLUE);
        mapping.put(TetrominoType.L, Color.ORANGE);
    }

    @Override
    public Color getColor(TetrominoType type) {
        return mapping.get(type);
    }

    @Override
    public String getThemeName() {
        return "Classic";
    }

    public Color getBgColor(){
        // System.out.println("over here");
        return new Color(140,128,139);
    }
}