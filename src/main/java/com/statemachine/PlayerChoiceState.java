package com.statemachine;

public class PlayerChoiceState implements IState {
    StateMachine stateMachine;


    public PlayerChoiceState(StateMachine stateMachine) {

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
        return null;
    }

}
