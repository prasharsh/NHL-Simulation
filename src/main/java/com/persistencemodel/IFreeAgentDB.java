package com.persistencemodel;

import com.datamodel.leaguedatamodel.ILeague;
import org.json.simple.JSONArray;

public interface IFreeAgentDB {
	void loadFreeAgents(JSONArray freeAgentsArray, ILeague league);
}
