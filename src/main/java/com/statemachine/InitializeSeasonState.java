package com.statemachine;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.IGameSchedule;

public class InitializeSeasonState implements IState {

	IStateMachine stateMachine;

	public InitializeSeasonState(IStateMachine stateMachine) {
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
		IGameSchedule gameSchedule = dataModelFactory.createGameSchedule();
		gameSchedule.scheduleRegularSeason(stateMachine.getGame(), stateMachine);
		return stateMachine.getAdvanceTime();
	}
}