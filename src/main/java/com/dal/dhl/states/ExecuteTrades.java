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
		Trading trading = new Trading();
		trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(), stateMachine.getGame().getLeagues().get(0),
				stateMachine.getTeamList());
	}

	@Override
	public void exit() {
	}

	@Override
	public void doTask() {
	}
}
