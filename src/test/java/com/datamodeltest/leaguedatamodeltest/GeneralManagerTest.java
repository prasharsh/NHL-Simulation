package com.datamodeltest.leaguedatamodeltest;
import com.datamodeltest.leaguedatamodel.GeneralManager;
import org.junit.Assert;
import org.junit.Test;

public class GeneralManagerTest{

    @Test
    public void setGeneralManagerNameEmptyTest(){
        GeneralManager generalManager = new GeneralManager();
        Assert.assertFalse("General Manager name cannot be empty", generalManager.setGeneralManagerName(""));
    }

    @Test
    public void setGeneralManagerNameNullTest(){
        GeneralManager generalManager = new GeneralManager();
        Assert.assertFalse("General Manager name cannot be null", generalManager.setGeneralManagerName(null));
    }
}