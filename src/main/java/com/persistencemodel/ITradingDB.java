package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public interface ITradingDB {
    void loadTrading(JSONObject tradingObject, IGamePlayConfig gameplayConfig);
}
