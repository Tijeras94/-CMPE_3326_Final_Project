package mygame.shotter;

import mygame.engine.Sprite;

class Enemy2 extends Enemy {
    public Enemy2(String pimg, Sprite stage, int _damage, int _speed) {
        super(pimg, stage, _damage, _speed);
    }
    
    @Override
    public void update() {
        super.update();
        this.rotation = 1;

    }
    

}
