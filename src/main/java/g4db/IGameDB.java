package g4db;

import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IGame;
import g4dhl.ILeague;
import g4dhl.ITeam;

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
