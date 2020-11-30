package com.persistencemodel;

import org.json.simple.JSONArray;

import com.datamodel.leaguedatamodel.ILeague;

public interface IFreeAgentDB {
    void loadFreeAgents(JSONArray freeAgentsArray, ILeague league);
}
