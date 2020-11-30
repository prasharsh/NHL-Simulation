package com.datamodeltest.leaguedatamodeltest;

import com.app.main.Main;
import org.junit.Assert;
import org.junit.Test;

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