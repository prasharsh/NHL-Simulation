package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;
import trading.Trading;

public class ExecuteTrades implements IStateTransistion{
	DHLStateMachine stateMachine;



	public ExecuteTrades(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		Trading trading = new Trading();
		trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(), stateMachine.getTeamList(),
				stateMachine.getGame().getLeagues().get(0).getFreeAgents());
		stateMachine.getGame().getLeagues().get(0).getFreeAgents();
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
