/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos;

import mygame.demos.demo2.Demo2;
import mygame.demos.example1.Example;
import mygame.engine.Window;
import mygame.demo.shotter.Shotter;
import mygame.demos.sandbox.GameTest;

/**
 *
 * @author jaime.tijerina01
 */
public class Main {

    /**
     * Main Program Entry
     */
    public static void main(String[] args) {

        //Window win3 = new Window("Example", 800, 480, false); 
        //win3.start(60, Example.class);
        //Window win0 = new Window("SandBox", 800, 480, true); 
        //win0.start(60, GameTest.class);
        //Window win1 = new Window("Space Shooter", 320, 568, false); 
        //win1.start(60, Shotter.class);
        Window win2 = new Window("Space Shooter 2", 800, 480, false);
        win2.start(60, Demo2.class);

    }

}
