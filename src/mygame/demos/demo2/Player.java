/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.demo2;

import java.awt.event.KeyEvent;
import mygame.engine.MovieClip;
import mygame.engine.Sprite;
import mygame.engine.TextureAtlas;
import mygame.engine.io.Keyboard;

/**
 *
 * @author jaime.tijerina01
 */
public class Player extends MovieClip {

    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean shoot;
    private boolean moveRight;
    public int Health;
    public boolean isActive;
    private int moveSpeed;
    private int shootCoolDownCounter;
    private int shootCoolDownMax;
    Game parent;

    public Player(Game parent, TextureAtlas texture) {
        super(texture.getTextures("ship"));
        this.setCenterPivot(false);
        this.parent = parent;
        init();
    }

    @Override
    public void update() {
        super.update();
        shootCoolDownCounter++;

        if (this.moveLeft) {
            this.x -= moveSpeed;
        }

        if (this.moveRight) {
            this.x += moveSpeed;
        }

        if (this.moveUp) {
            this.y -= moveSpeed;
        }

        if (this.moveDown) {
            this.y += moveSpeed;
        }

        if (this.x <= 0) {
            this.x = 0;
        }

        if (this.x >= this.parent.getWidth() - this.width) {
            this.x = (int) this.parent.getWidth() - this.width;
        }

        if (this.y <= 0) {
            this.y = 0;
        }

        if (this.y >= this.parent.getHeight() - this.height) {
            this.y = (int) this.parent.getHeight() - this.height;
        }

        if (this.shoot) {
            if (shootCoolDownCounter > shootCoolDownMax) {
                //laserSound.play();
                shootCoolDownCounter = 0; 
                parent.AddProjectile();
            }
        }

        keyboardH();

    }

    public void keyboardH() {

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_UP)) {

            moveUp = true;
        } else {
            moveUp = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_DOWN)) {
            moveDown = true;
        } else {
            moveDown = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_LEFT)) {

            moveLeft = true;
        } else {
            moveLeft = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_RIGHT)) {
            moveRight = true;
        } else {
            moveRight = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_SPACE)) {
            shoot = true;
        } else {
            shoot = false;
        }
    }

    public void init() {
        // Set the player health
        Health = 100;

        // Set the player to be active
        isActive = true;

        moveSpeed = 10;

        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
        shoot = false;

        shootCoolDownCounter = 10;
        shootCoolDownMax = 10;
    }

}
