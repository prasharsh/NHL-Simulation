package g4db;

import g4dhl.*;

import java.sql.SQLException;

public interface IGameDB {
  void loadLeaguesFromDB(IGame game) throws SQLException;
  void loadConferencesFromDB(ILeague league);
  void loadFreeAgentsFromDB(ILeague league);
  void loadDivisionsFromDB(IConference conference);
  void loadTeamsFromDB(IDivision division);
  void loadPlayersFromDB(ITeam team);
  void loadGeneralManagerFromDb(ITeam team);
  void loadHeadCoachFromDB(ITeam team);
  void saveGame(IGame game);
}
