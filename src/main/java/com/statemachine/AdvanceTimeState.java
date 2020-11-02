package com.statemachine;

import java.sql.Date;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.TimeConcept;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

public class AdvanceTimeState implements IState {

	private static final String REGULAR_SEASON_END_DATE = "seasonEndDate";
	StateMachine stateMachine;

	public AdvanceTimeState(StateMachine stateMachine) {
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
	}

	@Override
	public IState doTask() {
		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		IPropertyLoader propertyLoader = new PropertyLoader();
		Date regularSeasonEndDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(REGULAR_SEASON_END_DATE));
		if (currentDate.compareTo(regularSeasonEndDate) == 0) {
			return stateMachine.getGeneratePlayoffSchedule();
		} else {
			return stateMachine.getTraining();
		}
	}

}
