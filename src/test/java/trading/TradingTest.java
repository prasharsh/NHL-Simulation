package trading;

import java.util.ArrayList;

import g4dhl.*;
import org.junit.Assert;
import org.junit.Test;
import static trading.Constants.FORWARD;
import static trading.Constants.DEFENSE;
import static trading.Constants.GOALIE;
import static trading.Constants.SKATER;

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
		ArrayList<IFreeAgent> freeAgentsToBeSorted = new ArrayList<>();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition(FORWARD);
		freeAgent1.setFreeAgentSaving(5);
		freeAgent1.setFreeAgentChecking(5);
		freeAgent1.setFreeAgentShooting(5);
		freeAgent1.setFreeAgentSkating(5);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition(DEFENSE);
		freeAgent2.setFreeAgentSaving(2);
		freeAgent2.setFreeAgentChecking(2);
		freeAgent2.setFreeAgentShooting(2);
		freeAgent2.setFreeAgentSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IFreeAgent> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, 2, true);

		Assert.assertTrue("Free Agents should be sorted in increasing order of strength",
				freeAgents.get(0).getFreeAgentStrength() <= freeAgents.get(1).getFreeAgentStrength());
	}

	@Test
	public void sortFreeAgentsOnStrengthStrongestFirstTest() {

		ITrading trading = new Trading();
		ArrayList<IFreeAgent> freeAgentsToBeSorted = new ArrayList<>();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition(FORWARD);
		freeAgent1.setFreeAgentSaving(5);
		freeAgent1.setFreeAgentChecking(5);
		freeAgent1.setFreeAgentShooting(5);
		freeAgent1.setFreeAgentSkating(5);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition(DEFENSE);
		freeAgent2.setFreeAgentSaving(2);
		freeAgent2.setFreeAgentChecking(2);
		freeAgent2.setFreeAgentShooting(2);
		freeAgent2.setFreeAgentSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IFreeAgent> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, 2, false);

		Assert.assertTrue("Free Agents should be sorted in decreasing order of strength",
				freeAgents.get(0).getFreeAgentStrength() >= freeAgents.get(1).getFreeAgentStrength());
	}

	@Test
	public void getFreeAgentsWithPositionTest() {

		ITrading trading = new Trading();
		ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
		ArrayList<IFreeAgent> goalies = new ArrayList<>();
		ArrayList<IFreeAgent> skaters = new ArrayList<>();


		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition(GOALIE);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition(DEFENSE);

		IFreeAgent freeAgent3 = new FreeAgent();
		freeAgent3.setFreeAgentName("Harpreet");
		freeAgent3.setFreeAgentPosition(FORWARD);

		freeAgents.add(freeAgent1);
		freeAgents.add(freeAgent2);
		freeAgents.add(freeAgent3);

		goalies.add(freeAgent1);
		skaters.add(freeAgent2);
		skaters.add(freeAgent3);

		ArrayList<IFreeAgent> freeAgentsWithGoaliePosition = trading.getFreeAgentsWithPosition(freeAgents, GOALIE);
		ArrayList<IFreeAgent> freeAgentsWithSkaterPosition = trading.getFreeAgentsWithPosition(freeAgents, SKATER);

		Assert.assertEquals(goalies, freeAgentsWithGoaliePosition);
		Assert.assertEquals(skaters, freeAgentsWithSkaterPosition);
	}

	@Test
	public void dropWeakestPlayersToFreeAgentListTest(){

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

		Assert.assertEquals(player1.getPlayerName(), league.getFreeAgents().get(0).getFreeAgentName());
		Assert.assertEquals(player2, team.getPlayers().get(0));
	}

	@Test
	public void hireStrongestPlayersFromFreeAgentListTest(){

		ITrading trading = new Trading();
		ILeague league = new League();

		ITeam team = new Team();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition(FORWARD);
		freeAgent1.setFreeAgentSaving(5);
		freeAgent1.setFreeAgentChecking(5);
		freeAgent1.setFreeAgentShooting(5);
		freeAgent1.setFreeAgentSkating(5);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition(DEFENSE);
		freeAgent2.setFreeAgentSaving(2);
		freeAgent2.setFreeAgentChecking(2);
		freeAgent2.setFreeAgentShooting(2);
		freeAgent2.setFreeAgentSkating(2);

		league.addFreeAgent(freeAgent1);
		league.addFreeAgent(freeAgent2);

		trading.hireStrongestPlayersFromFreeAgentList(league, team, SKATER, 1);

		Assert.assertEquals(freeAgent1.getFreeAgentName(), team.getPlayers().get(0).getPlayerName());
		Assert.assertEquals(freeAgent2, league.getFreeAgents().get(0));
	}

	@Test
	public void acceptTradeOfferTest(){

		ITrading trading = new Trading();

		ITeam team1 = new Team();
		team1.setTeamName("team1");
		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		team1.addPlayer(player1);

		ITeam team2 = new Team();
		team2.setTeamName("team2");
		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		team2.addPlayer(player2);

		trading.acceptTradeOffer(team1, team1.getPlayers(), team2, team2.getPlayers());
		Assert.assertEquals(team1.getPlayers().get(0), player2);
		Assert.assertEquals(team2.getPlayers().get(0), player1);
	}

}
