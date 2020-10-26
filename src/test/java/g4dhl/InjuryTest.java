package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class InjuryTest {
	@Test
	public void setNegativeInjuryChanceTest() {
		Injury injury = new Injury();
		Assert.assertFalse("Injury chance chance cannot be negative", injury.setRandomInjuryChance(-1));
	}

	@Test
	public void setInValidInjuryChanceTest() {
		Injury injury = new Injury();
		Assert.assertFalse("Injury chance chance cannot be greater than 1", injury.setRandomInjuryChance(2));
	}

	@Test
	public void setValidInjuryChanceTest() {
		Injury injury = new Injury();
		Assert.assertTrue("Injury chance chance is updated", injury.setRandomInjuryChance((float) 0.5));
	}

	@Test
	public void getInjuryChanceTest() {
		Injury injury = new Injury();
		float chance = (float) 0.5;
		injury.setRandomInjuryChance(chance);
		Assert.assertTrue(injury.getRandomInjuryChance() == (float) 0.5);
	}

	@Test
	public void getInjuryIdTest() {
		Injury injury = new Injury();
		injury.setInjuryId(10);
		Assert.assertEquals(10, injury.getInjuryId());
	}

	@Test
	public void setNegativeInjuryDaysLowTest() {
		Injury injury = new Injury();
		Assert.assertFalse("InjuryDaysLow cannot be negative", injury.setInjuryDaysLow(-1));
	}

	@Test
	public void setValidInjuryDaysLowTest() {
		Injury injury = new Injury();
		Assert.assertTrue("InjuryDaysLow is saved", injury.setInjuryDaysLow(50));
	}

	@Test
	public void getInjuryDaysLowTest() {
		Injury injury = new Injury();
		injury.setInjuryDaysLow(30);
		Assert.assertEquals(30, injury.getInjuryDaysLow());
	}

	@Test
	public void setNegativeInjuryDaysHighTest() {
		Injury injury = new Injury();
		Assert.assertFalse("InjuryDaysHigh cannot be negative", injury.setInjuryDaysHigh(-1));
	}

	@Test
	public void setValidInjuryDaysHighTest() {
		Injury injury = new Injury();
		Assert.assertTrue("InjuryDaysHigh is saved", injury.setInjuryDaysHigh(50));
	}

	@Test
	public void getInjuryDaysHighTest() {
		Injury injury = new Injury();
		injury.setInjuryDaysHigh(30);
		Assert.assertEquals(30, injury.getInjuryDaysHigh());
	}

}
