package com.statemachine;

import com.datamodeltest.GameScheduler;

public class InitializeSeasonState implements IState {
    StateMachine stateMachine;


    public InitializeSeasonState(StateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
    }

    @Override
    public void exit() {
        //		stateMachine.getCurrState().entry();

    }


    @Override
    public IState doTask() {
        GameScheduler gameScheduler = new GameScheduler();
        gameScheduler.scheduleRegularSeason(stateMachine.getGame(), stateMachine);
        return stateMachine.getAdvanceTime();
    }

}
