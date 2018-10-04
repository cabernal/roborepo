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
        double WEST_BOUNDARY = 20;
        double EAST_BOUNDARY = getBattleFieldWidth() - 20;
        double NORTH_BOUNDARY = getBattleFieldHeight() - 20;
        double SOUTH_BOUNDARY = 20;

        setColors(Color.blue, Color.blue, Color.blue); // body,gun,radar

        int currentWall = goToRandomWall();
        turnLeft(90);
//        turnGunLeft(90);
//        turnLeftOrRight(currentWall);
//        goToCornerAlongWall();

        double speed = 75;
        double angleFrag = 4;


        int tick = 0;
        // Robot main loop
        while (true) {
            tick = (tick + 1) % 2;
            if (tick == 1) {
                turnGunLeft(180);
            }
            else {
                turnGunRight(180);
            }
            double currentX = getX();
            double currentY = getY();

//            if (currentX > WEST_BOUNDARY && currentX < EAST_BOUNDARY && currentY > SOUTH_BOUNDARY && currentY < NORTH_BOUNDARY) {
//                ahead(20);
//            }
//            double x = this.getX();
//            double y = this.getY();
            ahead(speed);
//            turnRightRadians((Math.PI/16.0) * Math.cos(x + speed));
            turnGunRight(180);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // guess where the enemy is heading
        fire(50);
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
//        double wallDirection = e.getBearing();
//        onHitObject(wallDirection);
        goToRandomWall();
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
        goToRandomWall();
    }

    private int getRandomInt(int max) {
        return randomNumberGenerator.nextInt(max) + 1;
    }

    private void goToFieldCorner(double botX, double botY, double fieldX, double fieldY){
        // find corner

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

    private void goToCornerAlongWall() {
        if (getHeading() == 0) {
            ahead(getBattleFieldHeight() - getY() - 20);
        }
        else if (getHeading() == 90) {
            ahead(getBattleFieldWidth() - getX() - 20);
        }
        else if (getHeading() == 180) {
            ahead(getY() - 20);
        }
        else {
            ahead(getX() - 20);
        }
    }

    private void turnLeftOrRight(int currentWall) {
        int randomDirection = getRandomInt(2);
        if (randomDirection == LEFT) {
            turnLeft(90);
//            turnGunLeft(90);
        }
        else {
            turnRight(90);
//            turnGunRight(90);
        }

        double gunHeading = getGunHeading();
        if (currentWall == NORTH) {
            if (gunHeading < 180) {
                turnGunRight(180 - gunHeading);
            }
            else {
                turnGunLeft(gunHeading - 180);
            }
        }
        else if (currentWall == EAST) {
            if (gunHeading > 270 || gunHeading < 90) {
                turnGunLeft((gunHeading + 90) % 360);
            }
            else {
                turnGunRight(270 - gunHeading);
            }
        }
        else if (currentWall == SOUTH) {
            if (gunHeading < 180) {
                turnGunLeft(gunHeading);
            }
            else {
                turnGunRight(360 - gunHeading);
            }
        }
        else {
            if (gunHeading > 270 || gunHeading < 90) {
                turnGunRight((90 - gunHeading + 360) % 360);
            }
            else {
                turnGunLeft(gunHeading - 90);
            }
        }
    }

}
