package com.statemachine;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class GeneratePlayoffScheduleState implements IState {

	final static Logger logger = Logger.getLogger(GeneratePlayoffScheduleState.class);

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
       
    	LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelAbstractFactory.instance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		HashMap<String, Double> averages = schedule.getScheduledGamesAverageStats(game.getLeagues().get(0).getGameSchedules());
			Iterator it = averages.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				logger.debug("Average Statistics-- "+pair.getKey() + " - " + String.format("%.2f", pair.getValue()));

			}
		schedule.schedulePlayoff(game, stateMachine);
		stateFactory.createTrophySystemState().doTask();
		return stateFactory.createTrainingState();
	}
}