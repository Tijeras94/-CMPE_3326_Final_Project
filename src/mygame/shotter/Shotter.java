/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.shotter;

import java.awt.Graphics2D;
import mygame.engine.SimpleGame;

/**
 *
 * @author jaime.tijerina01
 */
public class Shotter extends SimpleGame {

    private StartScreen sScreen;
    private Game inGame;
    private GameOverScreen gameOver;

    public Shotter(int width, int height) {
        super(width, height);

        sScreen = new StartScreen();
        inGame = new Game();
        gameOver = new GameOverScreen(inGame);
    }

    @Override
    public void draw(Graphics2D g) {
       
        if (inGame.isGameOver()) {
            gameOver.enable = true;
            gameOver.draw(g);
        }else
        {
             inGame.draw(g);
        }
        sScreen.draw(g);
    }

    @Override
    public boolean update() {

        inGame.update();
        gameOver.update();
        
        if (inGame.isGameOver() && gameOver.getType() == 0) {
            sScreen.setAlpha(1);
            sScreen.setEnable(true);
            inGame.init();
            gameOver.setType(-1);
        }
        
         if (inGame.isGameOver() && gameOver.getType() == 1) {
            inGame.init();
            gameOver.setType(-1);
        }
        
        sScreen.update();
        return true;
    }

}
