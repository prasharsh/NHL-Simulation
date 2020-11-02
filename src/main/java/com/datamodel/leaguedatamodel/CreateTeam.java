package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.Scanner;
import com.inputoutputmodel.CreateTeamUI;
import com.inputoutputmodel.ICreateTeamUI;

public class CreateTeam {

	public void createNewTeam(Game game) {

		ILeague currentLeague = game.getLeagues().get(0);
		LoadTeam loadTeam = new LoadTeam();
		ICreateTeamUI teamUI = new CreateTeamUI();
		Scanner teamInput = new Scanner(System.in);
		teamUI.displayMessage("Initiating team creation flow");
		ArrayList<IConference> availableConferences = currentLeague.getConferences();
		IConference currentConference = null;
		boolean isConferenceNotSelected = true;
		while (isConferenceNotSelected) {
			teamUI.displayConferences(availableConferences);
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < availableConferences.size()) {
				currentConference = availableConferences.get(inputIndex);
				isConferenceNotSelected = false;
				teamUI.displaySuccess("Selected conference '" + currentConference.getConferenceName() + "'");
			} else {
				teamUI.displayError("Invalid serial number for conference");
			}
		}

		ArrayList<IDivision> availableDivisions = currentConference.getDivisions();
		IDivision currentDivision = null;
		boolean isDivisionNotSelected = true;
		while (isDivisionNotSelected) {
			teamUI.displayDivisions(availableDivisions);
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < availableDivisions.size()) {
				currentDivision = availableDivisions.get(inputIndex);
				isDivisionNotSelected = false;
				teamUI.displaySuccess("Selected division '" + currentDivision.getDivisionName() + "'");
			} else {
				teamUI.displayError("Invalid serial number for division");
			}
		}

		ArrayList<ITeam> teams = currentDivision.getTeams();
		ITeam currentTeam = new Team();
		boolean isTeamNotCreated = true;
		while (isTeamNotCreated) {
			teamUI.displayMessage("Enter a name for your team to be created: ");
			String teamName = teamInput.nextLine().trim();
			ITeam teamExist = loadTeam.teamExist(teamName, teams);

			if (teamExist == null) {
				if (teamName.isEmpty()) {
					teamUI.displayError("Team Name can't be empty!");
				} else {
					currentTeam.setTeamName(teamName);
					currentTeam.setTeamCreatedBy("user");
					teamUI.displaySuccess("Your team created with the name '" + teamName + "'");
					currentDivision.addTeam(currentTeam);
					isTeamNotCreated = false;
				}
			} else {
				teamUI.displayError("Oops! A team already exists with this name.");
			}
		}

		ArrayList<IGeneralManager> availableManagers = currentLeague.getManagers();
		boolean isManagerNotHired = true;
		while (isManagerNotHired) {
			teamUI.displayGeneralManagers(availableManagers);
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < availableManagers.size()) {
				currentTeam.setGeneralManager(availableManagers.get(inputIndex));
				teamUI.displaySuccess("General manager '" + availableManagers.get(inputIndex).getGeneralManagerName()
						+ "' hired for your team");
				availableManagers.remove(inputIndex);
				isManagerNotHired = false;
			} else {
				teamUI.displayError("Please select a valid serial number for general manager");
			}
		}

		ArrayList<IHeadCoach> availableCoaches = currentLeague.getCoaches();
		boolean isCoachNotHired = true;
		while (isCoachNotHired) {
			teamUI.displayHeadCoaches(availableCoaches);
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < availableCoaches.size()) {
				currentTeam.setHeadCoach(availableCoaches.get(inputIndex));
				teamUI.displaySuccess(
						"Head coach '" + availableCoaches.get(inputIndex).getHeadCoachName() + "' hired for your team");
				availableCoaches.remove(inputIndex);
				isCoachNotHired = false;
			} else {
				teamUI.displayError("Please select a valid serial number for head coach");
			}
		}

		ArrayList<IPlayer> availableFreeAgents = getRankedFreeAgents(currentLeague.getFreeAgents());
		int hiredSkaters = 0;
		int hiredGoalies = 0;
		int hiredPlayers = 0;
		while (hiredPlayers < 20) {
			teamUI.displayFreeAgents(availableFreeAgents, hiredSkaters, hiredGoalies);
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < availableFreeAgents.size()) {
				if (availableFreeAgents.get(inputIndex).getPlayerPosition().equals("goalie")) {
					if (hiredGoalies >= 2) {
						teamUI.displayError("There can't be more than 2 goalies in your team");
						continue;
					} else {
						addFreeAgentToTeam(availableFreeAgents.get(inputIndex), currentTeam);
						hiredGoalies++;
						hiredPlayers++;
						availableFreeAgents.remove(inputIndex);
					}
				} else {
					if (hiredSkaters >= 18) {
						teamUI.displayError("There can't be more than 18 skaters in your team");
						continue;
					} else {
						addFreeAgentToTeam(availableFreeAgents.get(inputIndex), currentTeam);
						hiredSkaters++;
						hiredPlayers++;
						availableFreeAgents.remove(inputIndex);
					}
				}
			} else {
				teamUI.displayError("Please select a valid serial number for player");
			}
			teamUI.displaySuccess(hiredSkaters + "/18 skaters and " + hiredGoalies + "/2 goalies hired!!!");
		}
		teamUI.displaySuccess("Player hiring completed!!! Here is your list of 20 players");

		boolean isCaptainSelected = false;
		while (isCaptainSelected == false) {
			teamUI.displayPlayers(currentTeam.getPlayers());
			int inputIndex = teamUI.getUserChoiceFromList(teamInput);
			if (inputIndex >= 0 && inputIndex < currentTeam.getPlayers().size()) {
				currentTeam.getPlayers().get(inputIndex).setPlayerCaptain(true);
				teamUI.displaySuccess(
						currentTeam.getPlayers().get(inputIndex).getPlayerName() + " selected as your captain");
				isCaptainSelected = true;
			} else {
				teamUI.displayError("Please select a valid serial number for player");
			}
		}
	}

	public void addFreeAgentToTeam(IPlayer freeAgent, ITeam team) {
		team.addPlayer(freeAgent);
	}

	public ArrayList<IPlayer> getRankedFreeAgents(ArrayList<IPlayer> freeAgentsList) {
		freeAgentsList.sort((agent1, agent2) -> {
			double agent1Score = agent1.getPlayerStrength();
			double agent2Score = agent2.getPlayerStrength();
			if (agent1Score > agent2Score) {
				return -1;
			} else {
				return 0;
			}
		});
		return freeAgentsList;
	}
}