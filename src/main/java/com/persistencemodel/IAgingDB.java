package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public interface IAgingDB {
    void loadAging(JSONObject agingObject, IGameplayConfig gameplayConfig);
}
