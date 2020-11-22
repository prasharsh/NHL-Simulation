package com.statemachine;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITrading;
import com.datamodel.leaguedatamodel.Trading;

public class ExecuteTradesState implements IState {

	IStateMachine stateMachine;

	public ExecuteTradesState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
//		ITrading trading = new Trading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading());
		ILeague league = stateMachine.getGame().getLeagues().get(0);
		ITrading trading = new Trading(league);
//		trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(),
//				stateMachine.getGame().getLeagues().get(0), stateMachine.getTeamList());
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