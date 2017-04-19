/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame;
  
import mygame.demos.demo2.Demo2;
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

        ///Window win = new Window("SandBox", 800, 480, false); 
        //win.start(60, Shotter.class);
        
        //Window win = new Window("Space Shooter", 320, 568, false); 
        //win.start(60, Shotter.class);
        
        Window win = new Window("Demo2", 800, 480, false); 
        win.start(60, Demo2.class);
    }

}
