package com.statemachine;

import com.datamodeltest.GameScheduler;

public class GeneratePlayoffScheduleState implements IState {
    StateMachine stateMachine;


    public GeneratePlayoffScheduleState(StateMachine stateMachine) {

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
        GameScheduler scheduler = new GameScheduler();
        scheduler.schedulePlayoff(stateMachine.getGame(), stateMachine);
        return stateMachine.getTraining();
    }

}
