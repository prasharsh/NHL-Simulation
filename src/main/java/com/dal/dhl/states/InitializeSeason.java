package com.dal.dhl.states;

import com.dal.dhl.LeagueSimulation.GameScheduler;
import com.dal.dhl.stateMachine.StateMachine;

public class InitializeSeason implements IState {
	StateMachine stateMachine;

	public InitializeSeason(StateMachine stateMachine) {

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
		GameScheduler gameScheduler = new GameScheduler();
		gameScheduler.scheduleRegularSeason(stateMachine.getGame(), stateMachine);
		return stateMachine.getAdvanceTime();
	}

}
