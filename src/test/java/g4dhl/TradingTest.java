package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class TradingTest {

    @Test
    public void setNegativeLossPointTest(){
        Trading trading = new Trading();
        Assert.assertFalse("LossPoint cannot be negative", trading.setLossPoint(-1));
    }

    @Test
    public void setValidLossPointTest(){
        Trading trading = new Trading();
        Assert.assertTrue("LossPoint is saved", trading.setLossPoint(50));
    }

    @Test
    public void getLossPointTest(){
        Trading trading = new Trading();
        trading.setLossPoint(30);
        Assert.assertEquals(30, trading.getLossPoint());
    }

    @Test
    public void setNegativeMaxPlayersPerTradeTest(){
        Trading trading = new Trading();
        Assert.assertFalse("MaxPlayersPerTrade cannot be negative", trading.setLossPoint(-1));
    }

    @Test
    public void setValidMaxPlayersPerTradeTest(){
        Trading trading = new Trading();
        Assert.assertTrue("MaxPlayersPerTrade is saved", trading.setLossPoint(5));
    }

    @Test
    public void getMaxPlayersPerTradeTest(){
        Trading trading = new Trading();
        trading.setLossPoint(3);
        Assert.assertEquals(3, trading.getLossPoint());
    }
    @Test
    public void setNegativeTradeOfferChanceTest(){
        Trading trading = new Trading();
        Assert.assertFalse("TradeOfferChance chance cannot be negative", trading.setRandomTradeOfferChance(-1));
    }

    @Test
    public void setInValidTradeOfferChanceTest(){
        Trading trading = new Trading();
        Assert.assertFalse("TradeOfferChance chance cannot be greater than 1", trading.setRandomTradeOfferChance(2));
    }

    @Test
    public void setValidTradeOfferChanceTest(){
        Trading trading = new Trading();
        Assert.assertTrue("TradeOfferChance chance is updated", trading.setRandomTradeOfferChance((float) 0.5));
    }

    @Test
    public void getTradeOfferChanceTest(){
        Trading trading = new Trading();
        float chance = (float) 0.5;
        trading.setRandomTradeOfferChance(chance);
        Assert.assertTrue(trading.getRandomTradeOfferChance() == (float) 0.5);
    }

    @Test
    public void getTradingIdTest() {
        Trading trading = new Trading();
        trading.setTradingId(10);
        Assert.assertEquals(10, trading.getTradingId());
    }
    @Test
    public void setNegativeAcceptanceChanceTest(){
        Trading trading = new Trading();
        Assert.assertFalse("AcceptanceChance chance cannot be negative", trading.setRandomAcceptanceChance(-1));
    }

    @Test
    public void setInValidAcceptanceChanceTest(){
        Trading trading = new Trading();
        Assert.assertFalse("AcceptanceChance chance cannot be greater than 1", trading.setRandomAcceptanceChance(2));
    }

    @Test
    public void setValidAcceptanceChanceTest(){
        Trading trading = new Trading();
        Assert.assertTrue("AcceptanceChance chance is updated", trading.setRandomAcceptanceChance((float) 0.5));
    }

    @Test
    public void getAcceptanceChanceTest(){
        Trading trading = new Trading();
        float chance = (float) 0.5;
        trading.setRandomAcceptanceChance(chance);
        Assert.assertTrue(trading.getRandomAcceptanceChance() == (float) 0.5);
    }
}
