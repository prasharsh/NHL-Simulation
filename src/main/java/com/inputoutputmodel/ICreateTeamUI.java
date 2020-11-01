package com.inputoutputmodel;

import java.util.ArrayList;
import java.util.Scanner;

import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;

public interface ICreateTeamUI {
	void displayConferences(ArrayList<IConference> conferences);

	void displayDivisions(ArrayList<IDivision> divisions);

	void displayHeadCoaches(ArrayList<IHeadCoach> headCoaches);

	void displayGeneralManagers(ArrayList<IGeneralManager> generalManagers);

	void displayFreeAgents(ArrayList<IPlayer> freeAgents, int skaterCount, int goalieCount);

	void displayPlayers(ArrayList<IPlayer> players);

	void displayError(String errorMessage);

	void displayMessage(String message);

	void displaySuccess(String successMessage);

	int getUserChoiceFromList(Scanner scanner);
}
