package com.statemachine;

public class StateMachine implements IStateMachine {

	private IState currentState;

	public StateMachine(String path) {
		IState jsonImport = new JsonImportState(path);
		currentState = jsonImport;
	}

	private void transistionToState(IState toState) {
		currentState = toState;
		currentState.entry();
	}

	public void start() {
		while(currentState != null) {
			IState transistionState = currentState.doTask();
			if(transistionState == currentState) {
				continue;
			} else {
				transistionToState(transistionState);
			}
		}
	}

	public IState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IState currentState) {
		this.currentState = currentState;
	}

}