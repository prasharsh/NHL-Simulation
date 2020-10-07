package g4dhl;

import java.util.ArrayList;

public class Division implements IDivision{

    private String divisionName;
    private ArrayList<ITeam> teams;

    @Override
    public int getDivisionId() {
        return 0;
    }

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionId(int id) {

    }

    @Override
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public ITeam getTeam(int index) {
        return null;
    }

    @Override
    public ArrayList<ITeam> getTeams() {
        return teams;
    }

    @Override
    public void addTeam(ITeam team) {

    }

    @Override
    public void setTeams(ArrayList<ITeam> teams) {
        this.teams = teams;
    }
}
