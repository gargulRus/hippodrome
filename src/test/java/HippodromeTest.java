import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HippodromeTest {

    public Hippodrome hippodrome;
    public List<Horse> horses = new ArrayList<>();
    public List<Horse> mockHorses = new ArrayList<>();

    @BeforeAll
    public void init() {
        horses = generateHorses();
        mockHorses = generateMockHorses();
        hippodrome = new Hippodrome(horses);
    }

    private List<Horse> generateHorses() {
        List<Horse> generateHorses = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            generateHorses.add(new Horse("Horse" + i, i,i));
        }
        return generateHorses;
    }
    private List<Horse> generateMockHorses() {
        List<Horse> generateHorses = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            generateHorses.add(Mockito.mock(Horse.class, "TestHorse" + i));
        }
        return generateHorses;
    }

    @Test
    public void testConstructorFirstParamNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorFirstParamEmpty(){
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void testGetHorses(){
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMove(){
        Hippodrome mockHippodrome = new Hippodrome(mockHorses);
        mockHippodrome.move();
        for(Horse horse : mockHorses) {
            verify(horse, times(1)).move();
        }
    }

    @Test
    public void testGetWinner(){
        assertEquals(29, hippodrome.getWinner().getDistance());
    }

}