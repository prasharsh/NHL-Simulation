package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.Team;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import com.inputoutputmodel.InputOutputModelFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

public class PlayerTest {

	private final LeagueDataModelAbstractFactory leagueDataModelAbstractFactory =
			LeagueDataModelAbstractFactory.instance();
	private final IPlayer player = leagueDataModelAbstractFactory.createPlayer();

	@BeforeClass
	public static void createConference() {
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		InputOutputModelAbstractFactory.setFactory(new InputOutputModelFactory());
	}

	@Test
	public void setPlayerIdTest() {
		Assert.assertTrue(player.setPlayerId(10));
	}

	@Test
	public void getPlayerIdTest() {
		player.setPlayerId(10);
		Assert.assertEquals(10, player.getPlayerId());
	}

	@Test
	public void setPlayerWithEmptyPlayerNameTest() {
		Assert.assertFalse("Player name cannot be empty", player.setPlayerName(""));
	}

	@Test
	public void setPlayerWithEmptyPlayerPositionTest() {
		Assert.assertFalse("Player position cannot be empty", player.setPlayerPosition(""));
	}

	@Test
	public void setPlayerWithNullPlayerNameTest() {
		Assert.assertFalse("Player name cannot be null", player.setPlayerName(null));
	}

	@Test
	public void setPlayerWithNullPlayerPositionTest() {
		Assert.assertFalse("Player position cannot be null", player.setPlayerPosition(null));
	}

	@Test
	public void isPlayerCaptainTest() {
		player.setPlayerName("Player One");
		player.setPlayerPosition("forward");
		player.setPlayerCaptain(true);
		Assert.assertTrue("Player should be a captain", player.isPlayerCaptain());
	}

	@Test
	public void getRosterStatusTest() {
		player.setRosterStatus(true);
		Assert.assertTrue(player.getRosterStatus());
	}

	@Test
	public void setPlayerCaptainTrueTest() {
		Assert.assertTrue("Player should be captain", player.setPlayerCaptain(true));
	}

	@Test
	public void setPlayerCaptainFalseTest() {
		Assert.assertTrue("Player should not be a captain failed!", player.setPlayerCaptain(false));
	}

	@Test
	public void setPlayerAgeYearTest() {
		Assert.assertTrue(player.setPlayerAgeYear(33));
	}

	@Test
	public void setPlayerAgeDaysTest() {
		Assert.assertTrue(player.setPlayerAgeDays(296));
	}

	@Test
	public void setPlayerSkatingTest() {
		Assert.assertTrue(player.setPlayerSkating(10));
	}

	@Test
	public void setPlayerShootingTest() {
		Assert.assertTrue(player.setPlayerShooting(12));
	}

	@Test
	public void setPlayerCheckingTest() {
		Assert.assertTrue(player.setPlayerChecking(15));
	}

	@Test
	public void setPlayerSavingTest() {
		Assert.assertTrue(player.setPlayerSaving(1));
	}

	@Test
	public void getForwardPlayerStrengthTest() {
		player.setPlayerPosition("forward");
		player.setPlayerSkating(16);
		player.setPlayerChecking(14);
		player.setPlayerShooting(15);
		double playerStrength =
				player.getPlayerSkating() + player.getPlayerShooting() + (player.getPlayerChecking() / 2.0);
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
		player.setPlayerIsInjured(true);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getForwardPlayerStrengthWhenInjuredTest() {
		player.setPlayerPosition("forward");
		player.setPlayerSkating(16);
		player.setPlayerChecking(14);
		player.setPlayerShooting(15);
		player.setPlayerIsInjured(true);
		double playerStrength =
				player.getPlayerSkating() + player.getPlayerShooting() + (player.getPlayerChecking() / 2.0);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getDefensePlayerStrengthTest() {
		player.setPlayerPosition("defense");
		player.setPlayerSkating(16);
		player.setPlayerChecking(14);
		player.setPlayerSaving(18);
		double playerStrength =
				player.getPlayerSkating() + player.getPlayerChecking() + (player.getPlayerShooting() / 2.0);
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getDefensePlayerStrengthWhenInjuredTest() {
		player.setPlayerPosition("defense");
		player.setPlayerSkating(16);
		player.setPlayerChecking(14);
		player.setPlayerSaving(18);
		player.setPlayerIsInjured(true);
		double playerStrength =
				player.getPlayerSkating() + player.getPlayerChecking() + (player.getPlayerShooting() / 2.0);
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getGoaliePlayerStrengthTest() {
		player.setPlayerPosition("goalie");
		player.setPlayerSkating(16);
		player.setPlayerSaving(19);
		double playerStrength = player.getPlayerSaving() + player.getPlayerSkating();
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void getGoaliePlayerStrengthWhenInjuredTest() {
		player.setPlayerPosition("goalie");
		player.setPlayerSkating(16);
		player.setPlayerSaving(19);
		player.setPlayerIsInjured(true);
		double playerStrength = player.getPlayerSaving() + player.getPlayerSkating();
		playerStrength = playerStrength / 2;
		Assert.assertEquals(playerStrength, player.getPlayerStrength(), 0.0);
	}

	@Test
	public void agePlayerTest() {
		player.setPlayerAgeDays(200);
		player.setPlayerAgeYear(30);
		player.agePlayer(50);
		Assert.assertEquals(30, player.getPlayerAgeYear());
		Assert.assertEquals(250, player.getPlayerAgeDays());
		player.setPlayerAgeDays(200);
		player.setPlayerAgeYear(30);
		player.agePlayer(200);
		Assert.assertEquals(31, player.getPlayerAgeYear());
		Assert.assertEquals(35, player.getPlayerAgeDays());
	}

	@Test
	public void getMaxPlayerStatValueTest() {
		int maxStatValue = player.getMaxPlayerStatValue();
		Assert.assertEquals(20, maxStatValue);
	}

	@Test
	public void isRecoveryDateNotNullTest() {
		Date recoveryDate = Date.valueOf("2020-10-09");
		Assert.assertTrue(player.isRecoveryDateIsNotNull(recoveryDate));
	}

	@Test
	public void isRecoveryDateNullTest() {
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
		player.setPlayerName("Dhoni");
		team.addPlayer(player);
		Assert.assertFalse(player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void isPlayerBirthDayTest() {
		player.setPlayerBirthYear(1996);
		player.setPlayerBirthMonth(11);
		player.setPlayerBirthDay(21);
		Assert.assertTrue(player.isPlayerBirthDay(11, 21));
	}

	@Test
	public void decreaseFreeAgentStatTest() {
		player.setPlayerChecking(12);
		player.setPlayerSaving(15);
		player.setPlayerShooting(12);
		player.setPlayerSkating(17);
		player.decreasePlayerStat(1);
		Assert.assertEquals(16, player.getPlayerSkating());
		Assert.assertEquals(11, player.getPlayerChecking());
		Assert.assertEquals(11, player.getPlayerShooting());
		Assert.assertEquals(14, player.getPlayerSaving());
	}

	@Test
	public void getPlayerBirthDateTest() {
		String date = "1996-11-21";
		player.setPlayerBirthDay((Integer.parseInt(date.split("-")[2])));
		player.setPlayerBirthMonth((Integer.parseInt(date.split("-")[1])));
		player.setPlayerBirthYear((Integer.parseInt(date.split("-")[0])));
		Assert.assertEquals(Date.valueOf(date), player.getPlayerBirthDate());
	}

}