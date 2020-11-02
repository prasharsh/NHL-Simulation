package com.datamodeltest.gameplayconfigtest;

import org.junit.*;

import com.datamodel.gameplayconfig.TrainingConfig;

public class TrainingConfigTest {

	@Test
	public void setDaysUntilStatIncreaseTest() {
		TrainingConfig training = new TrainingConfig();
		Assert.assertFalse("DaysUntilStatIncrease cannot be negative", training.setDaysUntilStatIncreaseCheck(-1));
	}

	@Test
	public void setValidDaysUntilStatIncreaseTest() {
		TrainingConfig training = new TrainingConfig();
		Assert.assertTrue("DaysUntilStatIncrease is saved", training.setDaysUntilStatIncreaseCheck(50));
	}

	@Test
	public void getDaysUntilStatIncreaseCheckTest() {
		TrainingConfig training = new TrainingConfig();
		training.setDaysUntilStatIncreaseCheck(33);
		Assert.assertEquals(33, training.getDaysUntilStatIncreaseCheck());
	}

	@Test
	public void setNoOfDaysTrainedTest() {
		TrainingConfig training = new TrainingConfig();
		Assert.assertFalse("NoOfDaysTrained cannot be negative", training.setNoOfDaysTrained(-1));
	}

	@Test
	public void setValidNoOfDaysTrainedTest() {
		TrainingConfig training = new TrainingConfig();
		Assert.assertTrue("NoOfDaysTrained is updates", training.setNoOfDaysTrained(50));
	}

	@Test
	public void getNoOfDaysTrainedTest() {
		TrainingConfig training = new TrainingConfig();
		training.setNoOfDaysTrained(14);
		Assert.assertEquals(14, training.getNoOfDaysTrained());
	}

	@Test
	public void getTrainingIdTest() {
		TrainingConfig training = new TrainingConfig();
		training.setTrainingId(10);
		Assert.assertEquals(10, training.getTrainingId());
	}
}