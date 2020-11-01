package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.GeneralManager;
import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.leaguedatamodel.Team;
import com.persistencemodel.IGameDB;

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
		IPlayer freeAgent = new FreeAgent();
		freeAgent.setPlayerId(1);
		freeAgent.setPlayerName("Agent One");
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

	@Override
	public void loadManagersFromDb(ILeague league) {
		IGeneralManager manager = new GeneralManager();
		manager.setGeneralManagerId(1);
		manager.setGeneralManagerName("Mister Kumar");
		league.setManager(manager);
	}

	@Override
	public void loadCoachesFromDB(ILeague league) {
		IHeadCoach coach = new HeadCoach();
		coach.setHeadCoachId(1);
		coach.setHeadCoachName("Mary Harpreet");
		league.setCoach(coach);
	}
}