package StepicTasks;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class Robot {
    int x;
    int y;
    Direction direction;

    public Robot (int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.direction = dir;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void turnLeft()
    {
        if (direction == Direction.UP) {
            direction = Direction.LEFT;
        } else if (direction == Direction.DOWN)  {
            direction = Direction.RIGHT;
        } else if (direction == Direction.LEFT)  {
            direction = Direction.DOWN;
        } else if (direction == Direction.RIGHT) {
            direction = Direction.UP;
        }
    }

    public void turnRight()
    {
        if (direction == Direction.UP)    {
            direction = Direction.RIGHT;}
        else if (direction == Direction.DOWN)  {
            direction = Direction.LEFT;}
        else if (direction == Direction.LEFT)  {
            direction = Direction.UP;}
        else if (direction == Direction.RIGHT) {
            direction = Direction.DOWN;}
    }

    public void stepForward() {
        if (direction == Direction.UP)    {y++;}
        if (direction == Direction.DOWN)  {y--;}
        if (direction == Direction.LEFT)  {x--;}
        if (direction == Direction.RIGHT) {x++;}
    }
}

public class RobotGameTests
{
    public static void moveRobot(Robot robot, int toX, int toY)
    {
        while (Direction.RIGHT != robot.getDirection())
            robot.turnRight();

        if (robot.getX() > toX) {
            robot.turnLeft();
            robot.turnLeft();
        }

        toX = Math.abs(robot.getX() - toX);
        while (toX-- > 0)
            robot.stepForward();

        if (toY > robot.getY()) {
            if (Direction.RIGHT == robot.getDirection())
                robot.turnLeft();
            else
                robot.turnRight();
        } else {
            if (Direction.LEFT == robot.getDirection())
                robot.turnLeft();
            else
                robot.turnRight();
        }

        toY = Math.abs(robot.getY() - toY);
        while (toY-- > 0)
            robot.stepForward();

        System.out.println(robot.getX() + ", " + robot.getY());
    }

    public static void main(String[] args) {
        Robot robot = new Robot(-410,-310, Direction.DOWN);
        moveRobot(robot, -20, 0);
    }
}
