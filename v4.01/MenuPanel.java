import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel {
    public MenuPanel(MainFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        JButton startButton = new JButton("Start Game");
        JButton settingsButton = new JButton("Settings");

        startButton.addActionListener(e -> {
            // GamePanel gp = new GamePanel(0);
            // gp.startGame();
            frame.start();
            frame.showPanel("Game");
        });
        settingsButton.addActionListener(e -> frame.showPanel("Settings"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        add(startButton, gbc);
        gbc.gridy++;
        add(settingsButton, gbc);
    }
}
