package com.persistencemodel;

public abstract class PersistenceAbstractFactory {
    private static PersistenceAbstractFactory uniqueInstance = null;

    public static PersistenceAbstractFactory instance() {
        return uniqueInstance;
    }

    public static void setFactory(PersistenceAbstractFactory persistenceFactory) {
        uniqueInstance = persistenceFactory;
    }

    public abstract ILeagueDB getLeagueDB();
}
