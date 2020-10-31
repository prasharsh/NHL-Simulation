package com.datamodeltest.gameplayconfig;

public interface IGameResolverConfig {
    int getGameResolverId();

    void setGameResolverId(int gameResolverId);

    float getRandomWinChance();

    boolean setRandomWinChance(float randomWinChance);
}
