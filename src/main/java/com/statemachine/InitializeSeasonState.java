package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import org.apache.log4j.Logger;

public class InitializeSeasonState implements IState {

	final static Logger logger = Logger.getLogger(InitializeSeasonState.class);

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		logger.info("Initializing season.");
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		IGameSchedule gameSchedule = factory.createGameSchedule();
		gameSchedule.scheduleRegularSeason(game, stateMachine);
		logger.info("General season schedule created.");
		return stateFactory.createAdvanceTimeState();
	}
}