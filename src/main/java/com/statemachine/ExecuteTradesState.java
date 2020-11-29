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

		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
//		ITrading trading = new Trading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading());
//		ILeague league = stateMachine.getGame().getLeagues().get(0);
		ILeague league = game.getLeagues().get(0);
		ITrading trading = new Trading(league);
//		trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(),
//				stateMachine.getGame().getLeagues().get(0), stateMachine.getTeamList());

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