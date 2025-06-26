import java.awt.Color;
import java.util.EnumMap;
public class Dark implements Theme {
    private EnumMap<TetrominoType, Color> mapping = new EnumMap<>(TetrominoType.class);

    public Dark() {
        mapping.put(TetrominoType.I, Color.decode("#0F7A80"));
        mapping.put(TetrominoType.O, Color.decode("#8F840F"));
        mapping.put(TetrominoType.T, Color.decode("#8A0E70"));
        mapping.put(TetrominoType.S, Color.decode("#198216"));
        mapping.put(TetrominoType.Z, Color.decode("#821010"));
        mapping.put(TetrominoType.J, Color.decode("#191282"));
        mapping.put(TetrominoType.L, Color.decode("#965F10"));
    }

    @Override
    public Color getColor(TetrominoType type) {
        return mapping.get(type);
    }

    @Override
    public String getThemeName() {
        return "Dark";
    }

    @Override
    public Color getBgColor(){
        return Color.BLACK;
    }
}