package com.statemachine;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Trading;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

public class AgingState implements IState {

	private static final String END_OF_SEASON = "playoffEndDate";

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
			if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear()) && (freeAgent.isPlayerRetired() == false)) {
				System.out.println("Freeagent " + freeAgent.getPlayerName() + " retired!!");
				freeAgent.setPlayerRetired(true);
			}
		}
		ArrayList<IConference> conferences = league.getConferences();
		for (IConference conference : conferences) {
			ArrayList<IDivision> divisions = conference.getDivisions();
			for (IDivision division : divisions) {
				ArrayList<ITeam> teams = division.getTeams();
				for (ITeam team : teams) {
					ArrayList<IPlayer> players = new ArrayList<>(team.getPlayers());
					for (IPlayer player : players) {
						player.agePlayer(1);
						if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
							player.setPlayerRetired(true);
							System.out.println(
									player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
							ArrayList<IPlayer> freeAgentsWithSamePosition = trading
									.getFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
							IPlayer freeAgent = trading.sortFreeAgentsOnStrength(freeAgentsWithSamePosition, 1, false)
									.get(0);
							team.addPlayer(freeAgent);
							league.removeFreeAgent(freeAgent);
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
		IPropertyLoader propertyLoader = new PropertyLoader();
		Date endOfSeason = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(END_OF_SEASON));
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
			return stateMachine.getInitializeSeason();
		} else {
			// return stateMachine.getPersist();
			return stateMachine.getAdvanceTime();
		}
	}

}
