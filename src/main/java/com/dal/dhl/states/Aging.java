package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class Aging implements IStateTransistion{
	DHLStateMachine stateMachine;



	public Aging(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// age all players on all teams and free agents by one day
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}

}
