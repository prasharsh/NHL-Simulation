package com.persistencemodel;
import com.datamodel.leaguedatamodel.IGame;

public interface IGameDB {

    boolean saveGame(IGame game);

    int getLeagueIdFromTeamName(String teamName);

    boolean loadGame(int leagueId, IGame game);
}
