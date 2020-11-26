package com.datamodel.gameplayconfig;

public class GamePlayConfigFactory implements IGamePlayConfigAbstractFactory {

    private static IGamePlayConfigAbstractFactory gamePlayConfigFactoryInstance = null;
    private IAgingConfig agingConfig;
    private ITradingConfig tradingConfig;

    private GamePlayConfigFactory() {
    }

    public static IGamePlayConfigAbstractFactory getInstance() {
        if (gamePlayConfigFactoryInstance == null){
            gamePlayConfigFactoryInstance = new GamePlayConfigFactory();
        }
        return gamePlayConfigFactoryInstance;
    }

    public IAgingConfig getAgingConfigInstance(){
        if (agingConfig == null){
            agingConfig = new AgingConfig();
        }
        return agingConfig;
    }

    public ITradingConfig getTradingConfigInstance(){
        if (tradingConfig == null) {
            tradingConfig = new TradingConfig();
        }
        return tradingConfig;
    }

}

