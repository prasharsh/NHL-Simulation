package com.datamodel.gameplayconfig;

public interface IGamePlayConfigAbstractFactory{

    IAgingConfig getAgingConfigInstance();
    ITradingConfig getTradingConfigInstance();
}
