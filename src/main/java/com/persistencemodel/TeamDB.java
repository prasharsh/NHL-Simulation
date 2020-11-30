package com.persistencemodel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Team;

public class TeamDB implements ITeamDB {

    @Override
    public void loadTeams(JSONArray teamsArray, IDivision division) {
        for (Object currentTeam : teamsArray) {
            JSONObject teamObject = (JSONObject) currentTeam;
            ITeam team = new Team();
            team.setTeamId((int) (long) teamObject.get("teamId"));
            team.setTeamName((String) teamObject.get("teamName"));
            team.setTeamCreatedBy((String) teamObject.get("teamCreatedBy"));
            JSONArray players = (JSONArray) teamObject.get("players");
            IPlayerDB playerDB = new PlayerDB();
            playerDB.loadPlayers(players, team);
            JSONObject headCoach = (JSONObject) teamObject.get("headCoach");
            ICoachDB coachDB = new CoachDB();
            coachDB.loadHeadCoach(headCoach, team);
            JSONObject generalManager = (JSONObject) teamObject.get("generalManager");
            IManagerDB managerDB = new ManagerDB();
            managerDB.loadGeneralManager(generalManager, team);
            division.addTeam(team);
        }
    }
}
