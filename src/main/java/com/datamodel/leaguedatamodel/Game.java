package com.datamodel.leaguedatamodel;

import java.util.ArrayList;

import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public class Game implements IGame {

    private final ArrayList<ILeague> leagues;

    public Game() {
        this.leagues = new ArrayList<>();
    }

    private boolean checkIfLeagueIsNull(ILeague league) {
        return league == null;
    }

    private boolean checkIfLeagueNameIsEmptyOrNull(String leagueName) {
        return leagueName == null || leagueName.trim().isEmpty();
    }

    private boolean checkIfLeagueNameAlreadyExists(String leagueName) {
        for (ILeague league : leagues) {
            if (league.getLeagueName().equals(leagueName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    @Override
    public boolean addLeague(ILeague league) {

        if (checkIfLeagueIsNull(league))
            return false;
        if (checkIfLeagueNameIsEmptyOrNull(league.getLeagueName()))
            return false;
        if (checkIfLeagueNameAlreadyExists(league.getLeagueName()))
            return false;

        leagues.add(league);
        return true;
    }

    @Override
    public int loadGameFromTeamName(String teamName, IGameDB gameDB){
        return gameDB.getLeagueIdFromTeamName(teamName);
    }

    @Override
    public boolean saveToDb(IGameDB gameDB) {
        return gameDB.saveGame(this);
    }

    @Override
    public boolean loadGame(int leagueId, IGameDB gameDB){
        return gameDB.loadGame(leagueId, this);
    }
}
