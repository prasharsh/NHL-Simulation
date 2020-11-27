package com.persistencemodel;

import com.datamodel.gameplayconfig.GameResolverConfig;
import com.datamodel.gameplayconfig.IGameResolverConfig;
import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public class GameResolverDB implements IGameResolverDB {

    @Override
    public void loadGameResolver(JSONObject gameResolverObject, IGamePlayConfig gameplayConfig) {
        IGameResolverConfig gameResolver = new GameResolverConfig();
        gameResolver.setGameResolverId((int) (long) gameResolverObject.get("gameResolverId"));
        gameResolver.setPenaltyChance((float) (double) gameResolverObject.get("randomWinChance"));
        gameplayConfig.setGameResolver(gameResolver);
    }
}
