package UserInputOutput;

import g4dhl.IFreeAgent;
import g4dhl.IPlayer;

import java.util.ArrayList;

public interface IDisplayRoaster {
    int takeNumberInputFromUser();
    void displayMessageToUser(String s);
    void displayPlayersToBeDropped(ArrayList<IPlayer> players, int count);
    int inputPlayerIndexToDrop();
    void displayFreeAgentsToBeHired(ArrayList<IFreeAgent> freeAgents, int count);
    int inputFreeAgentIndexToHire();
}
