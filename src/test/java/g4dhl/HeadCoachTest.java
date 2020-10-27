package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class HeadCoachTest {

	@Test
	public void setHeadCoachNameEmptyTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertFalse("Head Coach cannot be empty", headCoach.setHeadCoachName(""));
	}

	@Test
	public void setHeadCoachNameNullTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertFalse("Head Coach cannot be null", headCoach.setHeadCoachName(null));
	}

	@Test
	public void setHeadCoachNameTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachName("Steve Roger"));
	}

	@Test
	public void setHeadCoachSkatingTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachSkating(0.5f));
	}

	@Test
	public void setHeadCoachShootingTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachShooting(0.4f));
	}

	@Test
	public void setHeadCoachCheckingTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachChecking(0.9f));
	}

	@Test
	public void setHeadCoachSavingTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachSaving(0.2f));
	}
}
