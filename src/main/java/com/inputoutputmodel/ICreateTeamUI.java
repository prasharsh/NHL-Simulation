package com.inputoutputmodel;

import java.util.List;
import java.util.Scanner;

import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;

public interface ICreateTeamUI {

    void displayConferences(List<IConference> conferences);

    void displayDivisions(List<IDivision> divisions);

    void displayHeadCoaches(List<IHeadCoach> headCoaches);

    void displayGeneralManagers(List<IGeneralManager> generalManagers);

    void displayFreeAgents(List<IPlayer> freeAgents, int forwardCount, int defenseCount, int goalieCount);

    void displayPlayers(List<IPlayer> players);

    void displayError(String errorMessage);

    void displayMessage(String message);

    void displaySuccess(String successMessage);

    int getUserChoiceFromList(Scanner scanner);
}