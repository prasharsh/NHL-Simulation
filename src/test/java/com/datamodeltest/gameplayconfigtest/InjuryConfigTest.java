package com.datamodeltest.gameplayconfigtest;

import com.datamodel.gameplayconfig.InjuryConfig;
import org.junit.Assert;
import org.junit.Test;

public class InjuryConfigTest {
	@Test
	public void setNegativeInjuryChanceTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertFalse("Injury chance chance cannot be negative", injury.setRandomInjuryChance(-1));
	}

	@Test
	public void setInValidInjuryChanceTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertFalse("Injury chance chance cannot be greater than 1", injury.setRandomInjuryChance(2));
	}

	@Test
	public void setValidInjuryChanceTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertTrue("Injury chance chance is updated", injury.setRandomInjuryChance((float) 0.5));
	}

	@Test
	public void getInjuryChanceTest() {
		InjuryConfig injury = new InjuryConfig();
		float chance = (float) 0.5;
		injury.setRandomInjuryChance(chance);
		Assert.assertTrue(injury.getRandomInjuryChance() == (float) 0.5);
	}

	@Test
	public void getInjuryIdTest() {
		InjuryConfig injury = new InjuryConfig();
		injury.setInjuryId(10);
		Assert.assertEquals(10, injury.getInjuryId());
	}

	@Test
	public void setNegativeInjuryDaysLowTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertFalse("InjuryDaysLow cannot be negative", injury.setInjuryDaysLow(-1));
	}

	@Test
	public void setValidInjuryDaysLowTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertTrue("InjuryDaysLow is saved", injury.setInjuryDaysLow(50));
	}

	@Test
	public void getInjuryDaysLowTest() {
		InjuryConfig injury = new InjuryConfig();
		injury.setInjuryDaysLow(30);
		Assert.assertEquals(30, injury.getInjuryDaysLow());
	}

	@Test
	public void setNegativeInjuryDaysHighTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertFalse("InjuryDaysHigh cannot be negative", injury.setInjuryDaysHigh(-1));
	}

	@Test
	public void setValidInjuryDaysHighTest() {
		InjuryConfig injury = new InjuryConfig();
		Assert.assertTrue("InjuryDaysHigh is saved", injury.setInjuryDaysHigh(50));
	}

	@Test
	public void getInjuryDaysHighTest() {
		InjuryConfig injury = new InjuryConfig();
		injury.setInjuryDaysHigh(30);
		Assert.assertEquals(30, injury.getInjuryDaysHigh());
	}

}
