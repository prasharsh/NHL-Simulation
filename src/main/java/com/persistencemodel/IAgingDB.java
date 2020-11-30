package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;

public interface IAgingDB {
    void loadAging(JSONObject agingObject, IGamePlayConfig gameplayConfig);
}
