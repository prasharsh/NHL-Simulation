package com.dal.dhl.states;

import java.sql.Date;
import java.util.ArrayList;

import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Game;
import g4dhl.IAging;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import trading.FreeAgentToPlayer;

public class Aging implements IState {
	StateMachine stateMachine;

	public Aging(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		ILeague league = game.getLeagues().get(0);
		IAging aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
		trading.Trading trading = new trading.Trading();
		ArrayList<IFreeAgent> freeAgents = league.getFreeAgents();
		for (IFreeAgent freeAgent : freeAgents) {
			freeAgent.ageFreeAgent(1);
			if (aging.isPlayerRetires(freeAgent.getFreeAgentAgeYear())) {
				league.removeFreeAgent(freeAgent);
			}
		}
		ArrayList<IConference> conferences = league.getConferences();
		for (IConference conference : conferences) {
			ArrayList<IDivision> divisions = conference.getDivisions();
			for (IDivision division : divisions) {
				ArrayList<ITeam> teams = division.getTeams();
				for (ITeam team : teams) {
					ArrayList<IPlayer> players = team.getPlayers();
					for (IPlayer player : players) {
						player.agePlayer(1);
						if (aging.isPlayerRetires(player.getPlayerAgeYear())) {
							ArrayList<IFreeAgent> freeAgentsWithSamePosition = trading
									.getFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
							IFreeAgent freeAgent = trading
									.sortFreeAgentsOnStrength(freeAgentsWithSamePosition, 1, false).get(0);
							FreeAgentToPlayer freeAgentToPlayer = new FreeAgentToPlayer(freeAgent);
							team.addPlayer(freeAgentToPlayer.getPlayer());
							team.removePlayer(player);
						}
					}
				}
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public IState doTask() {
		Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date endOfSeason = Date.valueOf(""+(year+1)+"-06-01");
		System.out.println(currentDate);
		if(currentDate.compareTo(endOfSeason)==0) {
			return stateMachine.getAdvanceNextSeason();

		}
		else {
			return stateMachine.getPersist();

		}
	}

}
