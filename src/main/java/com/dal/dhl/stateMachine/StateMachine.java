package com.dal.dhl.stateMachine;

import java.util.ArrayList;
import java.util.HashSet;

import com.dal.dhl.states.AdvanceNextSeason;
import com.dal.dhl.states.AdvanceTime;
import com.dal.dhl.states.Aging;
import com.dal.dhl.states.CreateTeams;
import com.dal.dhl.states.ExecuteTrades;
import com.dal.dhl.states.GeneratePlayoffSchedule;
import com.dal.dhl.states.IState;
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

public class StateMachine {


	private IState createTeam;
	private IState loadTeam;
	private IState playerChoice;
	private IState playerSimulationChoice;
	private IState advanceNextSeason;
	private IState advanceTime;
	private IState aging;
	private IState executeTrades;
	private IState generatePlayoffSchedule;
	private IState initializeSeason;
	private IState injuryCheck;
	private IState persist;
	private IState simulateGame;
	private IState training;
	private IState currentState;
	private IState jsonImport;
	private ArrayList<ITeam> teamList;
	private HashSet<ITeam> gameDayTeams;
	Game game;

	public StateMachine(String path) {
		jsonImport = new JsonImport(this, path);
		createTeam = new CreateTeams(this);
		loadTeam = new LoadTeams(this);
		playerChoice = new PlayerChoice(this);
		playerSimulationChoice = new PlayerSimulationChoice(this);

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
		currentState = jsonImport;
	}


	public void start() {

		while(currentState != null) {
			IState transistionState = currentState.doTask();
			if(transistionState != currentState) {
				transistionToState(transistionState);
			}
		}
	}

	private void transistionToState(IState toState) {
		currentState.exit();
		currentState = toState;
		currentState.entry();
	}


	public ArrayList<ITeam> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<ITeam> teamList) {
		this.teamList = teamList;
	}

	public HashSet<ITeam> getGameDayTeams() {
		return gameDayTeams;
	}

	public void setGameDayTeams(HashSet<ITeam> gameDayTeams) {
		this.gameDayTeams = gameDayTeams;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public IState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IState currentState) {
		this.currentState = currentState;
	}


	public IState getCreateTeam() {
		return createTeam;
	}


	public void setCreateTeam(IState createTeam) {
		this.createTeam = createTeam;
	}


	public IState getLoadTeam() {
		return loadTeam;
	}


	public void setLoadTeam(IState loadTeam) {
		this.loadTeam = loadTeam;
	}


	public IState getPlayerChoice() {
		return playerChoice;
	}


	public void setPlayerChoice(IState playerChoice) {
		this.playerChoice = playerChoice;
	}


	public IState getPlayerSimulationChoice() {
		return playerSimulationChoice;
	}


	public void setPlayerSimulationChoice(IState playerSimulationChoice) {
		this.playerSimulationChoice = playerSimulationChoice;
	}


	public IState getAdvanceNextSeason() {
		return advanceNextSeason;
	}


	public void setAdvanceNextSeason(IState advanceNextSeason) {
		this.advanceNextSeason = advanceNextSeason;
	}


	public IState getAdvanceTime() {
		return advanceTime;
	}


	public void setAdvanceTime(IState advanceTime) {
		this.advanceTime = advanceTime;
	}


	public IState getAging() {
		return aging;
	}


	public void setAging(IState aging) {
		this.aging = aging;
	}


	public IState getExecuteTrades() {
		return executeTrades;
	}


	public void setExecuteTrades(IState executeTrades) {
		this.executeTrades = executeTrades;
	}


	public IState getGeneratePlayoffSchedule() {
		return generatePlayoffSchedule;
	}


	public void setGeneratePlayoffSchedule(IState generatePlayoffSchedule) {
		this.generatePlayoffSchedule = generatePlayoffSchedule;
	}


	public IState getInitializeSeason() {
		return initializeSeason;
	}


	public void setInitializeSeason(IState initializeSeason) {
		this.initializeSeason = initializeSeason;
	}


	public IState getInjuryCheck() {
		return injuryCheck;
	}


	public void setInjuryCheck(IState injuryCheck) {
		this.injuryCheck = injuryCheck;
	}


	public IState getPersist() {
		return persist;
	}


	public void setPersist(IState persist) {
		this.persist = persist;
	}


	public IState getSimulateGame() {
		return simulateGame;
	}


	public void setSimulateGame(IState simulateGame) {
		this.simulateGame = simulateGame;
	}


	public IState getTraining() {
		return training;
	}


	public void setTraining(IState training) {
		this.training = training;
	}


	public IState getJsonImport() {
		return jsonImport;
	}


	public void setJsonImport(IState jsonImport) {
		this.jsonImport = jsonImport;
	}

}
