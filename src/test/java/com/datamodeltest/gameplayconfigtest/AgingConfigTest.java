package com.datamodeltest.gameplayconfigtest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.IAgingConfig;

public class AgingConfigTest {

	private GamePlayConfigAbstractFactory gamePlayConfigAbstractFactory = GamePlayConfigAbstractFactory.instance();
	private IAgingConfig agingConfig = gamePlayConfigAbstractFactory.createAgingConfig();

	@BeforeClass
	public static void createAgingConfig(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactoryTest());
	}

	@Test
	public void setNegativeRetirementAgeTest() {
		Assert.assertFalse("Retirement age cannot be negative",agingConfig.setAverageRetirementAge(-1));
	}

	@Test
	public void setZeroRetirementAgeTest() {
		Assert.assertFalse("Retirement age cannot be zero", agingConfig.setAverageRetirementAge(0));
	}

	@Test
	public void setValidRetirementAgeTest() {
		Assert.assertTrue("Retirement age is saved", agingConfig.setAverageRetirementAge(50));
	}

	@Test
	public void getRetirementAgeTest() {
		agingConfig.setAverageRetirementAge(30);
		Assert.assertEquals(30, agingConfig.getAverageRetirementAge());
	}

	@Test
	public void getAgingIdTest() {
		agingConfig.setAgingId(30);
		Assert.assertEquals(30, agingConfig.getAgingId());
	}

	@Test
	public void setNegativeMaximumAgeTest() {
		Assert.assertFalse("Retirement age cannot be negative", agingConfig.setMaximumAge(-1));
	}

	@Test
	public void setZeroMaximumAgeTest() {
		Assert.assertFalse("Retirement age cannot be zero", agingConfig.setMaximumAge(0));
	}

	@Test
	public void setValidMaximumAgeTest() {
		Assert.assertTrue("Retirement age is saved", agingConfig.setMaximumAge(50));
	}

	@Test
	public void getMaximumAgeTest() {
		agingConfig.setMaximumAge(30);
		Assert.assertEquals(30, agingConfig.getMaximumAge());
	}

	@Test
	public void isPlayerRetiresTest() {
		agingConfig.setAverageRetirementAge(35);
		agingConfig.setMaximumAge(50);
		int playerAge = 51;
		Assert.assertTrue(agingConfig.isPlayerRetires(playerAge));
		playerAge = 30;
		Assert.assertFalse(agingConfig.isPlayerRetires(playerAge));
	}

	@Test
	public void getStatDecayChanceTest() {
		agingConfig.setStatDecayChance(0.05f);
		Assert.assertEquals(0.05f,agingConfig.getStatDecayChance(),0.0);
	}

	@Test
	public void isStatDecayOnBirthDayTest() {
		agingConfig.setStatDecayChance(1f);
		Assert.assertTrue(agingConfig.isStatDecayOnBirthDay());
		agingConfig.setStatDecayChance(0.001f);
		Assert.assertFalse(agingConfig.isStatDecayOnBirthDay());
	}
}