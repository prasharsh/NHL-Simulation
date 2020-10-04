package leagueModel;

import java.util.ArrayList;

public class Division implements IDivision{

    private String divisionName;
    private ArrayList<ITeam> teams;

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public ArrayList<ITeam> getTeams() {
        return teams;
    }

    @Override
    public void setTeams(ArrayList<ITeam> teams) {
        this.teams = teams;
    }
}
