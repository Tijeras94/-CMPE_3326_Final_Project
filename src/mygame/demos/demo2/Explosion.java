package mygame.demos.demo2;

import mygame.engine.MovieClip;
import mygame.engine.TextureAtlas;

class Explosion extends MovieClip {

    boolean isActive;

    public Explosion(Game parent, TextureAtlas texture) {
        super(texture.getTextures("explosion"));
        
        this.setIsLoop(false);
        isActive = true; 

    }

    public void update() {
        super.update();
        isActive = !this.done;
    }
}
