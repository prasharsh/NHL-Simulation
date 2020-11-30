package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.leaguedatamodel.ILeague;

public interface IGameplayConfigDB {
    void loadGameplayConfig(JSONObject gameplayConfigObject, ILeague league);
}
