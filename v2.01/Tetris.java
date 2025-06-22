import javax.swing.*;

class Tetris{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(430,630);
        frame.setResizable(true);
        
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);
        // panel.setFocusable(true);
        // panel.requestFocusInWindow();
        panel.startGame();
        
    }
}