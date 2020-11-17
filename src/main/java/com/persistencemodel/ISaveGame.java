package com.persistencemodel;

import com.datamodel.leaguedatamodel.IGame;

public interface ISaveGame {
    boolean exportGameToJSON(IGame game);
}
