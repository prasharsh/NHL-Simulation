package g4dhl;
import org.junit.Assert;
import org.junit.Test;

public class HeadCoachTest {

    @Test
    public void setHeadCoachNameEmptyTest(){
        HeadCoach headCoach = new HeadCoach();
        Assert.assertFalse("Head Coach cannot be empty", headCoach.setHeadCoachName(""));
    }

    @Test
    public void setHeadCoachNameNullTest(){
        HeadCoach headCoach = new HeadCoach();
        Assert.assertFalse("Head Coach cannot be null", headCoach.setHeadCoachName(null));
    }
}
