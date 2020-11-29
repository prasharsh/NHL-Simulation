package com.datamodeltest.gameplayconfigtest;
import java.sql.Date;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.datamodel.gameplayconfig.InjuryConfig;
public class InjuryConfigTest {

	private GamePlayConfigAbstractFactory gamePlayConfigAbstractFactory = GamePlayConfigAbstractFactory.instance();
	private IInjuryConfig injury = gamePlayConfigAbstractFactory.createInjuryConfig();

	@BeforeClass
	public static void createAgingConfig(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactoryTest());
	}

	@Test
	public void setNegativeInjuryChanceTest() {
		Assert.assertFalse("Injury chance chance cannot be negative", injury.setRandomInjuryChance(-1));
	}

	@Test
	public void setInValidInjuryChanceTest() {
		Assert.assertFalse("Injury chance chance cannot be greater than 1", injury.setRandomInjuryChance(2));
	}

	@Test
	public void setValidInjuryChanceTest() {
		Assert.assertTrue("Injury chance chance is updated", injury.setRandomInjuryChance((float) 0.5));
	}

	@Test
	public void getInjuryChanceTest() {
		float chance = (float) 0.5;
		injury.setRandomInjuryChance(chance);
		Assert.assertEquals(injury.getRandomInjuryChance(), (float) 0.5, 0.0);
	}

	@Test
	public void getInjuryIdTest() {
		injury.setInjuryId(10);
		Assert.assertEquals(10, injury.getInjuryId());
	}

	@Test
	public void setNegativeInjuryDaysLowTest() {
		Assert.assertFalse("InjuryDaysLow cannot be negative", injury.setInjuryDaysLow(-1));
	}

	@Test
	public void setValidInjuryDaysLowTest() {
		Assert.assertTrue("InjuryDaysLow is saved", injury.setInjuryDaysLow(50));
	}

	@Test
	public void getInjuryDaysLowTest() {
		injury.setInjuryDaysLow(30);
		Assert.assertEquals(30, injury.getInjuryDaysLow());
	}

	@Test
	public void setNegativeInjuryDaysHighTest() {
		Assert.assertFalse("InjuryDaysHigh cannot be negative", injury.setInjuryDaysHigh(-1));
	}

	@Test
	public void setValidInjuryDaysHighTest() {
		Assert.assertTrue("InjuryDaysHigh is saved", injury.setInjuryDaysHigh(50));
	}

	@Test
	public void getInjuryDaysHighTest() {
		injury.setInjuryDaysHigh(30);
		Assert.assertEquals(30, injury.getInjuryDaysHigh());
	}

	@Test
	public void getRecoveryDateTest() {
		Date currentDate = Date.valueOf("2020-10-01");
		Assert.assertNotNull(injury.getRecoveryDate(currentDate));
	}
}