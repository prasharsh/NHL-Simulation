package com.statemachine;

import com.datamodel.leaguedatamodel.League;

public class StateMachineFactory extends StateMachineAbstractFactory {

	private IStateMachine stateMachine = null;
	private IState createTeam = null;
	private IState loadTeam = null;
	private IState playerChoice = null;
	private IState playerSimulationChoice = null;
	private IState advanceNextSeason = null;
	private IState advanceTime = null;
	private IState aging = null;
	private IState executeTrades = null;
	private IState generatePlayoffSchedule = null;
	private IState initializeSeason = null;
	private IState injuryCheck = null;
	private IState persist = null;
	private IState simulateGame = null;
	private IState training = null;
	private IState currentState = null;
	private IState jsonImport = null;
	private IState draftPick = null;


	public StateMachineFactory() {
		super();
	}

	@Override
	public IStateMachine createStateMachine(String path) {
		if (stateMachine == null){
			stateMachine = new StateMachine(path);
		}
		return stateMachine;
	}

	@Override
	public IState createTeamSate() {
		if (createTeam == null){
			createTeam = new CreateTeamsState();
		}
		return createTeam;
	}

	@Override
	public IState createLoadTeamState() {
		if (loadTeam == null){
			loadTeam = new LoadTeamsState();
		}
		return loadTeam;
	}

	@Override
	public IState createPlayerChoiceState() {
		if (playerChoice == null){
			playerChoice = new PlayerChoiceState();
		}
		return playerChoice;	
	}

	@Override
	public IState createPlayerSimulationChoiceState() {
		if (playerSimulationChoice == null){
			playerSimulationChoice = new PlayerSimulationChoiceState();
		}
		return playerSimulationChoice;
	}

	@Override
	public IState createAdvanceNextSeasonState() {
		if (advanceNextSeason == null){
			advanceNextSeason = new AdvanceNextSeasonState();
		}
		return advanceNextSeason;
	}

	@Override
	public IState createAdvanceTimeState() {
		if (advanceTime == null){
			advanceTime = new AdvanceTimeState();
		}
		return advanceTime;
	}

	@Override
	public IState createAgingState() {
		if (aging == null){
			aging = new AgingState();
		}
		return aging;
	}

	@Override
	public IState createExecuteTradesState() {
		if (executeTrades == null){
			executeTrades = new ExecuteTradesState();
		}
		return executeTrades;
	}

	@Override
	public IState createGeneratePlayoffScheduleState() {
		if (generatePlayoffSchedule == null){
			generatePlayoffSchedule = new GeneratePlayoffScheduleState();
		}
		return generatePlayoffSchedule;
	}

	@Override
	public IState createInitializeSeasonState() {
		if (initializeSeason == null){
			initializeSeason = new InitializeSeasonState();
		}
		return initializeSeason;
	}

	@Override
	public IState createInjuryCheckState() {
		if (injuryCheck == null){
			injuryCheck = new InjuryCheckState();
		}
		return injuryCheck;
	}

	@Override
	public IState createPersistState() {
		if (persist == null){
			persist = new PersistState();
		}
		return persist;
	}

	@Override
	public IState createSimulateGameState() {
		if (simulateGame == null){
			simulateGame = new SimulateGameState();
		}
		return simulateGame;
	}

	@Override
	public IState createTrainingState() {
		if (training == null){
			training = new TrainingState();
		}
		return training;
	}

	@Override
	public IState createJsonImportState() {
		if (jsonImport == null){
			jsonImport = new JsonImportState();
		}
		return jsonImport;
	}

	@Override
	public IState createDraftPickState() {
		if (draftPick == null){
			draftPick = new DraftPickState();
		}
		return draftPick;
	}

}
