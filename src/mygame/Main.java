/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame;
  
import mygame.engine.Window;

/**
 *
 * @author jaime.tijerina01
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        Window win = new Window("Sime Game", 900, 600, false); 
        win.start(60, GameTest.class);
    }

}
