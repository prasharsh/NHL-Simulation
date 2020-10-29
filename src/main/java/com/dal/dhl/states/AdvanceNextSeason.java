package com.dal.dhl.states;

import java.sql.Date;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class AdvanceNextSeason implements IStateTransistion{
	DHLStateMachine stateMachine;



	public AdvanceNextSeason(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Date currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
		int nextYear = currDate.getYear()+1;
		Date nextSeasonStartDate = Date.valueOf(""+(nextYear)+"-09-30");
		stateMachine.getGame().getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
		
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}

}
