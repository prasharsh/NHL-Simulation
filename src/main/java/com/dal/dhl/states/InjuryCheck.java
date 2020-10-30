package com.dal.dhl.states;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Game;
import g4dhl.IInjury;
import g4dhl.IPlayer;
import g4dhl.ITeam;

public class InjuryCheck implements IState {
	StateMachine stateMachine;

	public InjuryCheck(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		IInjury injuryChance = game.getLeagues().get(0).getGamePlayConfig().getInjury();
		float randomInjuryChance = injuryChance.getRandomInjuryChance();
		HashSet<ITeam> teams = stateMachine.getGameDayTeams();

		for (ITeam team : teams) {
			ArrayList<IPlayer> players = team.getPlayers();
			for (IPlayer player : players) {
				Date recoveryDate = injuryChance.getRecoveryDate(currentDate);
				player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
			}
		}

		// stateMachine.setCurrState(stateMachine.getAdvanceTime());
		// stateMachine.getCurrState().doTask();

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public IState doTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
