package robomonkeys;

import robocode.*;
import robocode.Robot;

import java.util.Random;



import java.awt.*;
import java.util.Random;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

public class DreadNot extends AdvancedRobot {

    private Random randomNumberGenerator = new Random();
    private static int AHEAD = 1;
    private static int BACK = 2;

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
        double angleFrag = 4;


        // Robot main loop
        while (true) {
            double x = this.getX();
            double y = this.getY();
            ahead(speed);
            turnRightRadians((Math.PI/16.0) * Math.cos(x + speed));
            turnGunRight(180);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // guess where the enemy is heading
        setFire(Math.min(400 / e.getDistance(), 3));

        // If our target is too far away, turn and move toward it.
        if (e.getDistance() > 150) {
            double gunTurnAmt = robocode.util.Utils.normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

            turnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,
            turnRight(e.getBearing()); // and see how much Tracker improves...
            // (you'll have to make Tracker an AdvancedRobot)
            ahead(e.getDistance() - 140);
            return;
        }

        // Our target is close.
        double gunTurnAmt = robocode.util.Utils.normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        turnGunRight(gunTurnAmt);
        fire(3);

        // Our target is too close!  Back up.
        if (e.getDistance() < 100) {
            if (e.getBearing() > -90 && e.getBearing() <= 90) {
                back(40);
            } else {
                ahead(40);
            }
        }
        scan();
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        String assailant = e.getName();
        System.out.println("Curse you, " + assailant + "!");

        double bulletDirection = e.getBearing();

        if (bulletDirection < 0) {
            turnRight(bulletDirection + 90);
        } else {
            turnLeft( bulletDirection - 90);
        }

        int randomDistance = getRandomInt(100);
        int randomDirection = getRandomInt(2);

        if (randomDirection == AHEAD) {
            ahead(randomDistance);
        }
        else {
            back(randomDistance);
        }
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        double wallDirection = e.getBearing();
        onHitObject(wallDirection);
    }

    private void onHitObject(double wallDirection) {
        if (wallDirection < 0) {
            turnLeft(wallDirection + 135);
        }
        else {
            turnRight(wallDirection - 135);
        }
        ahead(100);
    }

    @Override
    public void onHitRobot(HitRobotEvent e){
        onHitObject(e.getBearing());
    }

    private int getRandomInt(int max) {
        return randomNumberGenerator.nextInt(max) + 1;
    }

    private void goToFieldCorner(double botX, double botY, double fieldX, double fieldY){
        // find corner

    }
}
