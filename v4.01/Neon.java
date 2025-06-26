import java.awt.Color;
import java.util.EnumMap;
public class Neon implements Theme {
    private EnumMap<TetrominoType, Color> mapping = new EnumMap<>(TetrominoType.class);

    public Neon() {
        mapping.put(TetrominoType.I, Color.decode("#60cdef"));
        mapping.put(TetrominoType.O, Color.decode("#e5d758"));
        mapping.put(TetrominoType.T, Color.decode("#4634b9"));
        mapping.put(TetrominoType.S, Color.decode("#54bea8"));
        mapping.put(TetrominoType.Z, Color.decode("#ca34ae"));
        mapping.put(TetrominoType.J, Color.decode("#1a429c"));
        mapping.put(TetrominoType.L, Color.decode("#d76140"));
    }

    @Override
    public Color getColor(TetrominoType type) {
        return mapping.get(type);
    }

    @Override
    public String getThemeName() {
        return "Neon";
    }
    
    @Override
    public Color getBgColor(){
        return Color.PINK;
    }
}