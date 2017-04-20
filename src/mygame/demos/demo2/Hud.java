/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.demo2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import mygame.engine.Font;
import mygame.engine.Sprite;

/**
 *
 * @author jaime.tijerina01
 */
public class Hud extends Sprite {

    Font text = new Font("spaceArt/GoodDog.ttf");
    Sprite stage;
    int score = 0;
    int life = 0;

    public Hud(Sprite stage) {
        super(new BufferedImage((int) stage.getWidth(), (int) stage.getHeight(), BufferedImage.TYPE_INT_ARGB));
        this.stage = stage;
        init();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        String lc = "Health: " + this.life;
        text.draw(lc, this.x + 20, this.y + 20, 30, g);
        String sc = "Score: " + this.score;
        text.draw(sc, (int) ((stage.getWidth() - sc.length() * 10) - 5), this.y + 20, 30, g);
    }

    public void setScore(int x) {
        this.score = x;
    }

    public void setLives(int x) {
        this.life = x;
    }

    public void init() {
        this.score = this.life = 0;
    }

}
