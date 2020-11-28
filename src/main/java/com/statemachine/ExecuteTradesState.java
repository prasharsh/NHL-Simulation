package com.statemachine;
import com.datamodel.leaguedatamodel.*;

public class ExecuteTradesState implements IState {

	IStateMachine stateMachine;

	public ExecuteTradesState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		ILeague league = LeagueDataModelAbstractFactory.instance().createLeague();
		ITrading trading = LeagueDataModelAbstractFactory.instance().createTrading();

		for (ITeam team: league.getAllTeams()) {
			team.proposeTrade(trading);
		}
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		return stateMachine.getAging();
	}
}