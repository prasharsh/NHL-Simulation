package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.Team;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class FreeAgentTest {

	@Test
	public void setFreeAgentWithEmptyFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be empty",freeAgent.setPlayerName(""));
	}

	@Test
	public void setFreeAgentWithEmptyFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be empty",freeAgent.setPlayerPosition(""));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be null",freeAgent.setPlayerName(null));
	}

	@Test
	public void setFreeAgentCaptainTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerCaptain(true));
	}

	@Test
	public void setFreeAgentIdTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerId(10));
	}

	@Test
	public void getMaxPlayerStatValueTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertEquals(20,freeAgent.getMaxPlayerStatValue());
	}


	@Test
	public void setFreeAgentWithNullFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be null",freeAgent.setPlayerPosition(null));
	}

	@Test
	public void setFreeAgentAgeYearTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerAgeYear(33));
	}

	@Test
	public void setFreeAgentIsInjuredTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerIsInjured(true));
	}

	@Test
	public void setFreeAgentWasInjuredTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerWasInjured(true));
	}

	@Test
	public void getFreeAgentBirthDateTest() {
		FreeAgent freeAgent = new FreeAgent();
		String date = "1996-11-21";
		freeAgent.setPlayerBirthDay((Integer.parseInt(date.split("-")[2])));
		freeAgent.setPlayerBirthMonth((Integer.parseInt(date.split("-")[1])));
		freeAgent.setPlayerBirthYear((Integer.parseInt(date.split("-")[0])));
		Assert.assertEquals(Date.valueOf(date),freeAgent.getPlayerBirthDate());
	}

	@Test
	public void isFreeAgentCaptainTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		mockFreeAgent.setPlayerCaptain(true);
		Assert.assertTrue(mockFreeAgent.isPlayerCaptain());
	}

	@Test
	public void getRosterStatusTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		mockFreeAgent.setRosterStatus(true);
		Assert.assertTrue(mockFreeAgent.getRosterStatus());
	}


	@Test
	public void setFreeAgentAgeDaysTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerAgeDays(278));
	}

	@Test
	public void setFreeAgentSkatingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerSkating(10));
	}

	@Test
	public void setFreeAgentShootingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerShooting(12));
	}

	@Test
	public void setFreeAgentCheckingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerChecking(15));
	}

	@Test
	public void setFreeAgentSavingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerSaving(1));
	}

	@Test
	public void ageFreeAgentTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		mockFreeAgent.setPlayerAgeDays(200);
		mockFreeAgent.setPlayerAgeYear(30);
		mockFreeAgent.agePlayer(50);
		Assert.assertEquals(30,mockFreeAgent.getPlayerAgeYear());
		Assert.assertEquals(250,mockFreeAgent.getPlayerAgeDays());
		mockFreeAgent.setPlayerAgeDays(200);
		mockFreeAgent.setPlayerAgeYear(30);
		mockFreeAgent.agePlayer(200);
		Assert.assertEquals(31,mockFreeAgent.getPlayerAgeYear());
		Assert.assertEquals(35,mockFreeAgent.getPlayerAgeDays());
	}

	@Test
	public void checkFreeAgentInjuryForInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		FreeAgent freeAgent = new FreeAgent();
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerIsInjured(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance,recoveryDate,currentDate,team));
	}

	@Test
	public void checkFreeAgentInjuryAlreadyInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		FreeAgent freeAgent = new FreeAgent();
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerWasInjured(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance,recoveryDate,currentDate,team));
	}

	@Test
	public void checkFreeAgentInjuryRetiredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		FreeAgent freeAgent = new FreeAgent();
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerRetired(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance,recoveryDate,currentDate,team));
	}

	@Test
	public void checkFreeAgentInjuryWhenRandomChanceHighTest() {
		float randomInjuryChance = 1.0f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		FreeAgent freeAgent = new FreeAgent();
		freeAgent.setPlayerName("Dhoni");
		team.addPlayer(freeAgent);
		Assert.assertTrue(freeAgent.checkPlayerInjury(randomInjuryChance,recoveryDate,currentDate,team));
	}

	@Test
	public void checkFreeAgentInjuryWhenRandomChanceLowTest() {
		float randomInjuryChance = 0.001f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		Team team = new Team();
		team.setTeamName("CSK");
		FreeAgent freeAgent = new FreeAgent();
		freeAgent.setPlayerName("Dhoni");
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance,recoveryDate,currentDate,team));
	}

	@Test
	public void isFreeAgentBirthDayTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		mockFreeAgent.setPlayerBirthYear(1996);
		mockFreeAgent.setPlayerBirthMonth(11);
		mockFreeAgent.setPlayerBirthDay(21);
		Assert.assertTrue(mockFreeAgent.isPlayerBirthDay(11,21));
	}

	@Test
	public void decreaseFreeAgentStatTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		mockFreeAgent.setPlayerChecking(12);
		mockFreeAgent.setPlayerSaving(15);
		mockFreeAgent.setPlayerShooting(12);
		mockFreeAgent.setPlayerSkating(17);
		mockFreeAgent.decreasePlayerStat(1);
		Assert.assertEquals(16,mockFreeAgent.getPlayerSkating());
		Assert.assertEquals(11,mockFreeAgent.getPlayerChecking());
		Assert.assertEquals(11,mockFreeAgent.getPlayerShooting());
		Assert.assertEquals(14,mockFreeAgent.getPlayerSaving());
	}
}