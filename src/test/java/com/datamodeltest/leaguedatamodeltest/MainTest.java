package com.datamodeltest.leaguedatamodeltest;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Main;

public class MainTest {

	@Test
	public void isNullOrEmptyTest() {
		String str = "";
		Assert.assertTrue(Main.isNullOrEmpty(str));
		str = "  ";
		Assert.assertTrue(Main.isNullOrEmpty(str));
		str = null;
		Assert.assertTrue(Main.isNullOrEmpty(str));
	}
}