package com.statemachine;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.IGameSchedule;

public class GeneratePlayoffScheduleState implements IState {

	IStateMachine stateMachine;

	public GeneratePlayoffScheduleState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		schedule.schedulePlayoff(stateMachine.getGame(), stateMachine);
		return stateMachine.getTraining();
	}
}