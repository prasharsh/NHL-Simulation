package com.dal.dhl.states;

import com.dal.dhl.stateMachine.StateMachine;
import com.dal.dhl.stateMachine.StateMachine11;

public class Persist implements IState{
	StateMachine stateMachine;



	public Persist(StateMachine stateMachine) {

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
	public IState doTask() {
		return stateMachine.getAdvanceTime();
	}

}
