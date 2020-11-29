package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.HeadCoach;
import org.junit.Assert;
import org.junit.Test;

public class HeadCoachTest {

	@Test
	public void setHeadCoachIdTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertTrue(headCoach.setHeadCoachId(10));
	}

	@Test
	public void getHeadCoachIdTest() {
		HeadCoach headCoach = new HeadCoach();
		headCoach.setHeadCoachId(10);
		Assert.assertEquals(10,headCoach.getHeadCoachId());
	}

	@Test
	public void getHeadCoachNameTest() {
		HeadCoach headCoach = new HeadCoach();
		headCoach.setHeadCoachName("Rob");
		Assert.assertEquals("Rob",headCoach.getHeadCoachName());
	}

	@Test
	public void setHeadCoachNameEmptyTest() {
		HeadCoach headCoach = new HeadCoach();
		Assert.assertFalse("Head Coach cannot be empty",headCoach.setHeadCoachName(""));
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