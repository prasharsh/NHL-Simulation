package com.persistencemodel;

import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;

public interface IGameDB {
    void loadLeaguesFromDB(IGame game);

    void loadLeagueFromDB(IGame game);

    void loadConferencesFromDB(ILeague league);

    void loadFreeAgentsFromDB(ILeague league);

    void loadManagersFromDb(ILeague league);

    void loadCoachesFromDB(ILeague league);

    void loadDivisionsFromDB(IConference conference);

    void loadTeamsFromDB(IDivision division);

    void loadPlayersFromDB(ITeam team);

    void loadGeneralManagerFromDb(ITeam team);

    void loadHeadCoachFromDB(ITeam team);

    void saveGame(IGame game);
}
