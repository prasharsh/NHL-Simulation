package com.statemachine;

public class DraftPickState implements IState {
    IStateMachine stateMachine;

    public DraftPickState(IStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        //perform drafting
    }

    @Override
    public IState doTask() {
        return null;
    }

    @Override
    public void exit() {

    }
}
