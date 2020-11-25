package com.datamodel.gameplayconfig;

public interface IGamePlayConfigAbstractFactory{

    IAgingConfig createAgingConfig();
    ITradingConfig createTradingConfig();
}
