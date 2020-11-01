
package com.datamodeltest.gameplayconfigtest;

import org.junit.Assert;
import org.junit.Test;

import com.datamodel.gameplayconfig.TradingConfig;

public class TradingConfigTest {

	@Test
	public void setNegativeLossPointTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("LossPoint cannot be negative", trading.setLossPoint(-1));
	}

	@Test
	public void setValidLossPointTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertTrue("LossPoint is saved", trading.setLossPoint(50));
	}

	@Test
	public void getLossPointTest() {
		TradingConfig trading = new TradingConfig();
		trading.setLossPoint(30);
		Assert.assertEquals(30, trading.getLossPoint());
	}

	@Test
	public void setNegativeMaxPlayersPerTradeTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("MaxPlayersPerTrade cannot be negative", trading.setLossPoint(-1));
	}

	@Test
	public void setValidMaxPlayersPerTradeTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertTrue("MaxPlayersPerTrade is saved", trading.setLossPoint(5));
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		TradingConfig trading = new TradingConfig();
		trading.setLossPoint(3);
		Assert.assertEquals(3, trading.getLossPoint());
	}

	@Test
	public void setNegativeTradeOfferChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("TradeOfferChance chance cannot be negative", trading.setRandomTradeOfferChance(-1));
	}

	@Test
	public void setInValidTradeOfferChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("TradeOfferChance chance cannot be greater than 1", trading.setRandomTradeOfferChance(2));
	}

	@Test
	public void setValidTradeOfferChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertTrue("TradeOfferChance chance is updated", trading.setRandomTradeOfferChance((float) 0.5));
	}

	@Test
	public void getTradeOfferChanceTest() {
		TradingConfig trading = new TradingConfig();
		float chance = (float) 0.5;
		trading.setRandomTradeOfferChance(chance);
		Assert.assertTrue(trading.getRandomTradeOfferChance() == (float) 0.5);
	}

	@Test
	public void getTradingIdTest() {
		TradingConfig trading = new TradingConfig();
		trading.setTradingId(10);
		Assert.assertEquals(10, trading.getTradingId());
	}

	@Test
	public void setNegativeAcceptanceChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("AcceptanceChance chance cannot be negative", trading.setRandomAcceptanceChance(-1));
	}

	@Test
	public void setInValidAcceptanceChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertFalse("AcceptanceChance chance cannot be greater than 1", trading.setRandomAcceptanceChance(2));
	}

	@Test
	public void setValidAcceptanceChanceTest() {
		TradingConfig trading = new TradingConfig();
		Assert.assertTrue("AcceptanceChance chance is updated", trading.setRandomAcceptanceChance((float) 0.5));
	}

	@Test
	public void getAcceptanceChanceTest() {
		TradingConfig trading = new TradingConfig();
		float chance = (float) 0.5;
		trading.setRandomAcceptanceChance(chance);
		Assert.assertTrue(trading.getRandomAcceptanceChance() == (float) 0.5);
	}
}