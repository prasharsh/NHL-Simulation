package com.dal.dhl.states;

import com.dal.dhl.stateMachine.StateMachine;

import trading.ITrading;
import trading.Trading;

public class ExecuteTrades implements IState{
	StateMachine stateMachine;

	public ExecuteTrades(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		ITrading trading = new Trading();
		trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(), stateMachine.getGame().getLeagues().get(0),
				stateMachine.getTeamList());
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		return stateMachine.getAging();
	}
}
