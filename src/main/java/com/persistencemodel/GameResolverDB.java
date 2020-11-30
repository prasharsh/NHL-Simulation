package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.GameResolverConfig;
import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.IGameResolverConfig;

public class GameResolverDB implements IGameResolverDB {

    @Override
    public void loadGameResolver(JSONObject gameResolverObject, IGamePlayConfig gameplayConfig) {
        IGameResolverConfig gameResolver = new GameResolverConfig();
        gameResolver.setGameResolverId((int) (long) gameResolverObject.get("gameResolverId"));
        gameResolver.setPenaltyChance((float) (double) gameResolverObject.get("randomWinChance"));
        gameplayConfig.setGameResolver(gameResolver);
    }
}
