package com.statemachine;

public class PersistState implements IState {
	StateMachine stateMachine;

	public PersistState(StateMachine stateMachine) {

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
		return stateMachine.getAdvanceTime();
	}

}