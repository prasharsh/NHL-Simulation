package com.persistencemodel;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import java.util.List;

public interface ILeagueDB {
    boolean checkIfLeagueExists(String leagueName);

    List<ILeague> loadLeaguesFromTeamName(String teamName);

    boolean exportGameToJSON(IGame game);
}
