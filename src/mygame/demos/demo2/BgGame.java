/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.demo2;

import java.awt.Graphics2D;
import mygame.engine.ParallaxHSprite;
import mygame.engine.Sprite;

/**
 *
 * @author jaime.tijerina01
 */
public class BgGame extends Sprite {

    ParallaxHSprite bgLayer1 = new ParallaxHSprite("Demo2Assets/bgLayer1.png", 5);
    ParallaxHSprite bgLayer2 = new ParallaxHSprite("Demo2Assets/bgLayer1.png", 10);

    public BgGame() {
        super("Demo2Assets/mainbackground.png");

        this.setCenterPivot(false);
        bgLayer1.setCenterPivot(false);
        bgLayer2.setCenterPivot(false);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        bgLayer1.draw(g);
        bgLayer2.draw(g);
    }

    @Override
    public void update() {
        bgLayer1.update();
        bgLayer2.update();
    }
}
