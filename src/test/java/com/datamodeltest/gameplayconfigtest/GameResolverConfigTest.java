package com.datamodeltest.gameplayconfigtest;

import com.datamodeltest.gameplayconfig.GameResolverConfig;
import org.junit.Assert;
import org.junit.Test;

public class GameResolverConfigTest {
    @Test
    public void setNegativeWinChanceTest(){
        GameResolverConfig gameResolverConfig = new GameResolverConfig();
        Assert.assertFalse("Random win chance cannot be negative", gameResolverConfig.setRandomWinChance(-1));
    }

    @Test
    public void setInValidWinChanceTest(){
        GameResolverConfig gameResolverConfig = new GameResolverConfig();
        Assert.assertFalse("Random win chance cannot be greater than 1", gameResolverConfig.setRandomWinChance(2));
    }

    @Test
    public void setValidWinChanceTest(){
        GameResolverConfig gameResolverConfig = new GameResolverConfig();
        Assert.assertTrue("Random win chance is updated", gameResolverConfig.setRandomWinChance((float) 0.5));
    }

    @Test
    public void getResolverId(){
        GameResolverConfig gameResolverConfig = new GameResolverConfig();
        gameResolverConfig.setGameResolverId(1);
        Assert.assertEquals(1, gameResolverConfig.getGameResolverId());
    }

    @Test
    public void getWinChanceTest(){
        GameResolverConfig gameResolverConfig = new GameResolverConfig();
        float chance = (float) 0.5;
        gameResolverConfig.setRandomWinChance(chance);
        Assert.assertTrue(gameResolverConfig.getRandomWinChance() == (float) 0.5);
    }
}
