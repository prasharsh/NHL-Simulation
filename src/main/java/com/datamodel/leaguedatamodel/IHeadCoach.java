package com.datamodel.leaguedatamodel;

public interface IHeadCoach {

    int getHeadCoachId();

    String getHeadCoachName();

    float getHeadCoachSkating();

    float getHeadCoachShooting();

    float getHeadCoachChecking();

    float getHeadCoachSaving();

    boolean setHeadCoachId(int headCoachId);

    boolean setHeadCoachName(String headCoachName);

    boolean setHeadCoachSkating(float headCoachSkating);

    boolean setHeadCoachShooting(float headCoachShooting);

    boolean setHeadCoachChecking(float headCoachChecking);

    boolean setHeadCoachSaving(float headCoachSaving);
}
