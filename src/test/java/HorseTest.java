import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorseTest {

//    TODO Constructor Tests
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
    public void getName(){
        Assert.assertEquals(horseName, horseTwoParam.getName());
    }

    @Test
    public void getSpeed(){
        Assert.assertEquals(speed, horseThreeParam.getSpeed());
    }

    @Test
    public void getDistance(){
        Assert.assertEquals(distance, horseThreeParam.getDistance());
    }

    @Test
    public void move(){}
}
