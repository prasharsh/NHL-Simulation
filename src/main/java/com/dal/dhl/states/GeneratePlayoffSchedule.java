package com.dal.dhl.states;

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
		// TODO Auto-generated method stub
		stateMachine.getCurrState().entry();
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		//use NHL playoff rules to create schedule
		stateMachine.setCurrState(stateMachine.getTraining());
		exit();
	}

}
