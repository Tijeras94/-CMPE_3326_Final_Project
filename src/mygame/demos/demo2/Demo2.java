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

    Menu mn;
    public Game gm;
    GameOverScreen over;

    public int scene = 0;
    public int gameScore = 0;

    public Demo2(int width, int height) {
        super(width, height);
        gm = new Game(this);
        over = new GameOverScreen(this);
        mn = new Menu(this);
    }

    @Override
    public void draw(Graphics2D g) {
        gm.draw(g);
        mn.draw(g);
        over.draw(g);
    }

    @Override
    public boolean update() { 
        switch (scene) {
            case 0:
                mn.update();
                break;
            case 1:
                gm.update();
                break;
            case 2:
                over.update();
                break;
            default:
                break;
        }

        return true;
    }

}
