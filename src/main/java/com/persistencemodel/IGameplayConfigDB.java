package com.persistencemodel;

import com.datamodel.leaguedatamodel.ILeague;
import org.json.simple.JSONObject;

public interface IGameplayConfigDB {
    void loadGameplayConfig(JSONObject gameplayConfigObject, ILeague league);
}
