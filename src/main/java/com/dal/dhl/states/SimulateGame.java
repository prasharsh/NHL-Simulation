package com.dal.dhl.states;

import java.sql.Date;
import java.util.HashSet;

import com.dal.dhl.LeagueSimulation.SimulateMatch;
import com.dal.dhl.stateMachine.DHLStateMachine;

import g4dhl.IGame;
import g4dhl.IGameSchedule;
import g4dhl.ITeam;

public class SimulateGame implements IStateTransistion{
	DHLStateMachine stateMachine;



	public SimulateGame(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		

		SimulateMatch simulateMatch = new SimulateMatch();
		IGame game =stateMachine.getGame();
		for (IGameSchedule gameSchedule : game.getLeagues().get(0).getGameSchedules()) {
			Date curreDate = game.getLeagues().get(0).getCurrentDate();
			Date matchDate = gameSchedule.getMatchDate();
			if(curreDate.compareTo(matchDate)==0 && gameSchedule.getStatus().equals("scheduled")) {
				HashSet<ITeam> gameDayTeams = new HashSet<>();
				simulateMatch.simulateMatchResult(gameSchedule.getTeamA(),gameSchedule.getTeamA().getTeamStrength(),
						gameSchedule.getTeamB(),gameSchedule.getTeamA().getTeamStrength(), game.getLeagues().get(0).getGamePlayConfig().getGameResolver().getRandomWinChance(), game);
			gameDayTeams.add(gameSchedule.getTeamA());
			gameDayTeams.add(gameSchedule.getTeamB());
			gameSchedule.setStatus("played");
			stateMachine.setGameDayTeams(gameDayTeams);	
			stateMachine.setCurrState(stateMachine.getInjuryCheck());
			stateMachine.getCurrState().entry();
			}
		}
		
		//simulate one scheduled game --> win/loss

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
