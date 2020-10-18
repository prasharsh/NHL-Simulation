package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class AgingTest {
    @Test
    public void setNegativeRetirementAgeTest(){
        Aging aging = new Aging();
        Assert.assertFalse("Retirement age cannot be negative", aging.setAverageRetirementAge(-1));
    }

    @Test
    public void setZeroRetirementAgeTest(){
        Aging aging = new Aging();
        Assert.assertFalse("Retirement age cannot be zero", aging.setAverageRetirementAge(0));
    }

    @Test
    public void setValidRetirementAgeTest(){
        Aging aging = new Aging();
        Assert.assertTrue("Retirement age is saved", aging.setAverageRetirementAge(50));
    }

    @Test
    public void getRetirementAgeTest(){
        Aging aging = new Aging();
        aging.setAverageRetirementAge(30);
        Assert.assertEquals(30, aging.getAverageRetirementAge());
    }

    @Test
    public void getAgingIdTest(){
        Aging aging = new Aging();
        aging.setAgingId(30);
        Assert.assertEquals(30, aging.getAgingId());
    }

    @Test
    public void setNegativeMaximumAgeTest(){
        Aging aging = new Aging();
        Assert.assertFalse("Retirement age cannot be negative", aging.setMaximumAge(-1));
    }

    @Test
    public void setZeroMaximumAgeTest(){
        Aging aging = new Aging();
        Assert.assertFalse("Retirement age cannot be zero", aging.setMaximumAge(0));
    }

    @Test
    public void setValidMaximumAgeTest(){
        Aging aging = new Aging();
        Assert.assertTrue("Retirement age is saved", aging.setMaximumAge(50));
    }

    @Test
    public void getMaximumAgeTest(){
        Aging aging = new Aging();
        aging.setMaximumAge(30);
        Assert.assertEquals(30, aging.getMaximumAge());
    }
}
