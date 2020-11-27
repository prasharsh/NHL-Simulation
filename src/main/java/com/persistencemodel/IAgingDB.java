package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public interface IAgingDB {
    void loadAging(JSONObject agingObject, IGamePlayConfig gameplayConfig);
}
