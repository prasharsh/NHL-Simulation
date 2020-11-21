package com.statemachine;
import java.util.ArrayList;
import java.util.HashSet;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITeam;

public class StateMachine implements IStateMachine{

	private static StateMachine instance;
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
	IGame game;

	 public static StateMachine getInstance(String path) {
	        if (instance == null) {
	            instance = new StateMachine(path);
	        }
	        return instance;
	    }
	
	public StateMachine(String path) {
		jsonImport = new JsonImportState(this, path);
		createTeam = new CreateTeamsState(this);
		loadTeam = new LoadTeamsState(this);
		playerChoice = new PlayerChoiceState(this);
		playerSimulationChoice = new PlayerSimulationChoiceState(this);
		advanceNextSeason = new AdvanceNextSeasonState(this);
		advanceTime = new AdvanceTimeState(this);
		aging = new AgingState(this);
		executeTrades = new ExecuteTradesState(this);
		generatePlayoffSchedule = new GeneratePlayoffScheduleState(this);
		initializeSeason = new InitializeSeasonState(this);
		injuryCheck = new InjuryCheckState(this);
		persist = new PersistState(this);
		simulateGame = new SimulateGameState(this);
		training = new TrainingState(this);
		currentState = jsonImport;
	}

	private void transistionToState(IState toState) {
		currentState.exit();
		currentState = toState;
		currentState.entry();
	}

	public void start() {
		while (currentState != null) {
			IState transistionState = currentState.doTask();
			if (transistionState != currentState) {
				transistionToState(transistionState);
			}
		}
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

	public IGame getGame() {
		return game;
	}

	public void setGame(IGame game) {
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