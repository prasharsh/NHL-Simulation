package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.DataModelObjectUtility;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.Drafting;
import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.GameSchedule;
import com.datamodel.leaguedatamodel.GeneralManager;
import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDataModelObjectUtility;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IDrafting;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.IRandomPlayer;
import com.datamodel.leaguedatamodel.ISimulateMatch;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITeamStanding;
import com.datamodel.leaguedatamodel.ITimeConcept;
import com.datamodel.leaguedatamodel.ITrading;
import com.datamodel.leaguedatamodel.ITraining;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.leaguedatamodel.RandomPlayer;
import com.datamodel.leaguedatamodel.SimulateMatch;
import com.datamodel.leaguedatamodel.Team;
import com.datamodel.leaguedatamodel.TeamStanding;
import com.datamodel.leaguedatamodel.TimeConcept;
import com.datamodel.leaguedatamodel.Trading;
import com.datamodel.leaguedatamodel.Training;

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
