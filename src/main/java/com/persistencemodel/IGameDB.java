package com.persistencemodel;

import com.datamodeltest.leaguedatamodel.IConference;
import com.datamodeltest.leaguedatamodel.IDivision;
import com.datamodeltest.leaguedatamodel.IGame;
import com.datamodeltest.leaguedatamodel.ILeague;
import com.datamodeltest.leaguedatamodel.ITeam;

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
