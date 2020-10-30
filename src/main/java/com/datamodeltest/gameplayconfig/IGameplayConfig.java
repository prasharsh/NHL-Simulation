package com.datamodeltest.gameplayconfig;

public interface IGameplayConfig {
    int getGameConfigId();

    void setGameConfigId(int gameConfigId);

    IAgingConfig getAging();

    void setAging(IAgingConfig aging);

    IInjuryConfig getInjury();

    void setInjury(IInjuryConfig injury);

    IGameResolverConfig getGameResolver();

    void setGameResolver(IGameResolverConfig gameResolver);

    ITrainingConfig getTraining();

    void setTraining(ITrainingConfig training);

    ITradingConfig getTrading();

    void setTrading(ITradingConfig trading);
}
