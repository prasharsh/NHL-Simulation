package com.persistencemodel;

import com.datamodel.leaguedatamodel.ITeam;
import org.json.simple.JSONArray;

public interface IPlayerDB {
    void loadPlayers(JSONArray playersArray, ITeam team);
}
