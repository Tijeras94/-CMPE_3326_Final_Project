package mygame.demos.demo2;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import mygame.engine.Font;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class GameOverScreen extends Sprite {

    Font text = new Font("spaceArt/GoodDog.ttf");

    private final Demo2 game;
    public boolean enable = true;
    private double fade = 0;

    GameOverScreen(Demo2 game) {
        super("Demo2Assets/endMenu.png");
        this.game = game;
        this.setCenterPivot(false);

    }

    public void reset() {
        enable = true;
        fade = 1;
    }

    @Override
    public void draw(Graphics2D l) {
        BufferedImage img = new BufferedImage(this.img.getWidth(), this.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        super.draw(g);
        int v = 200;
        String nm = "" + 1000;
        String sc = "Score: " + this.game.gameScore;
        text.draw(sc, v+ 100 - (nm.length()*10), 140 + 80, sc.length() * 10, g);
        String pw = "PRESS ESC TO RETURN TO MENU";
        text.draw(pw, v+ 15, 140 + 80 + sc.length() * 10, sc.length() * 4, g); 

        modAlpha(img, fade);
        l.drawImage(img, null, x, y);
        img.flush();
    }

    @Override
    public void update() {

        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ESCAPE)) {
            this.enable = false;
        }

        if (!this.enable) {
            fadeout();

            game.mn.reset();
            if (this.fade == 0) {
                this.game.scene = 0;
            }
        } else {
            fadein();
        }
    }

    void fadeout() {
        this.fade -= 0.05;
        if (this.fade <= 0.0f) {
            this.fade = 0.0f;
            this.enable = false;
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
            this.enable = true;
        }
    }
}
