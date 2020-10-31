package com.inputoutputmodel;

import java.util.ArrayList;

import com.datamodeltest.leaguedatamodel.IPlayer;

public interface IDisplayRoaster {
    int takeNumberInputFromUser();

    void displayMessageToUser(String s);

    void displayPlayersToBeDropped(ArrayList<IPlayer> players, int count);

    int inputPlayerIndexToDrop();

    void displayFreeAgentsToBeHired(ArrayList<IPlayer> freeAgents, int count);

    int inputFreeAgentIndexToHire();
}
