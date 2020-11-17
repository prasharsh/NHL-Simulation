package com.persistencemodel;

import com.datamodel.gameplayconfig.GameResolverConfig;
import com.datamodel.gameplayconfig.IGameResolverConfig;
import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public class GameResolverDB implements IGameResolverDB {

    @Override
    public void loadGameResolver(JSONObject gameResolverObject, IGameplayConfig gameplayConfig) {
        IGameResolverConfig gameResolver = new GameResolverConfig();
        gameResolver.setGameResolverId((int) (long) gameResolverObject.get("gameResolverId"));
        gameResolver.setRandomWinChance((float) (double) gameResolverObject.get("randomWinChance"));
        gameplayConfig.setGameResolver(gameResolver);
    }
}
