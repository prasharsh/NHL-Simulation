package g4dhl;

import g4db.IGameDB;

import java.util.ArrayList;

public class Division implements IDivision, ILoadDataFromDB{

    private int divisionId;
    private String divisionName;
    private ArrayList<ITeam> teams;

    public Division(){
        divisionName = null;
        teams = new ArrayList<>();
    }

    @Override
    public int getDivisionId() {
        return divisionId;
    }

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
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
    public void addTeam(ITeam team) {
        teams.add(team);
    }

    @Override
    public void loadFromDB(IGameDB gameDB) {
        gameDB.loadTeamsFromDB(this);
    }
}
