package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public interface IGameResolverDB {
    void loadGameResolver(JSONObject gameResolverObject, IGamePlayConfig gameplayConfig);
}
