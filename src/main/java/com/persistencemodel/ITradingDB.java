package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;

public interface ITradingDB {
    void loadTrading(JSONObject tradingObject, IGamePlayConfig gameplayConfig);
}
