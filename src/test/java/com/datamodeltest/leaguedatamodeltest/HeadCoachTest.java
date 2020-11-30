package com.datamodeltest.leaguedatamodeltest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class HeadCoachTest {

    private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
    private IHeadCoach headCoach = leagueDataModelAbstractFactory.createHeadCoach();

    @BeforeClass
    public static void setFactory() {
        LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
    }

    @Test
    public void setHeadCoachIdTest() {
        Assert.assertTrue(headCoach.setHeadCoachId(10));
    }

    @Test
    public void getHeadCoachIdTest() {
        headCoach.setHeadCoachId(10);
        Assert.assertEquals(10, headCoach.getHeadCoachId());
    }

    @Test
    public void getHeadCoachNameTest() {
        headCoach.setHeadCoachName("Rob");
        Assert.assertEquals("Rob", headCoach.getHeadCoachName());
    }

    @Test
    public void setHeadCoachNameEmptyTest() {
        Assert.assertFalse("Head Coach cannot be empty", headCoach.setHeadCoachName(""));
    }

    @Test
    public void setHeadCoachNameNullTest() {
        Assert.assertFalse("Head Coach cannot be null", headCoach.setHeadCoachName(null));
    }

    @Test
    public void setHeadCoachNameTest() {
        Assert.assertTrue(headCoach.setHeadCoachName("Steve Roger"));
    }

    @Test
    public void setHeadCoachSkatingTest() {
        Assert.assertTrue(headCoach.setHeadCoachSkating(0.5f));
    }

    @Test
    public void setHeadCoachShootingTest() {
        Assert.assertTrue(headCoach.setHeadCoachShooting(0.4f));
    }

    @Test
    public void setHeadCoachCheckingTest() {
        Assert.assertTrue(headCoach.setHeadCoachChecking(0.9f));
    }

    @Test
    public void setHeadCoachSavingTest() {
        Assert.assertTrue(headCoach.setHeadCoachSaving(0.2f));
    }
}