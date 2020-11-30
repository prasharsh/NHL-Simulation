package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.*;

public class LeagueDataModelFactoryTest extends LeagueDataModelAbstractFactory {

	private ILeague league = null;
	private ITrading trading = null;
	private ITraining training = null;
	private IDrafting drafting = null;
	private IGame game = null;
	private ISimulateMatch simulateMatch = null;
	private IDataModelObjectUtility utility = null;

	@Override
	public ISimulateMatch createSimulateMatch() {
		if(simulateMatch == null) {
			simulateMatch = new SimulateMatch();
		}
		return simulateMatch;
	}

	@Override
	public IGameSchedule createGameSchedule() {
		return new GameSchedule();
	}

	@Override
	public ITeamStanding createTeamStanding() {
		return new TeamStanding();
	}

	@Override
	public ITimeConcept createTimeConcept() {
		return new TimeConcept();
	}

	@Override
	public ILeague createLeague() {
		if(league == null) {
			league = new League();
		}
		return league;
	}

	@Override
	public IConference createConference() {
		return new Conference();
	}

	@Override
	public IDivision createDivision() {
		return new Division();
	}

	@Override
	public ITeam createTeam() {
		return new Team();
	}

	@Override
	public IPlayer createPlayer() {
		return new Player();
	}

	@Override
	public IPlayer createFreeAgent() {
		return new FreeAgent();
	}

	@Override
	public IGeneralManager createGeneralManager() {
		return new GeneralManager();
	}

	@Override
	public IHeadCoach createHeadCoach() {
		return new HeadCoach();
	}

	@Override
	public ITrading createTrading() {
		if(trading == null) {
			trading = new Trading();
		}
		return trading;
	}

	@Override
	public ITraining createTraining() {
		if(training == null) {
			training = new Training();
		}
		return training;
	}

	@Override
	public IDrafting createDrafting() {
		if(drafting == null) {
			drafting = new Drafting();
		}
		return drafting;
	}

	@Override
	public IRandomPlayer createRandomPlayer() {
		return new RandomPlayer();
	}

	@Override
	public IGame createGame() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}

	@Override
	public IDataModelObjectUtility createUtility() {
		if(utility == null) {
			utility = new DataModelObjectUtility();
		}
		return utility;
	}
}
