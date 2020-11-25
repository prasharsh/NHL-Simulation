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
    int MAX_STAT = 20;

//    public ITeam getStrongestTeam(){
//        ITeam strongestTeam = league.getAllTeams().get(0);
//        for (IPlayer player: strongestTeam.getPlayers()){
//            player.setPlayerSkating(MAX_STAT);
//            player.setPlayerShooting(MAX_STAT);
//            player.setPlayerChecking(MAX_STAT);
//            player.setPlayerSaving(MAX_STAT);
//        }
//        return strongestTeam;
//    }
}
