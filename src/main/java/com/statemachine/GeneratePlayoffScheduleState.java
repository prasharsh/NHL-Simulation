package com.statemachine;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;

public class GeneratePlayoffScheduleState implements IState {

	@Override
	public void entry() {
	}


	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
       
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		HashMap<String, Double> averages = schedule.getScheduledGamesAverageStats(game.getLeagues().get(0).getGameSchedules());
			Iterator it = averages.entrySet().iterator();
			System.out.println("----------|Average Statistics|--------------");
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				System.out.println(pair.getKey() + " - " + String.format("%.2f", pair.getValue()));

			}
		schedule.schedulePlayoff(game, stateMachine);
		return stateFactory.createTrainingState();
	}
}