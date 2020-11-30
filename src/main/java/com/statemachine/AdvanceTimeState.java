package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.TimeConcept;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import org.apache.log4j.Logger;

import java.sql.Date;

public class AdvanceTimeState implements IState {

	final static Logger logger = Logger.getLogger(AdvanceTimeState.class);

	private static final String REGULAR_SEASON_END_DATE = "seasonEndDate";
	IStateMachine stateMachine;

	@Override
	public void entry() {
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		TimeConcept timeConcept = new TimeConcept();
		currentDate = timeConcept.getNextDate(currentDate);
		game.getLeagues().get(0).setCurrentDate(currentDate);
		logger.info("Advanced date by one day, current date: " + currentDate);
	}


	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IPropertyLoader propertyLoader = ioFactory.createPropertyLoader();
		Date regularSeasonEndDate =
				Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(REGULAR_SEASON_END_DATE));

		if(currentDate.compareTo(regularSeasonEndDate) == 0) {
			return stateFactory.createGeneratePlayoffScheduleState();
		} else {
			return stateFactory.createTrainingState();
		}
	}
}