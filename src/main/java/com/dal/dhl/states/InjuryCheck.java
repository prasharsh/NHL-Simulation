package com.dal.dhl.states;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import com.dal.dhl.stateMachine.DHLStateMachine;

import g4dhl.Game;
import g4dhl.IInjuredPlayer;
import g4dhl.IInjury;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.InjuredPlayer;
import g4dhl.Injury;

public class InjuryCheck implements IStateTransistion {
	DHLStateMachine stateMachine;

	public InjuryCheck(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		Date currentDate = game.getLeagues().get(0).getCurrentDate();
		IInjury injuryChance = new Injury();
		float randomInjuryChance = injuryChance.getRandomInjuryChance();

		HashSet<ITeam> teams = stateMachine.getGameDayTeams();
		for (ITeam team : teams) {
			System.out.println(team.getTeamName());
			ArrayList<IPlayer> players = team.getPlayers();
			for (IPlayer player : players) {
				if (player.checkPlayerInjury(randomInjuryChance)) {
					Date recoveryDate = injuryChance.getRecoveryDate(currentDate);
					IInjuredPlayer injuredPlayer = new InjuredPlayer();
					injuredPlayer.setPlayerId(player.getPlayerId());
					injuredPlayer.setTeamId(team.getTeamId());
					injuredPlayer.setRecoveryDate(recoveryDate);
					team.addInjuredPlayer(injuredPlayer);
				}
			}
			ArrayList<IInjuredPlayer> injuredPlayers = team.getInjuredPlayers();
			for (IInjuredPlayer injuredPlayer : injuredPlayers) {
				if (currentDate.compareTo(injuredPlayer.getRecoveryDate()) == 0) {
					team.removeInjuredPlayer(injuredPlayer);
					for (IPlayer player : players) {
						if (player.getPlayerId() == injuredPlayer.getPlayerId()) {
							player.setPlayerIsInjured(false);
						}
					}
				}
			}
		}

		stateMachine.setCurrState(stateMachine.getAdvanceTime());
		stateMachine.getCurrState().doTask();
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
