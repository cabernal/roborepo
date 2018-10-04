package robomonkeys;

import robocode.*;

import java.util.Random;



import java.awt.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

public class DreadNot extends AdvancedRobot {

    private Random randomNumberGenerator = new Random();
    private static int AHEAD = 1;
    private static int BACK = 2;

    private static int NORTH = 1;
    private static int EAST = 2;
    private static int SOUTH = 3;
    private static int WEST = 4;

    private static int LEFT = 1;
    private static int RIGHT = 2;


    /**
     * run: MyInitialRobot's default behavior
     */
    public void run() {
        setColors(Color.blue, Color.black, Color.black); // body,gun,radar

        int currentWall = goToRandomWall();
        turnLeft(90);
        turnGunLeft(90);

        double speed = 75;
        double angleFrag = 4;


        int tick = 0;
        // Robot main loop
        while (true) {
            ahead(speed);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // guess where the enemy is heading
        fire(1);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        turnLeft(90);
    }

    @Override
    public void onHitRobot(HitRobotEvent e){
        goToRandomWall();
    }

    private int getRandomInt(int max) {
        return randomNumberGenerator.nextInt(max) + 1;
    }

    private int goToRandomWall() {
        int randomWall = getRandomInt(4);
        double startingHeading = getHeading();
        double wallHeading = (randomWall - 1) * 90;
        double amountToTurn = startingHeading - wallHeading;
        turnLeft(amountToTurn);

        if (randomWall == EAST) {
            double currentX = getX();
            ahead(getBattleFieldWidth() - currentX - 20);
        }
        else if (randomWall == WEST) {
            double distance = getX();
            ahead(distance - 20);
        }
        if (randomWall == NORTH) {
            double currentY = getY();
            ahead(getBattleFieldHeight() - currentY - 20);
        }
        else if (randomWall == SOUTH) {
            double distance = getY();
            ahead(distance - 20);
        }
        return randomWall;
    }
}
