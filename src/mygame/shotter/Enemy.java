package mygame.shotter;

import mygame.engine.Sprite;

class Enemy extends Sprite {

    private int speed;
    public int damage;
    private Sprite stage;
    private boolean toRemove = false;

    public Enemy(Sprite stage, int _damage, int _speed) {
        super("SShotter/meteorSmall.png");
        this.stage = stage;
        this.speed = _speed;
        this.damage = _damage;
    }

    @Override
    public void update() {
        this.y += speed;
        this.rotation += speed;

        if (this.y > stage.getHeight() + this.getHeight()) {
            this.toRemove = true;
        }
    }

    public void setRemove(boolean b) {
        toRemove = b;
    }

    public boolean isToRemove() {
        return toRemove;
    }
}
