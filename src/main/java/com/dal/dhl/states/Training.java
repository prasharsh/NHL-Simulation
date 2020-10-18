package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class Training implements IStateTransistion{
	DHLStateMachine stateMachine;



	public Training(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		//increment day since last training by 1
		doTask();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		//if days since last stat check have passed, perform stat increase - check for all teams / players
		
	}

	

}
