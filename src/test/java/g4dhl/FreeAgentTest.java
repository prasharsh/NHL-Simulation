package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class FreeAgentTest {

	@Test
	public void setFreeAgentWithEmptyFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be empty", freeAgent.setFreeAgentName(""));
	}

	@Test
	public void setFreeAgentWithEmptyFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be empty", freeAgent.setFreeAgentPosition(""));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentNameTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent name cannot be null", freeAgent.setFreeAgentName(null));
	}

	@Test
	public void setFreeAgentWithNullFreeAgentPositionTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertFalse("Free Agent position cannot be null", freeAgent.setFreeAgentPosition(null));
	}

	@Test
	public void setFreeAgentAgeYearTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentAgeYear(33));
	}

	@Test
	public void setFreeAgentAgeDaysTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentAgeDays(278));
	}

	@Test
	public void setFreeAgentSkatingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentSkating(10));
	}

	@Test
	public void setFreeAgentShootingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentShooting(12));
	}

	@Test
	public void setFreeAgentCheckingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentChecking(15));
	}

	@Test
	public void setFreeAgentSavingTest() {
		FreeAgent freeAgent = new FreeAgent();
		Assert.assertTrue(freeAgent.setFreeAgentSaving(1));
	}

	@Test
	public void ageFreeAgentTest() {
		FreeAgent mockFreeAgent = new FreeAgent();
		// when agedays + days < 365
		mockFreeAgent.setFreeAgentAgeDays(200);
		mockFreeAgent.setFreeAgentAgeYear(30);
		mockFreeAgent.ageFreeAgent(50);
		Assert.assertEquals(30, mockFreeAgent.getFreeAgentAgeYear());
		Assert.assertEquals(250, mockFreeAgent.getFreeAgentAgeDays());
		// when agedays + days >= 365
		mockFreeAgent.setFreeAgentAgeDays(200);
		mockFreeAgent.setFreeAgentAgeYear(30);
		mockFreeAgent.ageFreeAgent(200);
		Assert.assertEquals(31, mockFreeAgent.getFreeAgentAgeYear());
		Assert.assertEquals(35, mockFreeAgent.getFreeAgentAgeDays());

	}
}
