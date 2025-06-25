import java.awt.Color;

public interface Theme{
    Color getColor(TetrominoType type);
    String getThemeName();
    Color getBgColor();
}