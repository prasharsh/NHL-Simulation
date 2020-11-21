package com.datamodel.leaguedatamodel;

public interface IGeneralManager {

    int getGeneralManagerId();

    String getGeneralManagerName();

    boolean setGeneralManagerId(int generalManagerId);

    boolean setGeneralManagerName(String generalManagerName);

    String getGeneralManagerPersonality();

    boolean setGeneralManagerPersonality(String generalManagerPersonality);
}
