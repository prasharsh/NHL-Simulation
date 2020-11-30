package com.statemachinetest;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ImportJson;

public class StateMachineLeagueMock {

	ClassLoader classLoader = getClass().getClassLoader();
	String mockReaderFile = classLoader.getResource("MockJsonM3Test.json").getPath();
	ImportJson importer = new ImportJson();
	ILeague league = importer.parseJson(mockReaderFile);
}
