package com.dal.dhl.stateMachine;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.FreeAgent;
import g4dhl.Game;
import g4dhl.GeneralManager;
import g4dhl.HeadCoach;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.IGeneralManager;
import g4dhl.IHeadCoach;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Player;
import g4dhl.Team;;

public class Main {

	public String setJsonPath() {
		Scanner scanInput = new Scanner(System.in);
		String path = scanInput.nextLine().trim();
		if (isNullOrEmpty(path)) {
			path = "loadTeam";
		}
		scanInput.close();
		return path;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}

	public String containsKey(JSONObject jsonObj, String key) {
		String str;
		if (jsonObj.containsKey(key)) {
			str = (String) jsonObj.get(key);
			return str;
		}
		str = "Provide " + key + " in JSON file.";
		return str;
	}

	public static void main(String[] args) throws IOException, ParseException {

		Main objMain = new Main();
		JSONParser parser = new JSONParser();

		System.out.println("Enter path to JSON file: ");
//		"C:\\Users\\deepd\\OneDrive\\Desktop\\league.json" // path to file
		String filePath = objMain.setJsonPath();
		System.out.println(filePath);
		Object jsonObj = null;
		try {
			Reader reader = new FileReader(filePath);
			jsonObj = parser.parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) jsonObj;
		Game game = new Game();
		ILeague leagueObj = new League();
//		String leagueName = objMain.containsKey(jsonObject, "leagueName");
		String leagueName = (String) jsonObject.get("leagueName");
		leagueObj.setLeagueName(leagueName);
		JSONArray conferencesArray = (JSONArray) jsonObject.get("conferences");

		for (int a = 0; a < conferencesArray.size(); a++) {
			JSONObject conference = (JSONObject) conferencesArray.get(a);
			String conferenceName = (String) conference.get("conferenceName");
			JSONArray divisionsArray = (JSONArray) conference.get("divisions");
			IConference conferenceObj = new Conference();
			conferenceObj.setConferenceName(conferenceName);
			for (int b = 0; b < divisionsArray.size(); b++) {

				JSONObject division = (JSONObject) divisionsArray.get(b);
				String divisionName = (String) division.get("divisionName");
				JSONArray teamsArray = (JSONArray) division.get("teams");
				IDivision divisionObj = new Division();
				divisionObj.setDivisionName(divisionName);
				for (int c = 0; c < teamsArray.size(); c++) {
					JSONObject team = (JSONObject) teamsArray.get(c);
					String generalManagerName = (String) team.get("generalManager");
					String headCoachName = (String) team.get("headCoach");
					String teamName = (String) team.get("teamName");
					IGeneralManager generalManagerObj = new GeneralManager();
					generalManagerObj.setGeneralManagerName(generalManagerName);
					IHeadCoach headCoachObj = new HeadCoach();
					headCoachObj.setHeadCoachName(headCoachName);
					JSONArray playersArray = (JSONArray) team.get("players");
					ITeam teamObj = new Team();
					teamObj.setTeamName(teamName);
					teamObj.setGeneralManager(generalManagerObj);
					teamObj.setHeadCoach(headCoachObj);
					for (int i = 0; i < playersArray.size(); i++) {
						JSONObject player = (JSONObject) playersArray.get(i);
						String playerName = (String) player.get("playerName");
						String playerPosition = (String) player.get("position");
						Boolean isPlayerCaptain = (Boolean) player.get("captain");
						IPlayer playerObj = new Player();
						playerObj.setPlayerName(playerName);
						playerObj.setPlayerPosition(playerPosition);
						playerObj.setPlayerCaptain(isPlayerCaptain);
						teamObj.addPlayer(playerObj);
					}
					divisionObj.addTeam(teamObj);
				}
				conferenceObj.addDivision(divisionObj);
			}
			leagueObj.addConference(conferenceObj);
		}
		JSONArray freeAgentsArray = (JSONArray) jsonObject.get("freeAgents");
		for (int j = 0; j < freeAgentsArray.size(); j++) {
			JSONObject freeAgent = (JSONObject) freeAgentsArray.get(j);
			String freeAgentName = (String) freeAgent.get("playerName");
			String freeAgentPosition = (String) freeAgent.get("position");
			Boolean isFreeAgentCaptain = (Boolean) freeAgent.get("captain");
			IFreeAgent freeAgentObj = new FreeAgent();
			freeAgentObj.setFreeAgentName(freeAgentName);
			freeAgentObj.setFreeAgentPosition(freeAgentPosition);
			freeAgentObj.setFreeAgentCaptain(isFreeAgentCaptain);
			leagueObj.addFreeAgent(freeAgentObj);
		}
		game.addLeague(leagueObj);
	}
}
