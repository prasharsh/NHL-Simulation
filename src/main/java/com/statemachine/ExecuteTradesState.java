package com.statemachine;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITrading;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.Trading;

public class ExecuteTradesState implements IState {

	@Override
	public void entry() {

		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		ILeague league = game.getLeagues().get(0);
		ITrading trading = new Trading();

		for (ITeam team: league.getAllTeams()) {
			team.proposeTrade(trading);
		}
	}

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();

		return stateFactory.createAgingState();
	}
}