package com.persistencemodel;

import com.datamodel.gameplayconfig.GameplayConfig;
import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.leaguedatamodel.ILeague;
import org.json.simple.JSONObject;

public class GameplayConfigDB implements IGameplayConfigDB {

    @Override
    public void loadGameplayConfig(JSONObject gameplayConfigObject, ILeague league) {
        IGameplayConfig gameplayConfig = new GameplayConfig();
        gameplayConfig.setGameConfigId((int) (long) gameplayConfigObject.get("gameConfigId"));
        JSONObject aging = (JSONObject) gameplayConfigObject.get("aging");
        IAgingDB agingDB = new AgingDB();
        agingDB.loadAging(aging, gameplayConfig);
        JSONObject gameResolver = (JSONObject) gameplayConfigObject.get("gameResolver");
        IGameResolverDB gameResolverDB = new GameResolverDB();
        gameResolverDB.loadGameResolver(gameResolver, gameplayConfig);
        JSONObject injury = (JSONObject) gameplayConfigObject.get("injury");
        IInjuryDB injuryDB = new InjuryDB();
        injuryDB.loadInjury(injury, gameplayConfig);
        JSONObject trading = (JSONObject) gameplayConfigObject.get("trading");
        ITradingDB tradingDB = new TradingDB();
        tradingDB.loadTrading(trading, gameplayConfig);
        JSONObject training = (JSONObject) gameplayConfigObject.get("training");
        ITrainingDB trainingDB = new TrainingDB();
        trainingDB.loadTraining(training, gameplayConfig);
        league.setGamePlayConfig(gameplayConfig);
    }
}
