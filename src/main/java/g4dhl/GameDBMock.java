package g4dhl;

import g4db.IGameDB;

public class GameDBMock implements IGameDB {

    @Override
    public void loadLeaguesFromDB(IGame game) {
        ILeague league = new League();
        league.setLeagueId(1);
        league.setLeagueName("DHL");
        game.addLeague(league);

    }

    @Override
    public void loadConferencesFromDB(ILeague league) {
        IConference conference = new Conference();
        conference.setConferenceId(1);
        conference.setConferenceName("Eastern Conference");
        league.addConference(conference);
    }

    @Override
    public void loadFreeAgentsFromDB(ILeague league) {
        IFreeAgent freeAgent = new FreeAgent();
        freeAgent.setFreeAgentId(1);
        freeAgent.setFreeAgentName("Agent One");
        freeAgent.setFreeAgentCaptain(false);
        league.addFreeAgent(freeAgent);
    }

    @Override
    public void loadDivisionsFromDB(IConference conference) {
        IDivision division = new Division();
        division.setDivisionId(1);
        division.setDivisionName("Atlantic");
        conference.addDivision(division);
    }

    @Override
    public void loadTeamsFromDB(IDivision division) {
        ITeam team = new Team();
        team.setTeamId(1);
        team.setTeamName("Boston");
        division.addTeam(team);
    }

    @Override
    public void loadPlayersFromDB(ITeam team) {
        IPlayer player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Player One");
        player.setPlayerPosition("forward");
        player.setPlayerCaptain(false);
        team.addPlayer(player);
    }

    @Override
    public void loadGeneralManagerFromDb(ITeam team) {
        IGeneralManager generalManager = new GeneralManager();
        generalManager.setGeneralManagerId(1);
        generalManager.setGeneralManagerName("Mister Fred");
        team.setGeneralManager(generalManager);
    }

    @Override
    public void loadHeadCoachFromDB(ITeam team) {
        IHeadCoach headCoach = new HeadCoach();
        headCoach.setHeadCoachId(1);
        headCoach.setHeadCoachName("Mary Smith");
        team.setHeadCoach(headCoach);
    }

    @Override
    public void saveGame(IGame game) {

    }

	@Override
	public void loadLeagueFromDB(IGame game) {
		// TODO Auto-generated method stub
		
	}
}
