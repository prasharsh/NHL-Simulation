package g4db;

public interface IGameDB {
  void loadLeaguesFromDB(IGame game);
  void loadConferencesFromDB(ILeague league);
  void loadFreeAgentsFromDB(ILeague league);
  void loadDivisionsFromDB(IConference conference);
  void loadTeamsFromDB(IDivision division);
  void loadPlayersFromDB(ITeam team);
  void loadGenerealManagerFromDb(ITeam team);
  void loadHeadCoachFromDB(ITeam team);
  void saveGame(IGame game);
}
