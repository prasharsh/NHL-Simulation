package com.persistencemodel;

import com.datamodel.leaguedatamodel.IDivision;
import org.json.simple.JSONArray;

public interface ITeamDB {
    void loadTeams(JSONArray teamsArray, IDivision division);
}
