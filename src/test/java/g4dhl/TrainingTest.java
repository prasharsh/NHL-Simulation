package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class TrainingTest {

    @Test
    public void setDaysUntilStatIncreaseTest(){
        Training training = new Training();
        Assert.assertFalse("DaysUntilStatIncrease cannot be negative", training.setDaysUntilStatIncreaseCheck(-1));
    }

    @Test
    public void setValidDaysUntilStatIncreaseTest(){
        Training training = new Training();
        Assert.assertTrue("DaysUntilStatIncrease is saved", training.setDaysUntilStatIncreaseCheck(50));
    }

    @Test
    public void getTrainingIdTest() {
        Training training = new Training();
        training.setTrainingId(10);
        Assert.assertEquals(10, training.getTrainingId());
    }

    @Test
    public void getDaysUntilStatIncreaseTest(){
        Training training = new Training();
        training.setDaysUntilStatIncreaseCheck(30);
        Assert.assertEquals(30, training.getDaysUntilStatIncreaseCheck());
    }
}
