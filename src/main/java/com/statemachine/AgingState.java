package com.statemachine;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.Trading;
import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

public class AgingState implements IState {
	StateMachine stateMachine;

	public AgingState(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		Game game = stateMachine.getGame();
		ILeague league = game.getLeagues().get(0);
		IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
		Trading trading = new Trading();
		ArrayList<IPlayer> freeAgents = league.getFreeAgents();
		for (IPlayer freeAgent : freeAgents) {
			freeAgent.agePlayer(1);
			if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear())) {
				System.out.println("Freeagent " + freeAgent.getPlayerName() + " retired!!");
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
							System.out
									.println(player.getPlayerName() + "from team " + team.getTeamName() + " retired!!");
							ArrayList<IPlayer> freeAgentsWithSamePosition = trading
									.getFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
							IPlayer freeAgent = trading.sortFreeAgentsOnStrength(freeAgentsWithSamePosition, 1, false)
									.get(0);
							team.addPlayer(freeAgent);
							team.removePlayer(player);
						}
					}
				}
			}
		}
	}

	@Override
	public void exit() {

	}

	@Override
	public IState doTask() {
		Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
		String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
		ILeague league = stateMachine.getGame().getLeagues().get(0);
		int year = Integer.parseInt(date[0]);
		Date endOfSeason = Date.valueOf("" + (year + 1) + "-06-01");
		if (currentDate.compareTo(endOfSeason) == 0) {
			league.getTeamStandings().sort((standing1, standing2) -> {
				double points1 = standing1.getTotalPoints();
				double points2 = standing2.getTotalPoints();
				if (points1 > points2) {
					return -1;
				} else {
					return 0;
				}
			});
			System.out.println("The stanley cup winner for season " + league.getSeason() + " is "
					+ league.getTeamStandings().get(0).getTeam().getTeamName());
			stateMachine.setCurrentState(stateMachine.getAdvanceNextSeason());
			stateMachine.getCurrentState().entry();
//			stateMachine.setCurrentState(stateMachine.getInitializeSeason());
			return stateMachine.getInitializeSeason();

		} else {
			return stateMachine.getPersist();

		}
	}

}
