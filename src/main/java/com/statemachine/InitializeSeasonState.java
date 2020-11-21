package com.statemachine;
import com.datamodel.leaguedatamodel.AbstractDataModelFactory;
import com.datamodel.leaguedatamodel.GameSchedule;
import com.datamodel.leaguedatamodel.IDataModelFactory;
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
		IDataModelFactory dataModelFactory = AbstractDataModelFactory.getNewInstance();
		IGameSchedule gameSchedule = dataModelFactory.getGameSchedule();
		gameSchedule.scheduleRegularSeason(stateMachine.getGame(), stateMachine);
		return stateMachine.getAdvanceTime();
	}
}