package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;

public interface IGameResolverDB {
    void loadGameResolver(JSONObject gameResolverObject, IGamePlayConfig gameplayConfig);
}
