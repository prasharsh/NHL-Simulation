package com.persistencemodel;

import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DivisionDB implements IDivisionDB {

    @Override
    public void loadDivisions(JSONArray divisionArray, IConference conference) {
        for (Object currentDivision : divisionArray) {
            JSONObject divisionObject = (JSONObject) currentDivision;
            IDivision division = new Division();
            division.setDivisionId((int) (long) divisionObject.get("divisionId"));
            division.setDivisionName((String) divisionObject.get("divisionName"));
            JSONArray teams = (JSONArray) divisionObject.get("teams");
            ITeamDB teamDB = new TeamDB();
            teamDB.loadTeams(teams, division);
            conference.addDivision(division);
        }
    }
}
