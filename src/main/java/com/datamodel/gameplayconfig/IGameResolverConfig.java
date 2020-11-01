package com.datamodel.gameplayconfig;

public interface IGameResolverConfig {
	int getGameResolverId();

	void setGameResolverId(int gameResolverId);

	float getRandomWinChance();

	boolean setRandomWinChance(float randomWinChance);
}