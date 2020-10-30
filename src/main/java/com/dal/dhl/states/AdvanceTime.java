package com.dal.dhl.states;

import java.sql.Date;

import com.dal.dhl.LeagueSimulation.TimeConcept;
import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Game;

public class AdvanceTime implements IState {
	StateMachine stateMachine;

	public AdvanceTime(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		TimeConcept timeConcept = new TimeConcept();
		game.getLeagues().get(0).setCurrentDate(timeConcept.getNextDate(currentDate));

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		// stateMachine.getCurrState().entry();
	}

	@Override
	public IState doTask() {

		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date regularSeasonEndDate = Date.valueOf("" + (year + 1) + "-04-01");
		if (currentDate.compareTo(regularSeasonEndDate) == 0) {
			return stateMachine.getGeneratePlayoffSchedule();
		} else {
			return stateMachine.getTraining();
		}
	}

}
