package com.persistencemodel;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface ICoachDB {
	void loadCoaches(JSONArray coachesArray, ILeague league);

	void loadHeadCoach(JSONObject coachObject, ITeam team);
}
