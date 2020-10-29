package com.dal.dhl.states;

import com.dal.dhl.stateMachine.LoadTeam;
import com.dal.dhl.stateMachine.StateMachine;

public class LoadTeams implements IState{

	StateMachine stateMachine;


	public LoadTeams(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}
	@Override
	public IState doTask() {
return null;
	}

	@Override
	public void entry() {
		LoadTeam loadTeam = new LoadTeam();
		loadTeam.loadtTeam();
	}

	@Override
	public void exit() {
		//stateMachine.getCurrState().entry();
	}

}
