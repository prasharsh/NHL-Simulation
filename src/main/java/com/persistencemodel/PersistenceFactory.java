package com.persistencemodel;


public class PersistenceFactory extends PersistenceAbstractFactory {

    private ILeagueDB leagueDB;
    @Override
    public ILeagueDB getLeagueDB() {
        if (leagueDB == null) {
            leagueDB = new LeagueDB();
        }
        return leagueDB;
    }
}
