package com.datamodeltest.gameplayconfigtest;

import org.junit.Assert;
import org.junit.Test;

import com.datamodel.gameplayconfig.AgingConfig;

public class AgingConfigTest {
	@Test
	public void setNegativeRetirementAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertFalse("Retirement age cannot be negative", agingConfig.setAverageRetirementAge(-1));
	}

	@Test
	public void setZeroRetirementAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertFalse("Retirement age cannot be zero", agingConfig.setAverageRetirementAge(0));
	}

	@Test
	public void setValidRetirementAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertTrue("Retirement age is saved", agingConfig.setAverageRetirementAge(50));
	}

	@Test
	public void getRetirementAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		agingConfig.setAverageRetirementAge(30);
		Assert.assertEquals(30, agingConfig.getAverageRetirementAge());
	}

	@Test
	public void getAgingIdTest() {
		AgingConfig agingConfig = new AgingConfig();
		agingConfig.setAgingId(30);
		Assert.assertEquals(30, agingConfig.getAgingId());
	}

	@Test
	public void setNegativeMaximumAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertFalse("Retirement age cannot be negative", agingConfig.setMaximumAge(-1));
	}

	@Test
	public void setZeroMaximumAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertFalse("Retirement age cannot be zero", agingConfig.setMaximumAge(0));
	}

	@Test
	public void setValidMaximumAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		Assert.assertTrue("Retirement age is saved", agingConfig.setMaximumAge(50));
	}

	@Test
	public void getMaximumAgeTest() {
		AgingConfig agingConfig = new AgingConfig();
		agingConfig.setMaximumAge(30);
		Assert.assertEquals(30, agingConfig.getMaximumAge());
	}

	@Test
	public void isPlayerRetiresTest() {
		AgingConfig agingConfig = new AgingConfig();
		agingConfig.setAverageRetirementAge(35);
		agingConfig.setMaximumAge(50);
		int playerAge = 51;
		Assert.assertTrue(agingConfig.isPlayerRetires(playerAge));
		playerAge = 30;
		Assert.assertFalse(agingConfig.isPlayerRetires(playerAge));
	}
}
