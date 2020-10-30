package UserInputOutput;

import java.util.ArrayList;
import java.util.Scanner;

import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.IGeneralManager;
import g4dhl.IHeadCoach;
import g4dhl.IPlayer;

public interface ICreateTeamUI {
    void displayConferences(ArrayList<IConference> conferences);
    void displayDivisions(ArrayList<IDivision> divisions);
    void displayHeadCoaches(ArrayList<IHeadCoach> headCoaches);
    void displayGeneralManagers(ArrayList<IGeneralManager> generalManagers);
    void displayFreeAgents(ArrayList<IFreeAgent> freeAgents);
    void displayPlayers(ArrayList<IPlayer> players);
    void displayError(String errorMessage);
    void displayMessage(String message);
    void displaySuccess(String successMessage);
    int getUserChoiceFromList(Scanner scanner);
}
