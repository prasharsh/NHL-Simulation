package com.persistencemodel;

import java.util.List;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;

public interface ILeagueDB {
    boolean checkIfLeagueExists(String leagueName);

    List<ILeague> loadLeaguesFromTeamName(String teamName);

    boolean exportGameToJSON(IGame game);
}
