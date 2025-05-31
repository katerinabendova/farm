package main;

import javax.swing.*;

public class GameLauncher {

    private JFrame window;
    private GamePanel gamePanel;

    public GameLauncher() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Farm");

        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    public void start() {
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
