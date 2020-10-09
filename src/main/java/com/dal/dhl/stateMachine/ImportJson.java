package com.dal.dhl.stateMachine;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.FreeAgent;
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
import g4dhl.Team;

public class ImportJson {

	public ILeague parseJson(String filePath) {
		Object jsonObj = null;
		try {
			JSONParser parser = new JSONParser();
			Reader reader = new FileReader(filePath);
			jsonObj = parser.parse(reader);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		JSONObject jsonObject = (JSONObject) jsonObj;
		ILeague leagueObj = new League();
		String leagueName = containKey(jsonObject, "leagueName");
		leagueObj.setLeagueName(leagueName);
		JSONArray conferencesArray = containArray(jsonObject, "conferences");
		for (int a = 0; a < conferencesArray.size(); a++) {
			JSONObject conference = (JSONObject) conferencesArray.get(a);
			String conferenceName = containKey(conference, "conferenceName");
			JSONArray divisionsArray = containArray(conference, "divisions");
			IConference conferenceObj = new Conference();
			conferenceObj.setConferenceName(conferenceName);
			for (int b = 0; b < divisionsArray.size(); b++) {
				JSONObject division = (JSONObject) divisionsArray.get(b);
				String divisionName = containKey(division, "divisionName");
				JSONArray teamsArray = containArray(division, "teams");
				IDivision divisionObj = new Division();
				divisionObj.setDivisionName(divisionName);
				for (int c = 0; c < teamsArray.size(); c++) {
					JSONObject team = (JSONObject) teamsArray.get(c);
					String generalManagerName = containKey(team, "generalManager");
					String headCoachName = containKey(team, "headCoach");
					String teamName = containKey(team, "teamName");
					IGeneralManager generalManagerObj = new GeneralManager();
					generalManagerObj.setGeneralManagerName(generalManagerName);
					IHeadCoach headCoachObj = new HeadCoach();
					headCoachObj.setHeadCoachName(headCoachName);
					JSONArray playersArray = containArray(team, "players");
					ITeam teamObj = new Team();
					teamObj.setTeamName(teamName);
					teamObj.setGeneralManager(generalManagerObj);
					teamObj.setHeadCoach(headCoachObj);
					int captainCount = 0;
					for (int i = 0; i < playersArray.size(); i++) {
						JSONObject player = (JSONObject) playersArray.get(i);
						String playerName = containKey(player, "playerName");
						String playerPosition = containKey(player, "position");
						Boolean isPlayerCaptain = containKeyCaptain(player, "captain");
						if (isPlayerCaptain) {
							captainCount = captainCount + 1;
							if (captainCount > 1) {
								System.out.println("There is more than one caption in team: " + teamName);
								System.exit(1);
							}
						}
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
		JSONArray freeAgentsArray = containArray(jsonObject, "freeAgents");
		for (int j = 0; j < freeAgentsArray.size(); j++) {
			JSONObject freeAgent = (JSONObject) freeAgentsArray.get(j);
			String freeAgentName = containKey(freeAgent, "playerName");
			String freeAgentPosition = containKey(freeAgent, "position");
			Boolean isFreeAgentCaptain = containKeyCaptain(freeAgent, "captain");
			IFreeAgent freeAgentObj = new FreeAgent();
			freeAgentObj.setFreeAgentName(freeAgentName);
			freeAgentObj.setFreeAgentPosition(freeAgentPosition);
			freeAgentObj.setFreeAgentCaptain(isFreeAgentCaptain);
			leagueObj.addFreeAgent(freeAgentObj);
		}

		return leagueObj;
	}

	public String containKey(JSONObject obj, String key) {
		if (!obj.containsKey(key)) {
			System.out.println("Inavalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		String hasKey = (String) obj.get(key);
		if (hasKey == null || hasKey.isBlank()) {
			System.out.println("Inavalid JSON, It does not have value for the " + key);
			System.exit(1);
		}
		return hasKey;
	}

	public Boolean containKeyCaptain(JSONObject obj, String key) {
		if (!obj.containsKey(key)) {
			System.out.println("Inavalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		if (obj.get(key) == null) {
			System.out.println("Inavalid JSON, It does not have value for " + key);
			System.exit(1);
		}
		Boolean hasKeyCaptain = false;
		try {
			hasKeyCaptain = (Boolean) obj.get(key);
		} catch (Exception e) {
			System.out.println("Captain value is invalid");
			System.exit(1);
		}
		return hasKeyCaptain;
	}

	public JSONArray containArray(JSONObject obj, String arrayKey) {
		if (!obj.containsKey(arrayKey)) {
			System.out.println("Inavalid JSON, It does not have " + arrayKey + " information");
			System.exit(1);
		}
		JSONArray hasArray = (JSONArray) obj.get(arrayKey);
		if (hasArray == null || hasArray.size() == 0) {
			System.out.println("Inavalid JSON, It does not have value for the " + arrayKey);
			System.exit(1);
		}
		return hasArray;
	}

}
