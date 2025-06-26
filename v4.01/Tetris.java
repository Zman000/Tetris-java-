import javax.swing.*;

class Tetris{
    public static void main(String[] args) {
        /*
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(450,630);
        frame.setResizable(true);
        int theme = Integer.parseInt(args[0]);
        GamePanel panel = new GamePanel(theme);
        frame.add(panel);
        frame.setVisible(true);
        // panel.setFocusable(true);
        // panel.requestFocusInWindow();
        panel.startGame();
        */
        SwingUtilities.invokeLater(MainFrame::new);
    }
}