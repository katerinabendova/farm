package main;

import entity.Entity;
import entity.Player;
import crop.CropManager;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    public final int tileSize = 48;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidht = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world settings
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    // fps
    int FPS = 60;

    // system
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    public Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Inventory inventory = new Inventory();
    public CropManager cropM = new CropManager(this);


    public EventHandler eHandler = new EventHandler(this);
    Thread gameTreader;

    // entity and object
    public Player player;
    public Entity obj[] = new Entity[20];
    public Entity animals[] = new Entity[10];
    ArrayList<Entity> entities =new ArrayList<>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;


    public GamePanel() {
        this.player = new Player(this, this.keyHandler);

        this.setPreferredSize(new Dimension(screenWidht, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        ui.loadLifeImages();

        setupGame();
    }

    public void setupGame() {
        aSetter.setObjectWorld1();
        aSetter.setAnimalWorld1();
        //   playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        this.gameTreader = new Thread(this);
        this.gameTreader.start();
    }

    public void loadMapData(String mapName) {
        tileM.loadMap("/maps/" + mapName);

        maxWorldCol = tileM.maxWorldCol;
        maxWorldRow = tileM.maxWorldRow;

        obj = new Entity[10];
        animals = new Entity[10];

        if (mapName.equals("stable.txt")) {
            aSetter.setObjectStable();
            aSetter.setAnimalStable();
        } else if (mapName.equals("world1.txt")) {
            aSetter.setObjectWorld1();
            aSetter.setAnimalWorld1();
        }
    }


    public void run() {
        double drawInterval = (double) (1000000000 / this.FPS);
        double delta = (double) 0.0F;
        long lastTime = System.nanoTime();

        while (this.gameTreader != null) {
            long currentTime = System.nanoTime();
            delta += (double) (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= (double) 1.0F) {
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

        if (gameState == playState) {
            player.update();
            eHandler.checkEvent();
        }


        // animal
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] != null) {
                animals[i].update();
            }
        }
        if (gameState == pauseState) {
            ui.drawPauseScreen();
        }

        cropM.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // others
        else {
            tileM.draw(g2);
            cropM.draw(g2);

            entities = new ArrayList<>();

            entities.add(player);
            for (Entity animal : animals) {
                if (animal != null) {
                    entities.add(animal);
                }
            }
            for (Entity object : obj) {
                if (object != null) {
                     entities.add(object);
                }
            }



            Collections.sort(entities, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            for (Entity entity : entities) {
                entity.draw(g2);
            }

            ui.draw(g2);


        }
        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    /*
    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) { //se = sound effect
        sound.setFile(i);
        sound.play();
    }

     */

}