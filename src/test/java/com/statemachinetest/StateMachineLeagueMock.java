package com.statemachinetest;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ImportJson;

public class StateMachineLeagueMock {

	static ILeague league;

	public static ILeague getLeague() {
		ClassLoader classLoader = StateMachineLeagueMock.class.getClassLoader();
		String mockReaderFile = classLoader.getResource("MockJsonM3Test.json").getPath();
		ImportJson importer = new ImportJson();
		ILeague league = importer.parseJson(mockReaderFile);
		return league;
	}
}
