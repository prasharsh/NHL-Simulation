package com.dal.dhl.states;

import com.dal.dhl.LeagueSimulation.GameScheduler;
import com.dal.dhl.stateMachine.StateMachine;

public class GeneratePlayoffSchedule implements IState {
	StateMachine stateMachine;

	public GeneratePlayoffSchedule(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {

	}

	@Override
	public void exit() {
		// stateMachine.getCurrState().entry();
	}

	@Override
	public IState doTask() {
		GameScheduler scheduler = new GameScheduler();
		scheduler.schedulePlayoff(stateMachine.getGame(), stateMachine);
		return stateMachine.getTraining();
	}

}
