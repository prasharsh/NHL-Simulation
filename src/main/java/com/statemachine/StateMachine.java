package com.statemachine;

import java.util.HashSet;
import java.util.List;

import com.datamodel.leaguedatamodel.ITeam;

public class StateMachine implements IStateMachine {

	private IState currentState;

	public StateMachine(String path) {
		if(path==null) {
			System.out.println("no path passed");
		} else {
			IState jsonImport = new JsonImportState(path);
			currentState = jsonImport;
		}
	}

	private void transistionToState(IState toState) {
		currentState = toState;
		currentState.entry();
	}

	public void start() {
		while (currentState != null) {
			IState transistionState = currentState.doTask();
			if (transistionState != currentState) {
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