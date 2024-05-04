import java.util.ArrayList;
import java.util.List;

public class MarsRoverImpl implements MarsRover {

    private int x;
    private int y;
    private Direction direction;

    public MarsRoverImpl(Coordinates2D startingPosition, Direction startingDirection){
        x = startingPosition.x();
        y = startingPosition.y();
        direction = startingDirection;
    }

    @Override
    public void move(String commands){
        if (commands == null || commands.isBlank()) return;

        List<Character> validCommands = new ArrayList<>();
        validCommands.add('F');
        validCommands.add('B');
        validCommands.add('L');
        validCommands.add('R');

        char[] charCommands = commands.toCharArray();

        for (char command : charCommands){
            if (!validCommands.contains(command)) {
                System.out.println("WARN: Unknown command was used and ignored: " + command);
                continue;
            }

            switch (command){
                case 'F':
                    moveForward();
                    break;
                case 'B':
                    moveBack();
                    break;
                case 'L':
                    rotateLeft();
                    break;
                case 'R':
                    rotateRight();
                    break;
            }
        }
    }

    private void rotateLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
        }
    }

    private void rotateRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }

    private void moveForward() {
        switch (direction) {
            case NORTH -> moveY(1);
            case SOUTH -> moveY(-1);
            case EAST -> moveX(1);
            case WEST -> moveX(-1);
        }
    }

    private void moveBack() {
        switch (direction) {
            case NORTH -> moveY(-1);
            case SOUTH -> moveY(1);
            case EAST -> moveX(-1);
            case WEST -> moveX(1);
        }
    }

    private void moveY(int amount) {
        int maxY = 100;
        int minY = -100;

        y += amount;
        if (y > maxY)
            y = minY + (y - (maxY + 1));
        else if (y < minY)
            y = maxY - ((minY - 1) - y);
    }

    private void moveX(int amount) {
        int maxX = 100;
        int minX = -100;

        x += amount;
        if (x > maxX)
            x = minX + (x - (maxX + 1));
        else if (x < minX)
            x = maxX - ((minX - 1) - x);
    }

    @Override
    public Coordinates2D getCurrentLocation() {
        return new Coordinates2D(x, y);
    }
}
