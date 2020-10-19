package g4dhl;

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

}
