package g4dhl;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void setPlayerWithEmptyPlayerNameTest() {
		Player player = new Player();
		Assert.assertFalse("Player name cannot be empty", player.setPlayerName(""));
	}

	@Test
	public void setPlayerWithEmptyPlayerPositionTest() {
		Player player = new Player();
		Assert.assertFalse("Player position cannot be empty", player.setPlayerPosition(""));
	}

	@Test
	public void setPlayerWithNullPlayerNameTest() {
		Player player = new Player();
		Assert.assertFalse("Player name cannot be null", player.setPlayerName(null));
	}

	@Test
	public void setPlayerWithNullPlayerPositionTest() {
		Player player = new Player();
		Assert.assertFalse("Player position cannot be null", player.setPlayerPosition(null));
	}

	@Test
	public void isPlayerCaptainTest() {
		Player player = new Player();
		player.setPlayerName("Player One");
		player.setPlayerPosition("forward");
		player.setPlayerCaptain(true);
		Assert.assertTrue("Player should be a captain", player.isPlayerCaptain());
	}

	@Test
	public void setPlayerCaptainTrueTest() {
		Player player = new Player();
		Assert.assertTrue("Player should be captain", player.setPlayerCaptain(true));
	}

	@Test
	public void setPlayerCaptainFalseTest() {
		Player player = new Player();
		Assert.assertTrue("Player should not be a captain failed!", player.setPlayerCaptain(false));
	}

	@Test
	public void setPlayerAgeTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerAge(33));
	}

	@Test
	public void setPlayerSkatingTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerSkating(10));
	}

	@Test
	public void setPlayerShootingTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerShooting(12));
	}

	@Test
	public void setPlayerCheckingTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerChecking(15));
	}

	@Test
	public void setPlayerSavingTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerSaving(1));
	}

	@Test
	public void getForawardPlayerStrengthTest() {
		Player mockPlayer = mock(Player.class);
		mockPlayer.setPlayerPosition("forward");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerChecking(14);
		mockPlayer.setPlayerShooting(15);
		double playerStrength = mockPlayer.getPlayerSkating() + mockPlayer.getPlayerShooting()
				+ (mockPlayer.getPlayerChecking() / 2);
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
		// Forward injured player
		mockPlayer.setPlayerIsInjured(true);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getDefensePlayerStrengthTest() {
		Player player = new Player();
		player.setPlayerPosition("defense");
		player.setPlayerSkating(16);
		player.setPlayerChecking(14);
		player.setPlayerSaving(18);
		double playerStrength = player.getPlayerSkating() + player.getPlayerChecking()
				+ (player.getPlayerShooting() / 2);
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
		// Defense injured player
		player.setPlayerIsInjured(true);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getGoaliePlayerStrengthTest() {
		Player player = new Player();
		player.setPlayerPosition("goalie");
		player.setPlayerSkating(16);
		player.setPlayerSaving(19);
		double playerStrength = player.getPlayerSaving() + player.getPlayerSkating();
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
		// Goalie injured player
		player.setPlayerIsInjured(true);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

}
