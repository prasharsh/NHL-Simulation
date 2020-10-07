package g4dhl;

import g4db.IGameDB;

import java.util.ArrayList;

public class Team implements  ITeam, ILoadDataFromDB{

    private int teamId;
    private String teamName;
    private IGeneralManager generalManager;
    private IHeadCoach headCoach;
    private ArrayList<IPlayer> players;

    @Override
    public int getTeamId() {
        return teamId;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public IGeneralManager getGeneralManager() {
        return generalManager;
    }

    @Override
    public void setGeneralManager(IGeneralManager generalManager) {
        this.generalManager = generalManager;
    }

    @Override
    public IHeadCoach getHeadCoach() {
        return headCoach;
    }

    @Override
    public void setHeadCoach(IHeadCoach headCoach) {
        this.headCoach = headCoach;
    }

    @Override
    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public void addPlayer(IPlayer player) {
        players.add(player);
    }

    @Override
    public void loadFromDB(IGameDB gameDB) {
        gameDB.loadPlayersFromDB(this);
    }
}
