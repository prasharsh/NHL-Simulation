package g4db;

import g4dhl.*;

public interface IGameDB {
  void loadLeaguesFromDB(IGame game);
  void loadConferencesFromDB(ILeague league);
  void loadFreeAgentsFromDB(ILeague league);
  void loadDivisionsFromDB(IConference conference);
  void loadTeamsFromDB(IDivision division);
  void loadPlayersFromDB(ITeam team);
  void loadGeneralManagerFromDb(ITeam team);
  void loadHeadCoachFromDB(ITeam team);
  void saveGame(IGame game);
}
