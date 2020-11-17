package com.persistencemodel;

import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CoachDB implements ICoachDB {

    @Override
    public void loadCoaches(JSONArray coachesArray, ILeague league) {
        for (Object currentCoach : coachesArray) {
            JSONObject coachObject = (JSONObject) currentCoach;
            IHeadCoach coach = new HeadCoach();
            coach.setHeadCoachId((int) (long) coachObject.get("headCoachId"));
            coach.setHeadCoachName((String) coachObject.get("headCoachName"));
            coach.setHeadCoachSkating((float) (double) coachObject.get("headCoachSkating"));
            coach.setHeadCoachShooting((float) (double) coachObject.get("headCoachShooting"));
            coach.setHeadCoachChecking((float) (double) coachObject.get("headCoachChecking"));
            coach.setHeadCoachSaving((float) (double) coachObject.get("headCoachSaving"));
            league.setCoach(coach);
        }
    }

    @Override
    public void loadHeadCoach(JSONObject coachObject, ITeam team) {
        IHeadCoach headCoach = new HeadCoach();
        headCoach.setHeadCoachId((int) (long) coachObject.get("headCoachId"));
        headCoach.setHeadCoachName((String) coachObject.get("headCoachName"));
        headCoach.setHeadCoachSkating((float) (double) coachObject.get("headCoachSkating"));
        headCoach.setHeadCoachShooting((float) (double) coachObject.get("headCoachShooting"));
        headCoach.setHeadCoachChecking((float) (double) coachObject.get("headCoachChecking"));
        headCoach.setHeadCoachSaving((float) (double) coachObject.get("headCoachSaving"));
        team.setHeadCoach(headCoach);
    }
}
