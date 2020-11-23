package com.persistence;

import com.datamodel.leaguedatamodel.ILeague;
import com.persistencemodel.ILeagueDB;
import com.persistencemodel.IPersistenceFactory;
import com.persistencemodel.PersistenceFactory;
import org.junit.Assert;
import org.junit.Test;

public class PersistenceFactoryTest {

    @Test
    public void checkLeagueDBInstantiation() {
        IPersistenceFactory dbFactory1 = PersistenceFactory.instance();
        ILeagueDB leagueDB1 = dbFactory1.getLeagueDB();
        IPersistenceFactory dbFactory2 = PersistenceFactory.instance();
        ILeagueDB leagueDB2 = dbFactory2.getLeagueDB();
        Assert.assertSame(leagueDB1, leagueDB2);
    }
}
