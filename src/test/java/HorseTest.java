import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorseTest {

    public String horseName = "HorseName";
    public double speed = 2;
    public double distance = 1;

    public Horse horseTwoParam;
    public Horse horseThreeParam;

    @BeforeAll
    public void init() {
        horseTwoParam = new Horse(horseName, speed);
        horseThreeParam = new Horse(horseName, speed, distance);
    }

    @Test
    public void testConstructorFirstParamNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    static Stream<String> argsForTestConstructorFistParamBlank() {
        return Stream.of("", " ", "    ");
    }

    @ParameterizedTest
    @MethodSource("argsForTestConstructorFistParamBlank")
    public void testConstructorFirstParamBlank(String testArgument){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(testArgument, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testConstructorSecondParamNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("testName", -2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructorThirdParamNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("testName", 2, -3));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetName(){
        assertEquals(horseName, horseTwoParam.getName());
    }

    @Test
    public void testGetSpeed(){
        assertEquals(speed, horseThreeParam.getSpeed());
    }

    @Test
    public void testGetDistance(){
        assertEquals(distance, horseThreeParam.getDistance());
    }

    @Test
    public void testGetDistanceZero(){
        assertEquals(0, horseTwoParam.getDistance());
    }

    static Stream<Double> argsForTestMoveMethod() {
        return Stream.of(0.1, 20.2, 0.5);
    }

    @Test
    public void testGetRandomDoubleCallInMoveMethod(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            //horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.2);
            Horse horse = new Horse("test", speed,distance);
            horse.move();
            horseMockedStatic.verify(
                    ()->Horse.getRandomDouble(0.2,0.9)
            );
        }
    }

    @ParameterizedTest
    @MethodSource("argsForTestMoveMethod")
    public void testingDistanceCountInMoveMethod(Double randDouble){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randDouble);
            Horse horse = new Horse("test", speed,distance);
            horse.move();
            double expectedDistance = distance + speed * randDouble;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }

}
