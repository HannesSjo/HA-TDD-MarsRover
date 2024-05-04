import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTests {

    @Test
    public void testMoveForwardWest() {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(3, 3), Direction.WEST);

        //Act
        rover.move("F");

        //Assert
        Assertions.assertEquals(new Coordinates2D(2, 3), rover.getCurrentLocation());
    }

    @Test
    public void testNullInsert() {
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(3, 3), Direction.WEST);
        rover.move(null);
        Assertions.assertEquals(new Coordinates2D(3, 3), rover.getCurrentLocation());
    }


    @Test
    public void testMoveBackwardSouth() {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(70, 70), Direction.SOUTH);

        //Act
        rover.move("B");

        //Assert
        Assertions.assertEquals(new Coordinates2D(70, 71), rover.getCurrentLocation());
    }

    @Test
    public void testRotation() {
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(0, 0), Direction.NORTH);
        rover.move("F");
        Assertions.assertEquals(new Coordinates2D(0, 1), rover.getCurrentLocation());
        rover.move("R");
        rover.move("F");
        Assertions.assertEquals(new Coordinates2D(1, 1), rover.getCurrentLocation());
        rover.move("L");
        rover.move("F");
        Assertions.assertEquals(new Coordinates2D(1, 2), rover.getCurrentLocation());
    }

    @Test
    public void testMultipleInputs(){
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(0, 0), Direction.NORTH);
        rover.move("FFRBLB");
        Assertions.assertEquals(new Coordinates2D(-1, 1), rover.getCurrentLocation());
    }

    @Test
    public void testWrapping(){
        MarsRover rover = new MarsRoverImpl(new Coordinates2D(100, 100), Direction.NORTH);
        rover.move("F");
        Assertions.assertEquals(new Coordinates2D(100, -100), rover.getCurrentLocation());
        rover.move("RF");
        Assertions.assertEquals(new Coordinates2D(-100, -100), rover.getCurrentLocation());


        rover = new MarsRoverImpl(new Coordinates2D(-100, -100), Direction.SOUTH);
        rover.move("F");
        Assertions.assertEquals(new Coordinates2D(-100, 100), rover.getCurrentLocation());
        rover.move("RF");
        Assertions.assertEquals(new Coordinates2D(100, 100), rover.getCurrentLocation());
    }
}
