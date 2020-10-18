package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class SimulateGame implements IStateTransistion{
	DHLStateMachine stateMachine;



	public SimulateGame(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		//simulate one scheduled game --> win/loss
		stateMachine.setCurrState(stateMachine.getInjuryCheck());
		stateMachine.getCurrState().entry();
		
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
