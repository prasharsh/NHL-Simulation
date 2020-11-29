package com.statemachine;

public abstract class StateMachineAbstractFactory {

    private static StateMachineAbstractFactory uniqueInstance = null;

    public static StateMachineAbstractFactory instance(){
        return uniqueInstance;
    }

    public static void setFactory(StateMachineAbstractFactory stateMachineAbstractFactory){
        uniqueInstance = stateMachineAbstractFactory;
    }

    public abstract IStateMachine createStateMachine(String path);
    public abstract IState createTeamSate();
	public abstract IState createLoadTeamState();
	public abstract IState createPlayerChoiceState();
	public abstract IState createPlayerSimulationChoiceState();
	public abstract IState createAdvanceNextSeasonState();
	public abstract IState createAdvanceTimeState();
	public abstract IState createAgingState();
	public abstract IState createExecuteTradesState();
	public abstract IState createGeneratePlayoffScheduleState();
	public abstract IState createInitializeSeasonState();
	public abstract IState createInjuryCheckState();
	public abstract IState createPersistState();
	public abstract IState createSimulateGameState();
	public abstract IState createTrainingState();
	public abstract IState createJsonImportState();
	public abstract IState createDraftPickState();
	public abstract IState createTrophySystemState();
    
}
