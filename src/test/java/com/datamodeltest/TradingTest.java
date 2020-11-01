
package com.datamodeltest;

import static com.datamodel.Constants.DEFENSE;
import static com.datamodel.Constants.FORWARD;
import static com.datamodel.Constants.GOALIE;
import static com.datamodel.Constants.SKATER;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.datamodel.ITrading;
import com.datamodel.Trading;
import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.leaguedatamodel.Team;

public class TradingTest {

	@Test
	public void sortPlayersOnStrengthWeakestFirstTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> playersToBeSorted = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition(FORWARD);
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition(DEFENSE);
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		playersToBeSorted.add(player1);
		playersToBeSorted.add(player2);

		ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, 2, true);
		Assert.assertTrue("Players should be sorted in increasing order of strength",
				players.get(0).getPlayerStrength() <= players.get(1).getPlayerStrength());
	}

	@Test
	public void sortPlayersOnStrengthStrongestFirstTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> playersToBeSorted = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition(FORWARD);
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition(DEFENSE);
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		playersToBeSorted.add(player1);
		playersToBeSorted.add(player2);

		ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, 2, false);
		Assert.assertTrue("Players should be sorted in decreasing order of strength",
				players.get(0).getPlayerStrength() >= players.get(1).getPlayerStrength());
	}

	@Test
	public void getPlayersWithPositionTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> players = new ArrayList<>();
		ArrayList<IPlayer> goalies = new ArrayList<>();
		ArrayList<IPlayer> skaters = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition(GOALIE);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition(FORWARD);

		IPlayer player3 = new Player();
		player3.setPlayerName("Harpreet");
		player3.setPlayerPosition(DEFENSE);

		players.add(player1);
		players.add(player2);
		players.add(player3);

		goalies.add(player1);
		skaters.add(player2);
		skaters.add(player3);

		ArrayList<IPlayer> playersWithPositionGoalie = trading.getPlayersWithPosition(players, GOALIE);
		ArrayList<IPlayer> playersWithPositionSkater = trading.getPlayersWithPosition(players, SKATER);

		Assert.assertEquals(goalies, playersWithPositionGoalie);
		Assert.assertEquals(skaters, playersWithPositionSkater);
	}

	@Test
	public void calculateTotalStrengthOfPlayersTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> players = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition(FORWARD);
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition(DEFENSE);
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		players.add(player1);
		players.add(player2);

		double strength = trading.calculateTotalStrengthOfPlayers(players);

		Assert.assertEquals(player1.getPlayerStrength() + player2.getPlayerStrength(), strength, 0);
	}

	@Test
	public void sortFreeAgentsOnStrengthWeakestFirstTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> freeAgentsToBeSorted = new ArrayList<>();

		IPlayer freeAgent1 = new FreeAgent();
		freeAgent1.setPlayerName("Rob");
		freeAgent1.setPlayerPosition(FORWARD);
		freeAgent1.setPlayerSaving(5);
		freeAgent1.setPlayerChecking(5);
		freeAgent1.setPlayerShooting(5);
		freeAgent1.setPlayerSkating(5);

		IPlayer freeAgent2 = new FreeAgent();
		freeAgent2.setPlayerName("Hawkey");
		freeAgent2.setPlayerPosition(DEFENSE);
		freeAgent2.setPlayerSaving(2);
		freeAgent2.setPlayerChecking(2);
		freeAgent2.setPlayerShooting(2);
		freeAgent2.setPlayerSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IPlayer> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, 2, true);

		Assert.assertTrue("Free Agents should be sorted in increasing order of strength",
				freeAgents.get(0).getPlayerStrength() <= freeAgents.get(1).getPlayerStrength());
	}

	@Test
	public void sortFreeAgentsOnStrengthStrongestFirstTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> freeAgentsToBeSorted = new ArrayList<>();

		IPlayer freeAgent1 = new FreeAgent();
		freeAgent1.setPlayerName("Rob");
		freeAgent1.setPlayerPosition(FORWARD);
		freeAgent1.setPlayerSaving(5);
		freeAgent1.setPlayerChecking(5);
		freeAgent1.setPlayerShooting(5);
		freeAgent1.setPlayerSkating(5);

		IPlayer freeAgent2 = new FreeAgent();
		freeAgent2.setPlayerName("Hawkey");
		freeAgent2.setPlayerPosition(DEFENSE);
		freeAgent2.setPlayerSaving(2);
		freeAgent2.setPlayerChecking(2);
		freeAgent2.setPlayerShooting(2);
		freeAgent2.setPlayerSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IPlayer> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, 2, false);

		Assert.assertTrue("Free Agents should be sorted in decreasing order of strength",
				freeAgents.get(0).getPlayerStrength() >= freeAgents.get(1).getPlayerStrength());
	}

	@Test
	public void getFreeAgentsWithPositionTest() {

		ITrading trading = new Trading();
		ArrayList<IPlayer> freeAgents = new ArrayList<>();
		ArrayList<IPlayer> goalies = new ArrayList<>();
		ArrayList<IPlayer> skaters = new ArrayList<>();

		IPlayer freeAgent1 = new FreeAgent();
		freeAgent1.setPlayerName("Rob");
		freeAgent1.setPlayerPosition(GOALIE);

		IPlayer freeAgent2 = new FreeAgent();
		freeAgent2.setPlayerName("Hawkey");
		freeAgent2.setPlayerPosition(DEFENSE);

		IPlayer freeAgent3 = new FreeAgent();
		freeAgent3.setPlayerName("Harpreet");
		freeAgent3.setPlayerPosition(FORWARD);

		freeAgents.add(freeAgent1);
		freeAgents.add(freeAgent2);
		freeAgents.add(freeAgent3);

		goalies.add(freeAgent1);
		skaters.add(freeAgent2);
		skaters.add(freeAgent3);

		ArrayList<IPlayer> freeAgentsWithGoaliePosition = trading.getFreeAgentsWithPosition(freeAgents, GOALIE);
		ArrayList<IPlayer> freeAgentsWithSkaterPosition = trading.getFreeAgentsWithPosition(freeAgents, SKATER);

		Assert.assertEquals(goalies, freeAgentsWithGoaliePosition);
		Assert.assertEquals(skaters, freeAgentsWithSkaterPosition);
	}

	@Test
	public void dropWeakestPlayersToFreeAgentListTest() {

		ITrading trading = new Trading();
		ILeague league = new League();

		ITeam team = new Team();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition(FORWARD);
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition(DEFENSE);
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		team.addPlayer(player1);
		team.addPlayer(player2);

		trading.dropWeakestPlayersToFreeAgentList(league, team, FORWARD, 1);

		Assert.assertEquals(player1.getPlayerName(), league.getFreeAgents().get(0).getPlayerName());
		Assert.assertEquals(player2, team.getPlayers().get(0));
	}

	@Test
	public void hireStrongestPlayersFromFreeAgentListTest() {

		ITrading trading = new Trading();
		ILeague league = new League();

		ITeam team = new Team();

		IPlayer freeAgent1 = new FreeAgent();
		freeAgent1.setPlayerName("Rob");
		freeAgent1.setPlayerPosition(FORWARD);
		freeAgent1.setPlayerSaving(5);
		freeAgent1.setPlayerChecking(5);
		freeAgent1.setPlayerShooting(5);
		freeAgent1.setPlayerSkating(5);

		IPlayer freeAgent2 = new FreeAgent();
		freeAgent2.setPlayerName("Hawkey");
		freeAgent2.setPlayerPosition(DEFENSE);
		freeAgent2.setPlayerSaving(2);
		freeAgent2.setPlayerChecking(2);
		freeAgent2.setPlayerShooting(2);
		freeAgent2.setPlayerSkating(2);

		league.addFreeAgent(freeAgent1);
		league.addFreeAgent(freeAgent2);

		trading.hireStrongestPlayersFromFreeAgentList(league, team, SKATER, 1);

		Assert.assertEquals(freeAgent1.getPlayerName(), team.getPlayers().get(0).getPlayerName());
		Assert.assertEquals(freeAgent2, league.getFreeAgents().get(0));
	}

	@Test
	public void acceptTradeOfferTest() {

		ITrading trading = new Trading();

		ITeam team1 = new Team();
		team1.setTeamName("team1");
		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		team1.addPlayer(player1);

		ArrayList<IPlayer> team1players = new ArrayList<>();
		team1players.add(player1);

		ITeam team2 = new Team();
		team2.setTeamName("team2");
		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		team2.addPlayer(player2);

		ArrayList<IPlayer> team2players = new ArrayList<>();
		team2players.add(player2);

		trading.acceptTradeOffer(team1, team1players, team2, team2players);
		Assert.assertEquals(team1.getPlayers().get(0), player2);
		Assert.assertEquals(team2.getPlayers().get(0), player1);
	}

}