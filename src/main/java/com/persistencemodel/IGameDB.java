package com.persistencemodel;

import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;

public interface IGameDB {
    boolean saveGame(IGame game);

    int getLeagueIdFromTeamName(String teamName);

    boolean loadGame(int leagueId, IGame game);
}
