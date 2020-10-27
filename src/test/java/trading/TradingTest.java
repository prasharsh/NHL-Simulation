package trading;

import java.util.ArrayList;

import g4dhl.*;
import org.junit.Assert;
import org.junit.Test;

public class TradingTest {

	@Test
	public void sortPlayersOnStrengthInAscendingOrderTest() {

		Trading trading = new Trading();
		ArrayList<IPlayer> playersToBeSorted = new ArrayList<>();
		int playersCount = 2;

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition("forward");
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition("forward");
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		playersToBeSorted.add(player1);
		playersToBeSorted.add(player2);

		ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, playersCount, true);
		Assert.assertTrue("Players should be sorted in ascending order",
				players.get(0).getPlayerStrength() <= players.get(1).getPlayerStrength());
	}

	@Test
	public void sortPlayersOnStrengthInDescendingOrderTest() {

		Trading trading = new Trading();
		ArrayList<IPlayer> playersToBeSorted = new ArrayList<>();
		int playersCount = 2;

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition("forward");
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition("forward");
		player2.setPlayerSaving(2);
		player2.setPlayerChecking(2);
		player2.setPlayerShooting(2);
		player2.setPlayerSkating(2);

		playersToBeSorted.add(player1);
		playersToBeSorted.add(player2);

		ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, playersCount, false);
		Assert.assertTrue("Players should be sorted in descending order",
				players.get(0).getPlayerStrength() >= players.get(1).getPlayerStrength());
	}

	@Test
	public void acceptTradeOfferTest(){

		Trading trading = new Trading();

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

	@Test
	public void getPlayersWithPositionTest() {

		Trading trading = new Trading();
		ArrayList<IPlayer> players = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition("goalie");

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition("defense");

		IPlayer player3 = new Player();
		player3.setPlayerName("Harpreet");
		player3.setPlayerPosition("forward");

		players.add(player1);
		players.add(player2);
		players.add(player3);

		ArrayList<IPlayer> playersWithPosition = trading.getPlayersWithPosition(players, "goalie");

		Assert.assertEquals(player1, playersWithPosition.get(0));
	}

	@Test
	public void calculateTotalStrengthOfPlayersTest() {

		Trading trading = new Trading();
		ArrayList<IPlayer> players = new ArrayList<>();

		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		player1.setPlayerPosition("forward");
		player1.setPlayerSaving(5);
		player1.setPlayerChecking(5);
		player1.setPlayerShooting(5);
		player1.setPlayerSkating(5);

		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		player2.setPlayerPosition("forward");
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
	public void sortFreeAgentsOnStrengthInAscendingOrderTest() {

		Trading trading = new Trading();
		ArrayList<IFreeAgent> freeAgentsToBeSorted = new ArrayList<>();
		int freeAgentsCount = 2;

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition("forward");
		freeAgent1.setFreeAgentSaving(5);
		freeAgent1.setFreeAgentChecking(5);
		freeAgent1.setFreeAgentShooting(5);
		freeAgent1.setFreeAgentSkating(5);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition("forward");
		freeAgent2.setFreeAgentSaving(2);
		freeAgent2.setFreeAgentChecking(2);
		freeAgent2.setFreeAgentShooting(2);
		freeAgent2.setFreeAgentSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IFreeAgent> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, freeAgentsCount, true);

		Assert.assertTrue("Free Agents should be sorted in ascending order",
				freeAgents.get(0).getFreeAgentStrength() <= freeAgents.get(1).getFreeAgentStrength());
	}

	@Test
	public void sortFreeAgentsOnStrengthInDescendingOrderTest() {

		Trading trading = new Trading();
		ArrayList<IFreeAgent> freeAgentsToBeSorted = new ArrayList<>();
		int freeAgentsCount = 2;

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition("forward");
		freeAgent1.setFreeAgentSaving(5);
		freeAgent1.setFreeAgentChecking(5);
		freeAgent1.setFreeAgentShooting(5);
		freeAgent1.setFreeAgentSkating(5);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition("forward");
		freeAgent2.setFreeAgentSaving(2);
		freeAgent2.setFreeAgentChecking(2);
		freeAgent2.setFreeAgentShooting(2);
		freeAgent2.setFreeAgentSkating(2);

		freeAgentsToBeSorted.add(freeAgent1);
		freeAgentsToBeSorted.add(freeAgent2);

		ArrayList<IFreeAgent> freeAgents = trading.sortFreeAgentsOnStrength(freeAgentsToBeSorted, freeAgentsCount, false);

		Assert.assertTrue("Free Agents should be sorted in ascending order",
				freeAgents.get(0).getFreeAgentStrength() >= freeAgents.get(1).getFreeAgentStrength());
	}

	@Test
	public void getFreeAgentsWithPositionTest() {

		Trading trading = new Trading();
		ArrayList<IFreeAgent> freeAgents = new ArrayList<>();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Rob");
		freeAgent1.setFreeAgentPosition("goalie");

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Hawkey");
		freeAgent2.setFreeAgentPosition("defense");

		IFreeAgent freeAgent3 = new FreeAgent();
		freeAgent3.setFreeAgentName("Harpreet");
		freeAgent3.setFreeAgentPosition("forward");

		freeAgents.add(freeAgent1);
		freeAgents.add(freeAgent2);
		freeAgents.add(freeAgent3);

		ArrayList<IFreeAgent> freeAgentsWithPosition = trading.getFreeAgentsWithPosition(freeAgents, "goalie");

		Assert.assertEquals(freeAgent1, freeAgentsWithPosition.get(0));
	}
}
