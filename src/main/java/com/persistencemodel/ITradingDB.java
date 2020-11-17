package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public interface ITradingDB {
    void loadTrading(JSONObject tradingObject, IGameplayConfig gameplayConfig);
}
