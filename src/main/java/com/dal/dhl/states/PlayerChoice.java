package com.dal.dhl.states;

import com.dal.dhl.stateMachine.StateMachine;

public class PlayerChoice implements IState{
	StateMachine stateMachine;



	public PlayerChoice(StateMachine stateMachine) {

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
		// TODO Auto-generated method stub
		return null;
	}

}
