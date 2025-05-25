package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    public final int titleSize = 48;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidht = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // fps
    int FPS = 60;

    // system
    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameTreader;

    // entity and object
    public Player player;
    public SuperObject obj[] = new SuperObject[20];
    public Entity animals[] = new Entity[10];

    // game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;



    public GamePanel() {
        this.player = new Player(this, this.keyHandler);

        this.setPreferredSize(new Dimension(screenWidht, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();

        playMusic(0);
        aSetter.setAnimal();
        gameState = playState;
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
//        if (gameState == playState) {
//            player.update();
//            for (int i = 0; i < animals.length; i++) {
//                if (animals[i] != null) {
//                    animals[i].update();
//                }
//            }
//        }


        //this.player.update();

        if (gameState == playState){
            player.update();
        }

        
        // animal
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] != null){
                animals[i].update();
            }
        }
        if (gameState == pauseState){
           ui.drawPauseScreen();
        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // tile
        tileM.draw(g2);

        //object
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        // animal
        for (int i = 0; i < animals.length; i++){
            if (animals[i] != null){
                animals[i].draw(g2);
            }
        }
        // player
        this.player.draw(g2);

        //ui
        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){ //se = sound effect
        sound.setFile(i);
        sound.play();
    }
}

