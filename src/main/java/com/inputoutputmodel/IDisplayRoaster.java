package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.IPlayer;

import java.util.List;

public interface IDisplayRoaster {

	int takeNumberInputFromUser();

	void displayMessageToUser(String s);

	void displayPlayersToBeDropped(List<IPlayer> players, int count);

	int inputPlayerIndexToDrop();

	void displayFreeAgentsToBeHired(List<IPlayer> freeAgents, int count);

	int inputFreeAgentIndexToHire();
}