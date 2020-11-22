package com.persistencemodel;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;

import java.util.ArrayList;

public interface ILeagueDB {
    boolean checkIfLeagueExists(String leagueName);

    ArrayList<ILeague> loadLeaguesFromTeamName(String teamName);

    boolean exportGameToJSON(IGame game);
}
