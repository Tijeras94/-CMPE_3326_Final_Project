/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Graphics2D;

/**
 *
 * @author jaime.tijerina01
 */
public interface IRender {
    
    /*
     * Draws to Screen
    */
     public void draw(Graphics2D g);
     
     /*
     * Must Turn True In Success
     */
     public boolean update();
}
