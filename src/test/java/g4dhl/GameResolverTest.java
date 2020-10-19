package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class GameResolverTest {
    @Test
    public void setNegativeWinChanceTest(){
        GameResolver gameResolver = new GameResolver();
        Assert.assertFalse("Random win chance cannot be negative", gameResolver.setRandomWinChance(-1));
    }

    @Test
    public void setInValidWinChanceTest(){
        GameResolver gameResolver = new GameResolver();
        Assert.assertFalse("Random win chance cannot be greater than 1", gameResolver.setRandomWinChance(2));
    }

    @Test
    public void setValidWinChanceTest(){
        GameResolver gameResolver = new GameResolver();
        Assert.assertTrue("Random win chance is updated", gameResolver.setRandomWinChance((float) 0.5));
    }

    @Test
    public void getResolverId(){
        GameResolver gameResolver = new GameResolver();
        gameResolver.setGameResolverId(1);
        Assert.assertEquals(1, gameResolver.getGameResolverId());
    }

    @Test
    public void getWinChanceTest(){
        GameResolver gameResolver = new GameResolver();
        float chance = (float) 0.5;
        gameResolver.setRandomWinChance(chance);
        Assert.assertTrue(gameResolver.getRandomWinChance() == (float) 0.5);
    }
}
