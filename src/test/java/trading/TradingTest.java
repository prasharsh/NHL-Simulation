package trading;

import java.util.ArrayList;

import g4dhl.ITeam;
import g4dhl.Team;
import org.junit.Assert;
import org.junit.Test;

import g4dhl.IPlayer;
import g4dhl.Player;

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

}
