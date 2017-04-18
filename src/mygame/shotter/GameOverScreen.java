package mygame.shotter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import mygame.engine.Font;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class GameOverScreen extends Sprite {

    Font text = new Font("spaceArt/GoodDog.ttf");
    private final Game game;
    public int type = -1;
    public boolean enable = false;

    GameOverScreen(Game game) {
        super("SShotter/Background/bg.png");
        this.game = game;
        this.x += this.width / 2;
        this.y += this.height / 2;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        String lc = "Game Over";
        text.draw(lc, 20, 140, 80, g);
        String sc = "Score: " + this.game.gameScore;
        text.draw(sc, 20, 140 + 80, sc.length() * 10, g);
        String pw = "PRESS ENTER TO RESTART";
        text.draw(pw, 15, 140 + 80 + sc.length() * 10, sc.length() * 4, g);
        String sw = "OR PRESS ESC TO RETURN TO MENU";
        text.draw(sw, 50, 140 + 80 + (sc.length() * 10) + (sc.length() * 4), sc.length() * 2, g);
    }

    @Override
    public void update() {

        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ESCAPE)) {
            this.type = 0;
            this.enable = false;
        }

        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ENTER)) {
            this.type = 1;
            this.enable = false;
        }
    }

    public int getType() {
        return this.type;
    }

    void setType(int i) {
        this.type = i;
    }
}
