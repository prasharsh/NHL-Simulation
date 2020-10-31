package com.statemachine;

import java.sql.Date;

import com.datamodeltest.TimeConcept;

import com.datamodeltest.leaguedatamodel.Game;

public class AdvanceTimeState implements IState {
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
        Date regularSeasonEndDate = Date.valueOf("" + (year + 1) + "-04-01");
        if (currentDate.compareTo(regularSeasonEndDate) == 0) {
            return stateMachine.getGeneratePlayoffSchedule();
        } else {
            return stateMachine.getTraining();
        }
    }

}
