package main;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 4;
    public final int titleSize = 64;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidht = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameTreader;
    Player player;
    TileManager tileM = new TileManager(this);

    public GamePanel() {
        this.player = new Player(this, this.keyHandler);

        this.setPreferredSize(new Dimension(screenWidht, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
    }

    public void startGameTreader() {
        this.gameTreader = new Thread(this);
        this.gameTreader.start();
    }

    public void run() {
        double drawInterval = (double)(1000000000 / this.FPS);
        double delta = (double)0.0F;
        long lastTime = System.nanoTime();

        while(this.gameTreader != null) {
            long currentTime = System.nanoTime();
            delta += (double)(currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= (double)1.0F) {
                this.update();
                this.repaint();
                --delta;
            }
        }

    }

    public void update() {
        this.player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        this.player.draw(g2);

        g2.dispose();
    }
}

