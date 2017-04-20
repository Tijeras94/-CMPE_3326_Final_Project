package mygame.demo.shotter;

import mygame.engine.Sprite;

class Bullet extends Sprite {

    private int speed;
    private boolean toRemove = false;

    public boolean isToRemove() {
        return toRemove;
    }

    public void setRemove(boolean b) {
        toRemove = b;
    }

    public Bullet() {
        super("SShotter/laserRed.png");
        speed = 10;
    }

    @Override
    public void update() {
        this.y -= speed;

        if (this.y < 0) {
            this.toRemove = true;
        }
    }
}
