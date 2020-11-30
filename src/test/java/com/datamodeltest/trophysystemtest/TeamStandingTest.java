package com.datamodeltest.trophysystemtest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Team;
import com.datamodel.trophysystem.Observer;
import com.datamodel.trophysystem.Subject;
import com.datamodel.trophysystem.TeamStandingObserver;
import com.datamodel.trophysystem.TeamStandingSubject;

public class TeamStandingTest {

    private static final Subject teamStanding = TeamStandingSubject.instance();

    private static final Observer teamStandingObserver = new TeamStandingObserver();

    @BeforeClass
    public static void attachObservers() {
        teamStanding.attach(teamStandingObserver);
    }

    @Test
    public void getBestTeamTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        ITeam team3 = new Team();
        ITeam team4 = new Team();
        team1.setTeamName("team1");
        team2.setTeamName("team2");
        team3.setTeamName("team3");
        team4.setTeamName("team4");
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team2);
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        ITeam bestTeam = teamStandingPublisher.getBestTeam();
        Assert.assertSame(team3, bestTeam);
    }

    @Test
    public void getLeastTeamTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        ITeam team3 = new Team();
        ITeam team4 = new Team();
        team1.setTeamName("team1");
        team2.setTeamName("team2");
        team3.setTeamName("team3");
        team4.setTeamName("team4");
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        teamStandingPublisher.notifyTeamStandingPublisher(team2, team1);
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        ITeam leastTeam = teamStandingPublisher.getLeastTeam();
        Assert.assertSame(team1, leastTeam);
    }

    @Test
    public void setBestTeamTest() {
        ITeam team1 = new Team();
        team1.setTeamName("team1");
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
        teamStandingPublisher.setBestTeam(team1);
        ITeam bestTeam = teamStandingPublisher.getBestTeam();
        Assert.assertSame(team1, bestTeam);
    }

    @Test
    public void setLeastTeamTest() {
        ITeam team1 = new Team();
        team1.setTeamName("team1");
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
        teamStandingPublisher.setLeastTeam(team1);
        ITeam leastTeam = teamStandingPublisher.getLeastTeam();
        Assert.assertSame(team1, leastTeam);
    }

    @Test
    public void resetStandingTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        team1.setTeamName("team1");
        team2.setTeamName("team2");
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
        teamStandingPublisher.setBestTeam(team1);
        teamStandingPublisher.setLeastTeam(team1);
        teamStandingPublisher.resetTeamStandings();
        ITeam bestTeam = teamStandingPublisher.getBestTeam();
        ITeam leastTeam = teamStandingPublisher.getLeastTeam();
        Assert.assertNull(leastTeam);
        Assert.assertNull(bestTeam);
    }
}
