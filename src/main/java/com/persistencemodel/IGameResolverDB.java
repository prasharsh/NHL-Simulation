package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public interface IGameResolverDB {
    void loadGameResolver(JSONObject gameResolverObject, IGameplayConfig gameplayConfig);
}
