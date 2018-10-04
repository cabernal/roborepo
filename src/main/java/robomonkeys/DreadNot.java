package robomonkeys;

import robocode.*;
import robocode.Robot;

import java.util.Random;



import java.awt.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

public class DreadNot extends AdvancedRobot {
    /**
     * run: MyInitialRobot's default behavior
     */
    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        setColors(Color.blue, Color.blue, Color.blue); // body,gun,radar
        Random rand = new Random();
        double battleFieldHeight = this.getBattleFieldHeight();
        double battleFieldWidth = this.getBattleFieldWidth();
        double speed = 75;


        // Robot main loop
        while (true) {
            double x = this.getX();
            double y = this.getY();
            ahead(speed);
            turnRightRadians((Math.PI/16.0) * Math.cos(x + speed));
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        fire(50);
        turnLeft(45);

    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        turnLeft(45);
        ahead(150);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        turnLeft(180);
        ahead(150);
    }

    private void goToFieldCorner(double botX, double botY, double fieldX, double fieldY){
        // find corner

    }
}
