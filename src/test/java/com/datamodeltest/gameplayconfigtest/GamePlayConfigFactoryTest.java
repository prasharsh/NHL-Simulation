package com.datamodeltest.gameplayconfigtest;

import com.datamodel.gameplayconfig.AgingConfig;
import com.datamodel.gameplayconfig.GamePlayConfig;
import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GameResolverConfig;
import com.datamodel.gameplayconfig.GeneralManagerConfig;
import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.IGameResolverConfig;
import com.datamodel.gameplayconfig.IGeneralManagerConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.datamodel.gameplayconfig.ITradingConfig;
import com.datamodel.gameplayconfig.ITrainingConfig;
import com.datamodel.gameplayconfig.InjuryConfig;
import com.datamodel.gameplayconfig.TradingConfig;
import com.datamodel.gameplayconfig.TrainingConfig;

public class GamePlayConfigFactoryTest extends GamePlayConfigAbstractFactory {

    private IGamePlayConfig gamePlayConfig = null;
    private IAgingConfig agingConfig = null;
    private IGameResolverConfig gameResolverConfig = null;
    private IGeneralManagerConfig generalManagerConfig = null;
    private IInjuryConfig injuryConfig = null;
    private ITrainingConfig trainingConfig = null;
    private ITradingConfig tradingConfig = null;

    @Override
    public IGamePlayConfig createGamePlayConfig() {
        if (gamePlayConfig == null){
            gamePlayConfig = new GamePlayConfig();
        }
        return gamePlayConfig;
    }

    @Override
    public IAgingConfig createAgingConfig() {
        if (agingConfig == null){
            agingConfig = new AgingConfig();
        }
        return agingConfig;
    }

    @Override
    public IGameResolverConfig createGameResolverConfig() {
        if (gameResolverConfig == null){
            gameResolverConfig = new GameResolverConfig();
        }
        return gameResolverConfig;
    }

    @Override
    public IGeneralManagerConfig createGeneralManagerConfig() {
        if (generalManagerConfig == null){
            generalManagerConfig = new GeneralManagerConfig();
        }
        return generalManagerConfig;
    }

    @Override
    public IInjuryConfig createInjuryConfig() {
        if (injuryConfig == null){
            injuryConfig = new InjuryConfig();
        }
        return injuryConfig;
    }

    @Override
    public ITrainingConfig createTrainingConfig() {
        if (trainingConfig == null){
            trainingConfig = new TrainingConfig();
        }
        return trainingConfig;
    }

    @Override
    public ITradingConfig createTradingConfig() {
        if (tradingConfig == null){
            tradingConfig = new TradingConfig();
        }
        return tradingConfig;
    }
}
