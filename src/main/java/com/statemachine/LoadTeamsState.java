package com.statemachine;
import com.datamodel.leaguedatamodel.LoadTeam;

public class LoadTeamsState implements IState {



	@Override
	public IState doTask() {
		return null;
	}

	@Override
	public void entry() {
		LoadTeam loadTeam = new LoadTeam();
		loadTeam.loadTeam();
	}

}