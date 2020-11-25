package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ImportJson;

public class LeagueMock {

    ClassLoader classLoader = getClass().getClassLoader();
    String mockReaderFile = classLoader.getResource("MockJsonM3.json").getPath();
    ImportJson importer = new ImportJson();
    ILeague league = importer.parseJson(mockReaderFile);
}
