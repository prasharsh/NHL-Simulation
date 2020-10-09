package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class HeadCoachTest {

	@Test
	public void setHeadCoachNameTest() {
		HeadCoach headCoach = new HeadCoach();
		headCoach.setHeadCoachName("Mary Smith");
		Assert.assertEquals("Mary Smith", headCoach.getHeadCoachName());
	}
}
