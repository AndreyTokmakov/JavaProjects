package StepicTasks;

interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}

interface RobotConnectionManager {
    public RobotConnection getConnection();
}


class RobotConnectionImpl implements RobotConnection
{
    private static int throwException = 2;
    private static int throwExceptionOther = 0;

    public void moveRobotTo(int x, int y) {
        if (throwException > 0) {
            --throwException;
            throw new RobotConnectionException("Failed to move robot!");
        }
        if (throwExceptionOther  > 0) {
            --throwExceptionOther;
            throw new RuntimeException("Shit has happen!");
        }
        System.out.println(String.format("Robot moved to [%d, %d]", x, y));
    }

    @Override
    public void close() {
        if (false)
            throw new RobotConnectionException("Close connection exception");
        System.out.println("Connection closed");
    }
}

class RobotConnectionManagerImpl implements RobotConnectionManager
{
    private static int exceptionsToThrow = 0;

    public RobotConnection getConnection() {
        if (exceptionsToThrow > 0) {
            --exceptionsToThrow;
            throw new RobotConnectionException("Failed to connect");
        }
        return new RobotConnectionImpl();
    }
}

class RobotConnectionException extends RuntimeException {
    public RobotConnectionException(String message) {
        super(message);
    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

class RobotTests
{
    private void CloseConnectionUnsafe(RobotConnection connection) {
        if (null != connection) {
            try {
                connection.close();
            }  catch (final Exception exception) {
            }
        }
    }

    private void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        for (int i = 0; i < 3; ++i) {

            try (final RobotConnection connection = robotConnectionManager.getConnection()) {
                connection.moveRobotTo(toX, toY);
                return;
            } catch (final RobotConnectionException exc) {
                System.err.println("We've got RobotConnectionException:" + exc);
            } catch (final Exception exc) {
                throw new RobotConnectionException(exc.getMessage(), exc);
            } finally {
                // System.out.println("Finally block called");
            }
        }
        throw new RobotConnectionException("Failed");
    }

    private void moveRobot2(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        for (int i = 0; i < 3; ++i) {
            RobotConnection connection = null;
            try {
                connection = robotConnectionManager.getConnection();
                connection.moveRobotTo(toX, toY);
                CloseConnectionUnsafe(connection);
                connection = null;
                return;
            } catch (final RobotConnectionException exc) {
                CloseConnectionUnsafe(connection);
                connection = null;
            } catch (final Exception exc) {
                CloseConnectionUnsafe(connection);
                connection = null;
                throw new RobotConnectionException(exc.getMessage(), exc);
            }
            CloseConnectionUnsafe(connection);
            connection = null;
        }
        throw new RobotConnectionException("Failed");
    }

    public void MoveRobotTest() {
        RobotConnectionManagerImpl connector = new RobotConnectionManagerImpl();
        moveRobot2(connector, 5, 5);
        System.out.println("OK. Done!");
    }
}


public class RobotExceptionsTests
{
    public void func() throws Exception {
        System.out.println("func() called");
        throw new Exception("Exception from func!!!");
    }

    public void TestFinaly() {
        try {
            func();
            // System.exit(0);
        } catch (Exception e) {
            System.out.println("Exception !");
            throw new RuntimeException("New RuntimeException!");
        } finally {
            System.out.println("Finally() called");
        }
    }

    public static void main(String[] args)
    {
        // new RobotExceptionsTests().TestFinaly();

        RobotTests tests =  new RobotTests();
        tests.MoveRobotTest();
    }
}
