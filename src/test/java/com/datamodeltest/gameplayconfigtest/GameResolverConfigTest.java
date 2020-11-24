package com.datamodeltest.gameplayconfigtest;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.gameplayconfig.GameResolverConfig;

public class GameResolverConfigTest {

	@Test
	public void setNegativeWinChanceTest() {
		GameResolverConfig gameResolverConfig = new GameResolverConfig();
		Assert.assertFalse("Random win chance cannot be negative", gameResolverConfig.setPenaltyChance(-1));
	}

	@Test
	public void setInValidWinChanceTest() {
		GameResolverConfig gameResolverConfig = new GameResolverConfig();
		Assert.assertFalse("Random win chance cannot be greater than 1", gameResolverConfig.setPenaltyChance(2));
	}

	@Test
	public void setValidWinChanceTest() {
		GameResolverConfig gameResolverConfig = new GameResolverConfig();
		Assert.assertTrue("Random win chance is updated", gameResolverConfig.setPenaltyChance((float) 0.5));
	}

	@Test
	public void getResolverId() {
		GameResolverConfig gameResolverConfig = new GameResolverConfig();
		gameResolverConfig.setGameResolverId(1);
		Assert.assertEquals(1, gameResolverConfig.getGameResolverId());
	}

	@Test
	public void getPenaltyChanceTest() {
		GameResolverConfig gameResolverConfig = new GameResolverConfig();
		float chance = (float) 0.5;
		gameResolverConfig.setPenaltyChance(chance);
		Assert.assertEquals(gameResolverConfig.getPenaltyChance(), (float) 0.5, 0.0);
	}
}