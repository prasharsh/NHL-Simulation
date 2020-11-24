package com.datamodel.gameplayconfig;

public class GameResolverConfig implements IGameResolverConfig {

	private int gameResolverId;
	private float penaltyChance;

	@Override
	public int getGameResolverId() {
		return this.gameResolverId;
	}

	@Override
	public void setGameResolverId(int gameResolverId) {
		this.gameResolverId = gameResolverId;
	}

	@Override
	public float getPenaltyChance() {
		return this.penaltyChance;
	}

	@Override
	public boolean setPenaltyChance(float penaltyChance) {
		if (isValidPenaltyChance(penaltyChance)) {
			this.penaltyChance = penaltyChance;
			return true;
		}
		return false;
	}

	private boolean isValidPenaltyChance(float penaltyChance) {
		return penaltyChance >= 0 && penaltyChance <= 1;
	}
}