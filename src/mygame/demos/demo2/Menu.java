package mygame.demos.demo2;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import mygame.engine.AudioPlayer;
import mygame.engine.Font;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class Menu extends Sprite {

    Font text = new Font("spaceArt/GoodDog.ttf");
    AudioPlayer musicSound = new AudioPlayer("Demo2Assets/Sound/menuMusic.wav");
    private final Demo2 game;
    public int type = -1;
    public boolean enable = false;
    private double fade = 1;

    Menu(Demo2 game) {
        super("Demo2Assets/mainMenu.png");
        this.game = game;
        this.setCenterPivot(false);
        this.reset();
    }

    public void reset() {
        if (!enable) {
            musicSound.play();
        }

        enable = true;
        fade = 1;

    }

    @Override
    public void draw(Graphics2D l) {
        BufferedImage img = new BufferedImage(this.img.getWidth(), this.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        super.draw(g);
        String pw = "PRESS (SPACE) TO START";
        text.draw(pw, 200, 300, 50, g);
        modAlpha(img, fade);
        l.drawImage(img, null, x, y);

        img.flush();
    }

    @Override
    public void update() {
        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_SPACE)) {
            this.enable = false;
        }

        if (!this.enable) {
            fadeout();
            if (this.fade == 0) {
                this.game.scene = 1;
                this.game.gm.init();
                musicSound.stop();
            }
        } else {
            fadein();
        }
    }

    void fadeout() {
        this.fade -= 0.05;
        if (this.fade <= 0.0f) {
            this.fade = 0.0f;
        }
        if (this.fade >= 1.0f) {
            this.fade = 1.0f;
        }
    }

    void fadein() {
        this.fade += 0.1;
        if (this.fade <= 0.0f) {
            this.fade = 0.0f;
        }
        if (this.fade >= 1.0f) {
            this.fade = 1.0f;
        }
    }
}
