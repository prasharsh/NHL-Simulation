package com.persistencemodel;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IManagerDB {
	void loadManagers(JSONArray managersArray, ILeague league);

	void loadGeneralManager(JSONObject managerObject, ITeam team);
}
