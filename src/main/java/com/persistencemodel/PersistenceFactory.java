package com.persistencemodel;

public class PersistenceFactory implements IPersistenceFactory {

    private static IPersistenceFactory persistenceInstance = null;
    private ILeagueDB leagueDB;

    private PersistenceFactory() {
        this.leagueDB = new LeagueDB();
    }

    public static IPersistenceFactory instance() {
        if (persistenceInstance == null) {
            persistenceInstance = new PersistenceFactory();
        }
        return persistenceInstance;
    }

    @Override
    public ILeagueDB getLeagueDB() {
        return this.leagueDB;
    }
}
