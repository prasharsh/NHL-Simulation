package com.persistencemodel;

import org.json.simple.JSONArray;

import com.datamodel.leaguedatamodel.IDivision;

public interface ITeamDB {
    void loadTeams(JSONArray teamsArray, IDivision division);
}
