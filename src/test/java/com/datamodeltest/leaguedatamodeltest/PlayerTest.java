package com.datamodeltest.leaguedatamodeltest;
import java.sql.Date;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.leaguedatamodel.Team;

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
	public void setPlayerAgeYearTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerAgeYear(33));
	}

	@Test
	public void setPlayerAgeDaysTest() {
		Player player = new Player();
		Assert.assertTrue(player.setPlayerAgeDays(296));
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
	public void getForwardPlayerStrengthTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("forward");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerChecking(14);
		mockPlayer.setPlayerShooting(15);
		double playerStrength = mockPlayer.getPlayerSkating() + mockPlayer.getPlayerShooting()
				+ (mockPlayer.getPlayerChecking() / 2.0);
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
		mockPlayer.setPlayerIsInjured(true);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getForwardPlayerStrengthWhenInjuredTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("forward");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerChecking(14);
		mockPlayer.setPlayerShooting(15);
		mockPlayer.setPlayerIsInjured(true);
		double playerStrength = mockPlayer.getPlayerSkating() + mockPlayer.getPlayerShooting()
				+ (mockPlayer.getPlayerChecking() / 2.0);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getDefensePlayerStrengthTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("defense");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerChecking(14);
		mockPlayer.setPlayerSaving(18);
		double playerStrength = mockPlayer.getPlayerSkating() + mockPlayer.getPlayerChecking()
				+ (mockPlayer.getPlayerShooting() / 2.0);
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getDefensePlayerStrengthWhenInjuredTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("defense");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerChecking(14);
		mockPlayer.setPlayerSaving(18);
		mockPlayer.setPlayerIsInjured(true);
		double playerStrength = mockPlayer.getPlayerSkating() + mockPlayer.getPlayerChecking()
				+ (mockPlayer.getPlayerShooting() / 2.0);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getGoaliePlayerStrengthTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("goalie");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerSaving(19);
		double playerStrength = mockPlayer.getPlayerSaving() + mockPlayer.getPlayerSkating();
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void getGoaliePlayerStrengthWhenInjuredTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerPosition("goalie");
		mockPlayer.setPlayerSkating(16);
		mockPlayer.setPlayerSaving(19);
		mockPlayer.setPlayerIsInjured(true);
		double playerStrength = mockPlayer.getPlayerSaving() + mockPlayer.getPlayerSkating();
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, mockPlayer.getPlayerStrength(), 0.0);
	}

	@Test
	public void agePlayerTest() {
		Player mockPlayer = new Player();
		mockPlayer.setPlayerAgeDays(200);
		mockPlayer.setPlayerAgeYear(30);
		mockPlayer.agePlayer(50);
		Assert.assertEquals(30, mockPlayer.getPlayerAgeYear());
		Assert.assertEquals(250, mockPlayer.getPlayerAgeDays());
		mockPlayer.setPlayerAgeDays(200);
		mockPlayer.setPlayerAgeYear(30);
		mockPlayer.agePlayer(200);
		Assert.assertEquals(31, mockPlayer.getPlayerAgeYear());
		Assert.assertEquals(35, mockPlayer.getPlayerAgeDays());
	}

	@Test
	public void getMaxPlayerStatValueTest() {
		IPlayer player = new Player();
		int maxStatValue = player.getMaxPlayerStatValue();
		Assert.assertEquals(20, maxStatValue);
	}

	@Test
	public void isRecoveryDateNotNullTest() {
		Player player = new Player();
		Date recoveryDate = Date.valueOf("2020-10-09");
		Assert.assertTrue(player.isRecoveryDateIsNotNull(recoveryDate));
	}

	@Test
	public void isRecoveryDateNullTest() {
		Player player = new Player();
		Date recoveryDate = null;
		Assert.assertFalse(player.isRecoveryDateIsNotNull(recoveryDate));
	}

	@Test
	public void checkPlayerInjuryForInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		Player player = new Player();
		player.setPlayerName("Dhoni");
		player.setPlayerIsInjured(true);
		team.addPlayer(player);
		Assert.assertFalse(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkPlayerInjuryAlreadyInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		Player player = new Player();
		player.setPlayerName("Dhoni");
		player.setPlayerWasInjured(true);
		team.addPlayer(player);
		Assert.assertFalse(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkPlayerInjuryRetiredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		Player player = new Player();
		player.setPlayerName("Dhoni");
		player.setPlayerRetired(true);
		team.addPlayer(player);
		Assert.assertFalse(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkPlayerInjuryWhenRandomChanceHighTest() {
		float randomInjuryChance = 1.0f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		Player player = new Player();
		player.setPlayerName("Dhoni");
		team.addPlayer(player);
		Assert.assertTrue(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkPlayerInjuryWhenRandomChanceLowTest() {
		float randomInjuryChance = 0.001f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		Player player = new Player();
		player.setPlayerName("Dhoni");
		team.addPlayer(player);
		Assert.assertFalse(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}
}