/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.demo2;

import java.awt.Graphics2D;
import mygame.engine.SimpleGame;

/**
 *
 * @author jaime.tijerina01
 */
public class Demo2 extends SimpleGame {

    Game gm;

    public Demo2(int width, int height) {
        super(width, height);
        gm = new Game();
    }

    @Override
    public void draw(Graphics2D g) {
        gm.draw(g);
    }

    @Override
    public boolean update() {
        gm.update();
        return true;
    }

}
