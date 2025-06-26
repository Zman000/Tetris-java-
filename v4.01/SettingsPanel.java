import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(MainFrame frame, GamePanel gamePanel, ThemeManager themeManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);

        JLabel label = new JLabel("Select Theme:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<Theme> themes = themeManager.getAllThemes();
        String[] themeNames = new String[themes.size()];
        for(int i=0;i<themes.size();i++)
            themeNames[i] = themes.get(i).getThemeName();

        JComboBox<String> themeDropdown = new JComboBox<>(themeNames);
        themeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeDropdown.setSelectedItem(themeManager.getCurrentTheme().getThemeName());

        JButton applyButton = new JButton("Apply Theme");
        applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyButton.addActionListener(e -> {
            int selectedIndex = themeDropdown.getSelectedIndex();
            themeManager.setTheme(selectedIndex);
            gamePanel.applyTheme(themeManager.getCurrentTheme());
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> frame.showPanel("Menu"));

        add(Box.createVerticalStrut(50));
        add(label);
        add(Box.createVerticalStrut(10));
        add(themeDropdown);
        add(Box.createVerticalStrut(20));
        add(applyButton);
        add(Box.createVerticalStrut(20));
        add(backButton);
    }
}
