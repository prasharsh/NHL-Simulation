package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.*;

import java.util.List;
import java.util.Scanner;

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