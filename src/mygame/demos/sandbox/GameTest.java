/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.sandbox;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import mygame.engine.AudioPlayer;
import mygame.engine.Font;
import mygame.engine.SimpleGame;
import mygame.engine.Sprite;
import mygame.engine.TextureAtlas;
import mygame.engine.io.Keyboard;
import mygame.engine.io.MouseManager;

/**
 *
 * @author jaime.tijerina01
 */
public class GameTest extends SimpleGame {

    int i = 0;
    int speed = 10;

    TextureAtlas attlas = new TextureAtlas("spaceArt/mySpritesheet.xml");
    Sprite player = new Sprite("spaceArt/player.png");
    Sprite enemy = new Sprite("spaceArt/enemyShip.png");
    List<Sprite> bullets = new ArrayList<>();
    AudioPlayer laser;
    Sprite as;

    Sprite hero;
    
    Font text = new Font("spaceArt/GoodDog.ttf");

    public GameTest(int width, int height) {
        super(width, height);

        enemy = attlas.getMovieClip("fly_");
        enemy.x = width / 2;
        enemy.y = height / 2;
        as = attlas.getMovieClip("fly_");
        as.x = as.y = 100;
        hero = attlas.getMovieClip("watchOut_");
        hero.x = hero.y = 250; 
    }

    @Override
    public void draw(Graphics2D g) {
       
        player.draw(g);
        enemy.draw(g);
        for (int j = 0; j < bullets.size(); j++) {
            bullets.get(j).draw(g);

        }

        as.draw(g);

        hero.draw(g);
        text.draw("Hello World", this.getWidth()/2, 0, 60, g);
    }

    @Override
    public boolean update() {

        hero.update();
        as.update();

        laser = null;
        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_R)) {
            player.y = player.x = 0;

        }

        player.rotation = MouseManager.getInstance().getAngle(player.x, player.y) - 90;
        enemy.rotation += 1;

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

        player.walkWithinBox(0, 0, this.getWidth(), this.getHeight());

        if (MouseManager.getInstance().isKeyPress(MouseManager.M_CLICK_RIGHT) || Keyboard.getInstance().isKeyPress(KeyEvent.VK_SPACE)) {
            Sprite b = new Sprite("spaceArt/laserGreen.png");
            b.x = player.x;
            b.y = player.y;
            b.width = 9;
            b.height = 9;
            b.setRotationOffset(90);
            b.rotation = player.rotation - 90;
            bullets.add(b);
            laser = new AudioPlayer("spaceArt/sound/laser.wav", true);
            laser.play();;
        } else if (MouseManager.getInstance().isKeyPress(MouseManager.M_CLICK_LEFT)) {
            Sprite b = new Sprite("spaceArt/laserRed.png");
            b.x = player.x;
            b.y = player.y;
            b.width = 9;
            b.height = 9;
            b.setRotationOffset(90);
            b.rotation = player.rotation - 90;
            bullets.add(b);
            laser = new AudioPlayer("spaceArt/sound/laser.wav", true);
            laser.play();;

        }

        for (int j = 0; j < bullets.size(); j++) {

            double rot = bullets.get(j).rotation;
            double x = Math.cos(Math.toRadians(rot));
            double y = Math.sin(Math.toRadians(rot));

            Sprite b = bullets.get(j);

            b.x += (int) (x * 20);
            b.y += (int) (y * 20);

            if (b.outOfSigth(this.getWidth(), this.getHeight())) {
                b.destroy();

                bullets.remove(j);
            }

        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_ESCAPE)) {
            return false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_Z)) {
            System.gc();
        }

        return true;
    }

}
