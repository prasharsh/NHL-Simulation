package com.datamodeltest.gameplayconfigtest;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.ITradingConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TradingConfigTest {

	private final GamePlayConfigAbstractFactory gamePlayConfigAbstractFactory =
			GamePlayConfigAbstractFactory.instance();
	private final ITradingConfig trading = gamePlayConfigAbstractFactory.createTradingConfig();

	@BeforeClass
	public static void createAgingConfig() {
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactoryTest());
	}

	@Test
	public void setNegativeLossPointTest() {
		Assert.assertFalse("LossPoint cannot be negative", trading.setLossPoint(-1));
	}

	@Test
	public void setValidLossPointTest() {
		Assert.assertTrue("LossPoint is saved", trading.setLossPoint(50));
	}

	@Test
	public void getLossPointTest() {
		trading.setLossPoint(30);
		Assert.assertEquals(30, trading.getLossPoint());
	}

	@Test
	public void setNegativeMaxPlayersPerTradeTest() {
		Assert.assertFalse("MaxPlayersPerTrade cannot be negative", trading.setLossPoint(-1));
	}

	@Test
	public void setValidMaxPlayersPerTradeTest() {
		Assert.assertTrue("MaxPlayersPerTrade is saved", trading.setLossPoint(5));
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		trading.setLossPoint(3);
		Assert.assertEquals(3, trading.getLossPoint());
	}

	@Test
	public void setNegativeTradeOfferChanceTest() {
		Assert.assertFalse("TradeOfferChance chance cannot be negative", trading.setRandomTradeOfferChance(-1));
	}

	@Test
	public void setInValidTradeOfferChanceTest() {
		Assert.assertFalse("TradeOfferChance chance cannot be greater than 1", trading.setRandomTradeOfferChance(2));
	}

	@Test
	public void setValidTradeOfferChanceTest() {
		Assert.assertTrue("TradeOfferChance chance is updated", trading.setRandomTradeOfferChance((float) 0.5));
	}

	@Test
	public void getTradeOfferChanceTest() {
		float chance = (float) 0.5;
		trading.setRandomTradeOfferChance(chance);
		Assert.assertEquals(trading.getRandomTradeOfferChance(), (float) 0.5, 0.0);
	}

	@Test
	public void getTradingIdTest() {
		trading.setTradingId(10);
		Assert.assertEquals(10, trading.getTradingId());
	}

	@Test
	public void setNegativeAcceptanceChanceTest() {
		Assert.assertFalse("AcceptanceChance chance cannot be negative", trading.setRandomAcceptanceChance(-1));
	}

	@Test
	public void setInValidAcceptanceChanceTest() {
		Assert.assertFalse("AcceptanceChance chance cannot be greater than 1", trading.setRandomAcceptanceChance(2));
	}

	@Test
	public void setValidAcceptanceChanceTest() {
		Assert.assertTrue("AcceptanceChance chance is updated", trading.setRandomAcceptanceChance((float) 0.5));
	}

	@Test
	public void getAcceptanceChanceTest() {
		float chance = (float) 0.5;
		trading.setRandomAcceptanceChance(chance);
		Assert.assertEquals(trading.getRandomAcceptanceChance(), (float) 0.5, 0.0);
	}
}