package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;
import com.dal.dhl.stateMachine.LoadTeam;

public class LoadTeams implements IStateTransistion{

	DHLStateMachine stateMachine;


	public LoadTeams(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}
	@Override
	public void doTask() {

	}

	@Override
	public void entry() {
		LoadTeam loadTeam = new LoadTeam();
		loadTeam.loadtTeam();
	}

	@Override
	public void exit() {
		stateMachine.getCurrState().entry();
	}

}
