package com.persistencemodel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;

public interface IManagerDB {
    void loadManagers(JSONArray managersArray, ILeague league);
    void loadGeneralManager(JSONObject managerObject, ITeam team);
}
