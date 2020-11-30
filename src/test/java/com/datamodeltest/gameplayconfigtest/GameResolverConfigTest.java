package com.datamodeltest.gameplayconfigtest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.IGameResolverConfig;

public class GameResolverConfigTest {

	private GamePlayConfigAbstractFactory gamePlayConfigAbstractFactory = GamePlayConfigAbstractFactory.instance();
	private IGameResolverConfig gameResolverConfig = gamePlayConfigAbstractFactory.createGameResolverConfig();

	@BeforeClass
	public static void createAgingConfig(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactoryTest());
	}
	@Test
	public void setNegativeWinChanceTest() {
		Assert.assertFalse("Random win chance cannot be negative", gameResolverConfig.setPenaltyChance(-1));
	}

	@Test
	public void setInValidWinChanceTest() {
		Assert.assertFalse("Random win chance cannot be greater than 1", gameResolverConfig.setPenaltyChance(2));
	}

	@Test
	public void setValidWinChanceTest() {
		Assert.assertTrue("Random win chance is updated", gameResolverConfig.setPenaltyChance((float) 0.5));
	}

	@Test
	public void getResolverId() {
		gameResolverConfig.setGameResolverId(1);
		Assert.assertEquals(1, gameResolverConfig.getGameResolverId());
	}

	@Test
	public void getPenaltyChanceTest() {
		float chance = (float) 0.5;
		gameResolverConfig.setPenaltyChance(chance);
		Assert.assertEquals(gameResolverConfig.getPenaltyChance(), (float) 0.5, 0.0);
	}
}