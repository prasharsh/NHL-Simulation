package com.dal.dhl.states;

import java.sql.Date;
import java.util.ArrayList;

import com.dal.dhl.stateMachine.StateMachine;
import com.dal.dhl.stateMachine.StateMachine11;

import g4dhl.Game;
import g4dhl.IAging;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import trading.FreeAgentToPlayer;

public class AdvanceNextSeason implements IState {
	StateMachine stateMachine;

	public AdvanceNextSeason(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Date currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date nextSeasonStartDate = Date.valueOf("" + (year+stateMachine.getGame().getLeagues().get(0).getSeason()) + "-09-30");
		long timeDiff = nextSeasonStartDate.getTime() - currDate.getTime();
		int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
		Game game = stateMachine.getGame();
		game.getLeagues().get(0).setSeason(game.getLeagues().get(0).getSeason()+1);
		game.getLeagues().get(0).setSimulationStartDate(nextSeasonStartDate);
		game.getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
		if(game.getLeagues().get(0).getSeason()>game.getLeagues().get(0).getSeasonToSimulate()+1) {
			System.out.println("end of DHL");
			System.exit(0);
		}
		ILeague league = game.getLeagues().get(0);
		IAging aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
		trading.Trading trading = new trading.Trading();
		ArrayList<IFreeAgent> freeAgents = league.getFreeAgents();
		for (IFreeAgent freeAgent : freeAgents) {
			freeAgent.ageFreeAgent(daysToAge);
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
						player.agePlayer(daysToAge);
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
		stateMachine.getGame().getLeagues().get(0).setCurrentDate(nextSeasonStartDate);

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public IState doTask() {
		return stateMachine.getPersist();

	}

}
