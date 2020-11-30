package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ImportJson;

public class LeagueMock {

	private final ILeague league;

	public LeagueMock() {
		ClassLoader classLoader = getClass().getClassLoader();
		String mockReaderFile = classLoader.getResource("MockJsonM3Test.json").getPath();
		ImportJson importer = new ImportJson();
		league = importer.parseJson(mockReaderFile);
	}

	public ILeague getLeague() {
		return league;
	}

}
