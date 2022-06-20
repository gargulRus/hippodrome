import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {

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
    public void getHorses(){
        List<Horse> horses = generateHorses();
        assertEquals(new Hippodrome(horses).getHorses(), horses);
    }

    private List<Horse> generateHorses() {
        List<Horse> generateHorses = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            generateHorses.add(new Horse("Horse" + i, i,i));
        }
        return generateHorses;
    }

    @Test
    public void move(){
    }

    @Test
    public void getWinner(){}

}