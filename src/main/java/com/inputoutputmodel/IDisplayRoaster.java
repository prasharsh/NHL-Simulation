package com.inputoutputmodel;
import java.util.List;
import com.datamodel.leaguedatamodel.IPlayer;

public interface IDisplayRoaster {

	int takeNumberInputFromUser();

	void displayMessageToUser(String s);

	void displayPlayersToBeDropped(List<IPlayer> players, int count);

	int inputPlayerIndexToDrop();

	void displayFreeAgentsToBeHired(List<IPlayer> freeAgents, int count);

	int inputFreeAgentIndexToHire();
}