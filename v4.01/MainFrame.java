import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel game;
    private ThemeManager themeManager;

    public MainFrame() {
        setTitle("Tetris");
        setSize(450 , 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        themeManager = new ThemeManager(); // Shared instance

        MenuPanel menu = new MenuPanel(this);
        game = new GamePanel(themeManager); 
        SettingsPanel settings = new SettingsPanel(this, game, themeManager);

        mainPanel.add(menu, "Menu");
        mainPanel.add(game, "Game");
        mainPanel.add(settings, "Settings");

        add(mainPanel);
        showPanel("Menu");
        setVisible(true);
    }


    public void start(){
        SoundManager.playBGM("sounds/bgm2.wav");
        SoundManager.playSFX("sounds/gamestart.wav");
        this.game.startGame();
    }

    public void showPanel(String name) { 
        cardLayout.show(mainPanel, name);
        if (name.equals("Game")) {
        SwingUtilities.invokeLater(() -> {
            game.requestFocusInWindow();
        });
    }
    }
}
