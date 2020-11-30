package com.datamodeltest.trophysystemtest;

import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.trophysystem.CoachStandingObserver;
import com.datamodel.trophysystem.CoachStandingSubject;
import com.datamodel.trophysystem.Observer;
import com.datamodel.trophysystem.Subject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachStandingTest {

	private static final Subject coachStanding = CoachStandingSubject.instance();

	private static final Observer coachStandingObserver = new CoachStandingObserver();

	@BeforeClass
	public static void attachObservers() {
		coachStanding.attach(coachStandingObserver);
	}

	@Test
	public void setBestCoachTest() {
		IHeadCoach coach1 = new HeadCoach();
		coach1.setHeadCoachName("Henry");
		CoachStandingSubject coachStandingPublisher = CoachStandingSubject.instance();
		coachStandingPublisher.setBestCoach(coach1);
		IHeadCoach coach = coachStandingPublisher.getBestCoach();
		Assert.assertSame(coach1, coach);
	}

	@Test
	public void getBestCoachTest() {
		IHeadCoach coach1 = new HeadCoach();
		IHeadCoach coach2 = new HeadCoach();
		coach1.setHeadCoachName("Henry");
		coach2.setHeadCoachName("Phil");
		CoachStandingSubject coachStandingPublisher = CoachStandingSubject.instance();
		coachStandingPublisher.notifyCoachStanding(coach1);
		coachStandingPublisher.notifyCoachStanding(coach2);
		coachStandingPublisher.notifyCoachStanding(coach1);
		IHeadCoach bestCoach = coachStandingPublisher.getBestCoach();
		Assert.assertSame(coach1, bestCoach);
	}

	@Test
	public void resetStandingTest() {
		IHeadCoach coach1 = new HeadCoach();
		coach1.setHeadCoachName("Henry");
		CoachStandingSubject coachStandingPublisher = CoachStandingSubject.instance();
		coachStandingPublisher.setBestCoach(coach1);
		coachStandingPublisher.resetCoachStandings();
		IHeadCoach coach = coachStandingPublisher.getBestCoach();
		Assert.assertNull(coach);

	}
}
