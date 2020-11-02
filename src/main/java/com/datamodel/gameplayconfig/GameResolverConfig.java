package com.datamodel.gameplayconfig;

public class GameResolverConfig implements IGameResolverConfig {
	private int gameResolverId;
	private float randomWinChance;

	@Override
	public int getGameResolverId() {
		return this.gameResolverId;
	}

	@Override
	public void setGameResolverId(int gameResolverId) {
		this.gameResolverId = gameResolverId;
	}

	@Override
	public float getRandomWinChance() {
		return this.randomWinChance;
	}

	@Override
	public boolean setRandomWinChance(float randomWinChance) {
		if (isValidWinChanceValue(randomWinChance)) {
			this.randomWinChance = randomWinChance;
			return true;
		}
		return false;
	}

	private boolean isValidWinChanceValue(float randomWinChance) {
		return randomWinChance >= 0 && randomWinChance <= 1;
	}
}
