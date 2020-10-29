package com.dal.dhl.states;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.dal.dhl.LeagueSimulation.TimeConcept;
import com.dal.dhl.stateMachine.DHLStateMachine;

import g4dhl.Game;

public class AdvanceTime implements IStateTransistion{
	DHLStateMachine stateMachine;



	public AdvanceTime(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		TimeConcept timeConcept = new TimeConcept();
		//advance time
		game.getLeagues().get(0).setCurrentDate(timeConcept.getNextDate(currentDate));
		
		String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date regularSeasonEndDate = Date.valueOf(""+(year+1)+"-04-01");
		if(currentDate.compareTo(regularSeasonEndDate) == 0) {
			stateMachine.setCurrState(stateMachine.getGeneratePlayoffSchedule());
			stateMachine.getCurrState().entry();
		}
		else {
			stateMachine.setCurrState(stateMachine.getTraining());
			stateMachine.getCurrState().entry();
			//post training
			doTask();
		}

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		stateMachine.getCurrState().entry();
	}



	@Override
	public void doTask() {
		
		stateMachine.setCurrState(stateMachine.getSimulateGame());
		stateMachine.getCurrState().entry();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date tradeEndMonth = Date.valueOf(""+(year+1)+"-03-01");
		LocalDate tradeEndDate = tradeEndMonth.toLocalDate()
				.with( TemporalAdjusters.previous(DayOfWeek.MONDAY));
		
		Date lastTradeDate = Date.valueOf(tradeEndDate);
		Date currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
		if(currDate.compareTo(lastTradeDate) <0) {
			stateMachine.setCurrState(stateMachine.getExecuteTrades());
			stateMachine.getCurrState().entry();
		}
		else {
			stateMachine.getGame();
		}
		stateMachine.setCurrState(stateMachine.getAging());
		stateMachine.getCurrState().entry();
		Date endOfSeason = Date.valueOf(""+(year+1)+"-06-01");
		if(currDate.compareTo(endOfSeason)==0) {
			stateMachine.setCurrState(stateMachine.getAdvanceNextSeason());
			stateMachine.getCurrState().entry();
			stateMachine.setCurrState(stateMachine.getPersist());
			stateMachine.getCurrState().entry();
			
			System.out.println("end  of simulation");
		}
		else {
			stateMachine.setCurrState(stateMachine.getPersist());
			stateMachine.getCurrState().entry();
			stateMachine.setCurrState(stateMachine.getAdvanceTime());
			stateMachine.getCurrState().entry();
			System.out.println("");
		}
		

	}

}
