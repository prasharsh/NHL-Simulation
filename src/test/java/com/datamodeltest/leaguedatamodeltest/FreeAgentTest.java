package com.datamodeltest.leaguedatamodeltest;

import com.datamodeltest.leaguedatamodel.FreeAgent;
import org.junit.Assert;
import org.junit.Test;

public class FreeAgentTest {

	@Test
	public void setFreeAgentWithEmptyFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be empty", freeAgent.setPlayerName(""));
	}

	@Test
	public void setFreeAgentWithEmptyFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be empty", freeAgent.setPlayerPosition(""));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be null", freeAgent.setPlayerName(null));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be null", freeAgent.setPlayerPosition(null));
	}

	@Test
	public void setFreeAgentAgeYearTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setPlayerAgeYear(33));
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
		// when agedays + days < 365
		mockFreeAgent.setPlayerAgeDays(200);
		mockFreeAgent.setPlayerAgeYear(30);
		mockFreeAgent.agePlayer(50);
		Assert.assertEquals(30, mockFreeAgent.getPlayerAgeYear());
		Assert.assertEquals(250, mockFreeAgent.getPlayerAgeDays());
		// when agedays + days >= 365
		mockFreeAgent.setPlayerAgeDays(200);
		mockFreeAgent.setPlayerAgeYear(30);
		mockFreeAgent.agePlayer(200);
		Assert.assertEquals(31, mockFreeAgent.getPlayerAgeYear());
		Assert.assertEquals(35, mockFreeAgent.getPlayerAgeDays());

	}
}
