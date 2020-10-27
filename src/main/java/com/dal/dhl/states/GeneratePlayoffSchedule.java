package com.dal.dhl.states;

import com.dal.dhl.LeagueSimulation.GameScheduler;
import com.dal.dhl.stateMachine.DHLStateMachine;

public class GeneratePlayoffSchedule implements IStateTransistion{
	DHLStateMachine stateMachine;



	public GeneratePlayoffSchedule(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		doTask();
		
	}

	@Override
	public void exit() {
		stateMachine.getCurrState().entry();
	}



	@Override
	public void doTask() {
		GameScheduler scheduler = new GameScheduler();
		scheduler.schedulePlayoff(stateMachine.getGame(), stateMachine);
		stateMachine.setCurrState(stateMachine.getTraining());
		exit();
	}

}
