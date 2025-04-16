package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.setDefaultValues();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

    public void update() {
        if (this.kh.upPressed) {
            this.y -= this.speed;
        } else if (this.kh.downPressed) {
            this.y += this.speed;
        } else if (this.kh.leftPressed) {
            this.x -= this.speed;
        } else if (this.kh.rightPressed) {
            this.x += this.speed;
        }

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        int var10001 = this.x;
        int var10002 = this.y;
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        g2.fillRect(var10001, var10002, 64, 64);
    }
}

