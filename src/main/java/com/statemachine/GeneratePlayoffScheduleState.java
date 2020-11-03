package com.statemachine;
import com.datamodel.leaguedatamodel.GameSchedule;
import com.datamodel.leaguedatamodel.IGameSchedule;

public class GeneratePlayoffScheduleState implements IState {

	StateMachine stateMachine;

	public GeneratePlayoffScheduleState(StateMachine stateMachine) {
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
		IGameSchedule schedule = new GameSchedule();
		schedule.schedulePlayoff(stateMachine.getGame(), stateMachine);
		return stateMachine.getTraining();
	}
}