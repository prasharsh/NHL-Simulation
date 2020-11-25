package com.datamodel.gameplayconfig;

public class GamePlayConfigFactory implements IGamePlayConfigAbstractFactory {

    private static GamePlayConfigFactory uniqueInstance = null;
    private IAgingConfig agingConfig;
    private ITradingConfig tradingConfig;

    public IAgingConfig createAgingConfig(){
        agingConfig = AgingConfig.FactorySingleton();
        return agingConfig;
    }

    public ITradingConfig createTradingConfig(){
        tradingConfig = TradingConfig.FactorySingleton();
        return tradingConfig;
    }

}

