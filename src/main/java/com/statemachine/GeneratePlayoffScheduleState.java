package com.statemachine;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;

public class GeneratePlayoffScheduleState implements IState {

	IStateMachine stateMachine;

	public GeneratePlayoffScheduleState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();

		HashMap<String, Double> averages = schedule.getScheduledGamesAverageStats(stateMachine.getGame().getLeagues().get(0).getGameSchedules());
			Iterator it = averages.entrySet().iterator();
			System.out.println("----------|Average Statistics|--------------");
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				System.out.println(pair.getKey() + " - " + String.format("%.2f", pair.getValue()));

			}
		schedule.schedulePlayoff(stateMachine.getGame(), stateMachine);
		return stateMachine.getTraining();
	}
}