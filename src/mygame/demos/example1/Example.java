/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.example1;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import mygame.engine.AudioPlayer;
import mygame.engine.Font;
import mygame.engine.SimpleGame;
import mygame.engine.Sprite;
import mygame.engine.TextureAtlas;
import mygame.engine.Window;
import mygame.engine.io.Keyboard;

/**
 *
 * @author jaime.tijerina01
 */
public class Example extends SimpleGame {
    private int speed = 10;
    private Font text;
    private TextureAtlas attlas;
    private AudioPlayer music;
    private Sprite player;
    private Sprite enemy;
    

    public Example(int width, int height) {
        
        super(width, height);
        
        //Debug
        Window.DEBUG = true;
        
        //Create Music
        music = new AudioPlayer("spaceArt/music.wav");
        //Create A Font 
        text = new Font("spaceArt/GoodDog.ttf");
        //Create an Atlas
        attlas = new TextureAtlas("spaceArt/mySpritesheet.xml");
        
        //Creting the Sprite Images
        player = attlas.getMovieClip("fly_");
        enemy = new Sprite("spaceArt/enemyShip.png");
        
        enemy.x = this.getWidth()/2; // positio enemy in the center
        enemy.y = this.getHeight()/2;
        
        music.setVolume(0.1f); // set volume (0.0 to 1.0) (loudest)
        music.play();//start playing
    }

    @Override
    public void draw(Graphics2D g) {
        //draw sprites and text to screen
        player.draw(g);
        enemy.draw(g);
        
        text.draw("This is A Text", 0, this.getHeight() - 60, 60, g);
    }

    @Override
    public boolean update() {
        this.enemy.rotation += 1;//rotate enemy
        
        player.update();//update the animation of the sprite

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_UP)) {
            player.y -= speed;
        }
        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_DOWN)) {

            player.y += speed;
        }
        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_LEFT)) {

            player.x -= speed;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_RIGHT)) {
            player.x += speed;

        }

        //make player to move inside an imaginary box
        player.walkWithinBox(0, 0, this.getWidth(), this.getHeight());

        return true; // must always return true
    }

}
