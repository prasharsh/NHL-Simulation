package com.persistencemodel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;

public interface ICoachDB {
    void loadCoaches(JSONArray coachesArray, ILeague league);
    void loadHeadCoach(JSONObject coachObject, ITeam team);
}
