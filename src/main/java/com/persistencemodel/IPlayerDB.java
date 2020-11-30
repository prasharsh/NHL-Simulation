package com.persistencemodel;

import org.json.simple.JSONArray;

import com.datamodel.leaguedatamodel.ITeam;

public interface IPlayerDB {
    void loadPlayers(JSONArray playersArray, ITeam team);
}
