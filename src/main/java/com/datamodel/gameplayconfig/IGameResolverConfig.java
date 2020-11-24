package com.datamodel.gameplayconfig;

public interface IGameResolverConfig {

	int getGameResolverId();

	void setGameResolverId(int gameResolverId);

	public float getPenaltyChance();
	
	public boolean setPenaltyChance(float penaltyChance);
	
}