package com.statemachine;

public class PlayerChoiceState implements IState {

	IStateMachine stateMachine;

	public PlayerChoiceState(IStateMachine stateMachine) {
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