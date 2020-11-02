package com.statemachine;

import com.datamodel.leaguedatamodel.LoadTeam;

public class LoadTeamsState implements IState {

	StateMachine stateMachine;

	public LoadTeamsState(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public IState doTask() {
		return null;
	}

	@Override
	public void entry() {
		LoadTeam loadTeam = new LoadTeam();
		loadTeam.loadTeam();
	}

	@Override
	public void exit() {
	}

}
