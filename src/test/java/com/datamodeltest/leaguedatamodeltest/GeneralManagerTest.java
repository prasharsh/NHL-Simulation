package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneralManagerTest {

	private final LeagueDataModelAbstractFactory leagueDataModelAbstractFactory =
			LeagueDataModelAbstractFactory.instance();
	private final IGeneralManager generalManager = leagueDataModelAbstractFactory.createGeneralManager();

	@BeforeClass
	public static void setFactory() {
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Test
	public void setGeneralManagerNameEmptyTest() {
		Assert.assertFalse("General Manager name cannot be empty", generalManager.setGeneralManagerName(""));
	}

	@Test
	public void setGeneralManagerNameNullTest() {
		Assert.assertFalse("General Manager name cannot be null", generalManager.setGeneralManagerName(null));
	}

	@Test
	public void setGeneralManagerIdTest() {
		Assert.assertTrue(generalManager.setGeneralManagerId(10));
	}

	@Test
	public void getGeneralManagerIdTest() {
		generalManager.setGeneralManagerId(10);
		Assert.assertEquals(10, generalManager.getGeneralManagerId());
	}

	@Test
	public void setGeneralManagerNameTest() {
		Assert.assertTrue(generalManager.setGeneralManagerName("Rob"));
	}

	@Test
	public void getGeneralManagerNameTest() {
		generalManager.setGeneralManagerName("Rob");
		Assert.assertEquals("Rob", generalManager.getGeneralManagerName());
	}

}