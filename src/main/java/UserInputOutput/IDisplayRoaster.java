package UserInputOutput;

import java.util.ArrayList;

import g4dhl.IFreeAgent;
import g4dhl.IPlayer;

public interface IDisplayRoaster {
    int takeNumberInputFromUser();
    void displayMessageToUser(String s);
    void displayPlayersToBeDropped(ArrayList<IPlayer> players, int count);
    int inputPlayerIndexToDrop();
    void displayFreeAgentsToBeHired(ArrayList<IFreeAgent> freeAgents, int count);
    int inputFreeAgentIndexToHire();
}
