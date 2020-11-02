
package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.League;
import org.junit.Assert;
import org.junit.Test;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.Main;
import com.datamodeltest.leaguedatamodeltest.GameDBMock;

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