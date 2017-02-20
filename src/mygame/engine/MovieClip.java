/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

/**
 *
 * @author jaime.tijerina01
 */
public class MovieClip extends Sprite {

    SubTextureAttrib[] textures;
    private int currentFrame = 0;
    private int frameCount = 0; 
    
    private int FramesPerSec = 0;
    private int counter = 0;

    public MovieClip(SubTextureAttrib[] textures) {
       super(textures[0].getTexture());
        this.frameCount = textures.length;
        this.textures = textures;
        FramesPerSec = 20;
    }
    
    public MovieClip(SubTextureAttrib[] textures, int fps) {
        super(textures[0].getTexture());
        this.frameCount = textures.length;
        this.textures = textures;
        FramesPerSec = fps;
    }
    
    public void setFramesPerSec(int fps){
        FramesPerSec = fps;
    }

    @Override
    public void update() {
        counter++;
         
        if (counter >= Fps.getInstance().getFps()/FramesPerSec) {
            currentFrame++;
            counter = 0;
        } 
        
        img = textures[currentFrame % frameCount].getTexture();
        super.update();
    }
}
