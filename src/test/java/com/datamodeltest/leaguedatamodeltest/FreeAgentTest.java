package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

public class FreeAgentTest {

	private final LeagueDataModelAbstractFactory leagueDataModelAbstractFactory =
			LeagueDataModelAbstractFactory.instance();
	private final IPlayer freeAgent = leagueDataModelAbstractFactory.createFreeAgent();

	@BeforeClass
	public static void setFactory() {
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Test
	public void setFreeAgentWithEmptyFreeAgentNameTest() {
		Assert.assertFalse("Free Agent name cannot be empty", freeAgent.setPlayerName(""));
	}

	@Test
	public void setFreeAgentWithEmptyFreeAgentPositionTest() {
		Assert.assertFalse("Free Agent position cannot be empty", freeAgent.setPlayerPosition(""));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentNameTest() {
		Assert.assertFalse("Free Agent name cannot be null", freeAgent.setPlayerName(null));
	}

	@Test
	public void setFreeAgentCaptainTest() {
		Assert.assertTrue(freeAgent.setPlayerCaptain(true));
	}

	@Test
	public void setFreeAgentIdTest() {
		Assert.assertTrue(freeAgent.setPlayerId(10));
	}

	@Test
	public void getFreeAgentIdTest() {
		freeAgent.setPlayerId(10);
		Assert.assertEquals(10, freeAgent.getPlayerId());
	}

	@Test
	public void getMaxPlayerStatValueTest() {
		Assert.assertEquals(20, freeAgent.getMaxPlayerStatValue());
	}

	@Test
	public void setFreeAgentWithNullFreeAgentPositionTest() {
		Assert.assertFalse("Free Agent position cannot be null", freeAgent.setPlayerPosition(null));
	}

	@Test
	public void setFreeAgentAgeYearTest() {
		Assert.assertTrue(freeAgent.setPlayerAgeYear(33));
	}

	@Test
	public void setFreeAgentIsInjuredTest() {
		Assert.assertTrue(freeAgent.setPlayerIsInjured(true));
	}

	@Test
	public void setFreeAgentWasInjuredTest() {
		Assert.assertTrue(freeAgent.setPlayerWasInjured(true));
	}

	@Test
	public void getFreeAgentBirthDateTest() {
		String date = "1996-11-21";
		freeAgent.setPlayerBirthDay((Integer.parseInt(date.split("-")[2])));
		freeAgent.setPlayerBirthMonth((Integer.parseInt(date.split("-")[1])));
		freeAgent.setPlayerBirthYear((Integer.parseInt(date.split("-")[0])));
		Assert.assertEquals(Date.valueOf(date), freeAgent.getPlayerBirthDate());
	}

	@Test
	public void isFreeAgentCaptainTest() {
		freeAgent.setPlayerCaptain(true);
		Assert.assertTrue(freeAgent.isPlayerCaptain());
	}

	@Test
	public void getRosterStatusTest() {
		freeAgent.setRosterStatus(true);
		Assert.assertTrue(freeAgent.getRosterStatus());
	}

	@Test
	public void isNotInPlayingSixTest() {
		freeAgent.setNotInPlayingSix(true);
		Assert.assertTrue(freeAgent.isNotInPlayingSix());
	}

	@Test
	public void setFreeAgentAgeDaysTest() {
		Assert.assertTrue(freeAgent.setPlayerAgeDays(278));
	}

	@Test
	public void setFreeAgentSkatingTest() {
		Assert.assertTrue(freeAgent.setPlayerSkating(10));
	}

	@Test
	public void setFreeAgentShootingTest() {
		Assert.assertTrue(freeAgent.setPlayerShooting(12));
	}

	@Test
	public void setFreeAgentCheckingTest() {
		Assert.assertTrue(freeAgent.setPlayerChecking(15));
	}

	@Test
	public void setFreeAgentSavingTest() {
		Assert.assertTrue(freeAgent.setPlayerSaving(1));
	}

	@Test
	public void ageFreeAgentTest() {
		freeAgent.setPlayerAgeDays(200);
		freeAgent.setPlayerAgeYear(30);
		freeAgent.agePlayer(50);
		Assert.assertEquals(30, freeAgent.getPlayerAgeYear());
		Assert.assertEquals(250, freeAgent.getPlayerAgeDays());
		freeAgent.setPlayerAgeDays(200);
		freeAgent.setPlayerAgeYear(30);
		freeAgent.agePlayer(200);
		Assert.assertEquals(31, freeAgent.getPlayerAgeYear());
		Assert.assertEquals(35, freeAgent.getPlayerAgeDays());
	}

	@Test
	public void checkFreeAgentInjuryForInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("CSK");
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerIsInjured(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkFreeAgentInjuryAlreadyInjuredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("CSK");
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerWasInjured(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkFreeAgentInjuryRetiredPlayerTest() {
		float randomInjuryChance = 0.05f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("CSK");
		freeAgent.setPlayerName("Dhoni");
		freeAgent.setPlayerRetired(true);
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkFreeAgentInjuryWhenRandomChanceHighTest() {
		float randomInjuryChance = 1.0f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("CSK");
		freeAgent.setPlayerName("Dhoni");
		team.addPlayer(freeAgent);
		Assert.assertTrue(freeAgent.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void checkFreeAgentInjuryWhenRandomChanceLowTest() {
		float randomInjuryChance = 0.001f;
		Date recoveryDate = Date.valueOf("2020-11-31");
		Date currentDate = Date.valueOf("2020-11-01");
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("CSK");
		freeAgent.setPlayerName("Dhoni");
		team.addPlayer(freeAgent);
		Assert.assertFalse(freeAgent.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team));
	}

	@Test
	public void isFreeAgentBirthDayTest() {
		freeAgent.setPlayerBirthYear(1996);
		freeAgent.setPlayerBirthMonth(11);
		freeAgent.setPlayerBirthDay(21);
		Assert.assertTrue(freeAgent.isPlayerBirthDay(11, 21));
	}

	@Test
	public void decreaseFreeAgentStatTest() {
		freeAgent.setPlayerChecking(12);
		freeAgent.setPlayerSaving(15);
		freeAgent.setPlayerShooting(12);
		freeAgent.setPlayerSkating(17);
		freeAgent.decreasePlayerStat(1);
		Assert.assertEquals(16, freeAgent.getPlayerSkating());
		Assert.assertEquals(11, freeAgent.getPlayerChecking());
		Assert.assertEquals(11, freeAgent.getPlayerShooting());
		Assert.assertEquals(14, freeAgent.getPlayerSaving());
	}
}