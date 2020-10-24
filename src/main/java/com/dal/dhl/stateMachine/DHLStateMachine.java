package com.dal.dhl.stateMachine;

import java.util.ArrayList;

import com.dal.dhl.states.AdvanceNextSeason;
import com.dal.dhl.states.AdvanceTime;
import com.dal.dhl.states.Aging;
import com.dal.dhl.states.CreateTeams;
import com.dal.dhl.states.ExecuteTrades;
import com.dal.dhl.states.GeneratePlayoffSchedule;
import com.dal.dhl.states.IStateTransistion;
import com.dal.dhl.states.InitializeSeason;
import com.dal.dhl.states.InjuryCheck;
import com.dal.dhl.states.JsonImport;
import com.dal.dhl.states.LoadTeams;
import com.dal.dhl.states.Persist;
import com.dal.dhl.states.PlayerChoice;
import com.dal.dhl.states.PlayerSimulationChoice;
import com.dal.dhl.states.SimulateGame;
import com.dal.dhl.states.Training;

import g4dhl.Game;
import g4dhl.ITeam;
import g4dhl.TeamStanding;

public class DHLStateMachine {

	IStateTransistion importJson;
	IStateTransistion createTeam;
	IStateTransistion loadTeam;
	IStateTransistion playerChoice;
	IStateTransistion playerSimulationChoice;
	IStateTransistion currState;

	IStateTransistion advanceNextSeason;

	IStateTransistion advanceTime;
	IStateTransistion aging;
	IStateTransistion executeTrades;
	IStateTransistion generatePlayoffSchedule;
	IStateTransistion initializeSeason;
	IStateTransistion injuryCheck;

	IStateTransistion persist;
	IStateTransistion simulateGame;
	IStateTransistion training;
	ArrayList<ITeam> teamList;
	
	Game game;

	public DHLStateMachine(String path) {
		importJson = new JsonImport(this, path);
		createTeam = new CreateTeams(this);
		loadTeam = new LoadTeams(this);
		playerChoice = new PlayerChoice(this);
		playerSimulationChoice = new PlayerSimulationChoice(this);
		currState = importJson;
		advanceNextSeason = new AdvanceNextSeason(this);
		advanceTime = new AdvanceTime(this);
		aging = new Aging(this);
		executeTrades= new ExecuteTrades(this);
		generatePlayoffSchedule = new GeneratePlayoffSchedule(this);
		initializeSeason = new InitializeSeason(this);
		injuryCheck = new InjuryCheck(this);
		persist = new Persist(this);
		simulateGame = new SimulateGame(this);
		training = new Training(this);
	}

	public ArrayList<ITeam> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<ITeam> teamList) {
		this.teamList = teamList;
	}

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	public IStateTransistion getImportJson() {
		return importJson;
	}
	public void setImportJson(IStateTransistion importJson) {
		this.importJson = importJson;
	}
	public IStateTransistion getCreateTeam() {
		return createTeam;
	}
	public void setCreateTeam(IStateTransistion createTeam) {
		this.createTeam = createTeam;
	}
	public IStateTransistion getLoadTeam() {
		return loadTeam;
	}
	public void setLoadTeam(IStateTransistion loadTeam) {
		this.loadTeam = loadTeam;
	}
	public IStateTransistion getPlayerChoice() {
		return playerChoice;
	}
	public void setPlayerChoice(IStateTransistion playerChoice) {
		this.playerChoice = playerChoice;
	}
	public IStateTransistion getPlayerSimulationChoice() {
		return playerSimulationChoice;
	}
	public void setPlayerSimulationChoice(IStateTransistion playerSimulationChoice) {
		this.playerSimulationChoice = playerSimulationChoice;
	}
	public IStateTransistion getCurrState() {
		return currState;
	}
	public void setCurrState(IStateTransistion currState) {
		this.currState = currState;
	}


	public IStateTransistion getAdvanceNextSeason() {
		return advanceNextSeason;
	}

	public void setAdvanceNextSeason(IStateTransistion advanceNextSeason) {
		this.advanceNextSeason = advanceNextSeason;
	}

	public IStateTransistion getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(IStateTransistion advanceTime) {
		this.advanceTime = advanceTime;
	}

	public IStateTransistion getAging() {
		return aging;
	}

	public void setAging(IStateTransistion aging) {
		this.aging = aging;
	}

	public IStateTransistion getExecuteTrades() {
		return executeTrades;
	}

	public void setExecuteTrades(IStateTransistion executeTrades) {
		this.executeTrades = executeTrades;
	}

	public IStateTransistion getGeneratePlayoffSchedule() {
		return generatePlayoffSchedule;
	}

	public void setGeneratePlayoffSchedule(IStateTransistion generatePlayoffSchedule) {
		this.generatePlayoffSchedule = generatePlayoffSchedule;
	}

	public IStateTransistion getInitializeSeason() {
		return initializeSeason;
	}

	public void setInitializeSeason(IStateTransistion initializeSeason) {
		this.initializeSeason = initializeSeason;
	}

	public IStateTransistion getInjuryCheck() {
		return injuryCheck;
	}

	public void setInjuryCheck(IStateTransistion injuryCheck) {
		this.injuryCheck = injuryCheck;
	}

	public IStateTransistion getPersist() {
		return persist;
	}

	public void setPersist(IStateTransistion persist) {
		this.persist = persist;
	}

	public IStateTransistion getSimulateGame() {
		return simulateGame;
	}

	public void setSimulateGame(IStateTransistion simulateGame) {
		this.simulateGame = simulateGame;
	}

	public IStateTransistion getTraining() {
		return training;
	}

	public void setTraining(IStateTransistion training) {
		this.training = training;
	}



	void start(){
		currState.entry();
	}

}
