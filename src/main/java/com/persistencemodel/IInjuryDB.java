package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;

public interface IInjuryDB {
    void loadInjury (JSONObject injuryObject, IGamePlayConfig gameplayConfig);
}
