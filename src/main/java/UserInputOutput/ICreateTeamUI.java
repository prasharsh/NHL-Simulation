package UserInputOutput;

import g4dhl.*;

import java.util.ArrayList;

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
}
