package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class Persist implements IStateTransistion{
	DHLStateMachine stateMachine;



	public Persist(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		
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
