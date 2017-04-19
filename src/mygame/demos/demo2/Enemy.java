package mygame.demos.demo2;

import mygame.engine.MovieClip;
import mygame.engine.Sprite;
import mygame.engine.TextureAtlas;

class Enemy extends MovieClip {

    private Sprite stage;
    private int speed;
    public int Health;
    public int Damage;
    public int Value;
    public boolean isActive;

    public Enemy(Sprite stage, int _speed, TextureAtlas texture) {
        super(texture.getTextures("mine"));
        this.stage = stage;
        speed = _speed;

        // Set the health of the enemy
        Health = 10;

        // Set the amount of damage the enemy can do
        Damage = 10;

        // Set the score value of the enemy
        Value = 100;

        isActive = true;
    }

    public void update() {
        super.update();
        this.x -= speed;

        if (this.x + this.width + 100 < 0 || Health <= 0) {
            isActive = false;
        }
    }
}
