package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.persistencemodel.ILeagueDB;
import com.persistencemodel.PersistenceAbstractFactory;

public class PersistState implements IState {

	@Override
	public void entry() {
		PersistenceAbstractFactory persistFactory = PersistenceAbstractFactory.instance();
		LeagueDataModelAbstractFactory leagueFactory = LeagueDataModelAbstractFactory.instance();
		ILeagueDB leagueDB = persistFactory.getLeagueDB();
		IGame game = leagueFactory.createGame();
		game.saveToDb(leagueDB);
	}


	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		return stateFactory.createAdvanceTimeState();
	}
}