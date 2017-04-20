/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demo.shotter;

import java.awt.Graphics2D;
import mygame.engine.AudioPlayer;
import mygame.engine.SimpleGame;

/**
 *
 * @author jaime.tijerina01
 */
public class Shotter extends SimpleGame {

    private StartScreen sScreen;
    private Game inGame;
    private GameOverScreen gameOver;
    private final AudioPlayer music;

    public Shotter(int width, int height) {
        super(width, height);

        music = new AudioPlayer("spaceArt/music.wav");
        sScreen = new StartScreen();
        inGame = new Game();
        gameOver = new GameOverScreen(inGame);
        music.play();
        music.setVolume(0.2f);
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

        if(sScreen.getAlpha() == 0)
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
