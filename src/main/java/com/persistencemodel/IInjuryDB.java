package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public interface IInjuryDB {
    void loadInjury (JSONObject injuryObject, IGameplayConfig gameplayConfig);
}
