package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GeneratePlayoffScheduleState implements IState {

	final static Logger logger = Logger.getLogger(GeneratePlayoffScheduleState.class);

	@Override
	public IState doTask() {
		logger.info("End of regular season.");
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelAbstractFactory.instance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		HashMap<String, Double> averages =
				schedule.getScheduledGamesAverageStats(game.getLeagues().get(0).getGameSchedules());
		Iterator it = averages.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			logger.info("Average Statistics-- " + pair.getKey() + " - " + String.format("%.2f", pair.getValue()));
		}
		schedule.schedulePlayoff(game, stateMachine);
		logger.info("Playoff schedule created.");
		stateFactory.createTrophySystemState().doTask();
		return stateFactory.createTrainingState();
	}
}