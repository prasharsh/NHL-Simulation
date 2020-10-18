package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class InitializeSeason implements IStateTransistion{
	DHLStateMachine stateMachine;



	public InitializeSeason(DHLStateMachine stateMachine) {

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
		// TODO Auto-generated method stub
		//Generate Full Season schedule
		stateMachine.setCurrState(stateMachine.getAdvanceTime());
		exit();
	}

}
