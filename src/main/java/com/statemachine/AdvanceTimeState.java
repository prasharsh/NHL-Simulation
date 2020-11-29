package com.statemachine;

import java.sql.Date;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.TimeConcept;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

public class AdvanceTimeState implements IState {

    private static final String REGULAR_SEASON_END_DATE = "seasonEndDate";
    IStateMachine stateMachine;


    @Override
    public void entry() {
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        Date currentDate = game.getLeagues().get(0).getCurrentDate();
        TimeConcept timeConcept = new TimeConcept();
        game.getLeagues().get(0).setCurrentDate(timeConcept.getNextDate(currentDate));
    }


    @Override
    public IState doTask() {
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        Date currentDate = game.getLeagues().get(0).getCurrentDate();
        String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date regularSeasonEndDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(REGULAR_SEASON_END_DATE));

        if (currentDate.compareTo(regularSeasonEndDate) == 0) {
            return stateFactory.createGeneratePlayoffScheduleState();
        } else {
            return stateFactory.createTrainingState();
        }
    }
}