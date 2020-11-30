package com.datamodeltest.gameplayconfigtest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.ITrainingConfig;
public class TrainingConfigTest {

	private GamePlayConfigAbstractFactory gamePlayConfigAbstractFactory = GamePlayConfigAbstractFactory.instance();
	private ITrainingConfig training = gamePlayConfigAbstractFactory.createTrainingConfig();

	@BeforeClass
	public static void createAgingConfig(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactoryTest());
	}

	@Test
	public void setDaysUntilStatIncreaseTest() {
		Assert.assertFalse("DaysUntilStatIncrease cannot be negative", training.setDaysUntilStatIncreaseCheck(-1));
	}

	@Test
	public void setValidDaysUntilStatIncreaseTest() {
		Assert.assertTrue("DaysUntilStatIncrease is saved", training.setDaysUntilStatIncreaseCheck(50));
	}

	@Test
	public void getDaysUntilStatIncreaseCheckTest() {
		training.setDaysUntilStatIncreaseCheck(33);
		Assert.assertEquals(33, training.getDaysUntilStatIncreaseCheck());
	}

	@Test
	public void setNoOfDaysTrainedTest() {
		Assert.assertFalse("NoOfDaysTrained cannot be negative", training.setNoOfDaysTrained(-1));
	}

	@Test
	public void setValidNoOfDaysTrainedTest() {
		Assert.assertTrue("NoOfDaysTrained is updates", training.setNoOfDaysTrained(50));
	}

	@Test
	public void getNoOfDaysTrainedTest() {
		training.setNoOfDaysTrained(14);
		Assert.assertEquals(14, training.getNoOfDaysTrained());
	}

	@Test
	public void getTrainingIdTest() {
		training.setTrainingId(10);
		Assert.assertEquals(10, training.getTrainingId());
	}
}