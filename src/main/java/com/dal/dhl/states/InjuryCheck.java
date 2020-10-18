package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class InjuryCheck implements IStateTransistion{
	DHLStateMachine stateMachine;



	public InjuryCheck(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		//check both teams for injury
		stateMachine.setCurrState(stateMachine.getAdvanceTime());
		stateMachine.getCurrState().doTask();
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
