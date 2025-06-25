import java.util.*;

public class ThemeManager {
    
    private List<Theme> themes;

    private int currentIndex;

    public ThemeManager(){
        currentIndex = 0;
        themes = new ArrayList<>();
        themes.add(new Classic());
        themes.add(new Dark());
        themes.add(new Neon());
    }
    public Theme getCurrentTheme() {
        return themes.get(currentIndex);
    }
    public void nextTheme() {
        currentIndex = (currentIndex + 1) % themes.size();
    }
    public void setTheme(int index) {
        if (index >= 0 && index < themes.size()) {
            currentIndex = index;
        }
    }
    public List<Theme> getAllThemes() {
        return themes;
    }
}