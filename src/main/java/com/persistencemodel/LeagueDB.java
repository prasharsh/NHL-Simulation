package com.persistencemodel;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.League;
import com.google.gson.Gson;
import com.inputoutputmodel.IDisplayToUser;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LeagueDB implements ILeagueDB {

	private final static Logger logger = Logger.getLogger(LeagueDB.class);
	private static final String STORAGE_PATH = Paths.get("").toAbsolutePath().toString() + "\\data\\leagueDB.json";


	@Override
	public boolean checkIfLeagueExists(String leagueName) {
		JSONArray existingLeagues = getDBLeaguesArray();
		boolean leagueExists = findLeague(existingLeagues, leagueName);
		return leagueExists;
	}

	@Override
	public List<ILeague> loadLeaguesFromTeamName(String teamName) {
		List<ILeague> leaguesMatched = new ArrayList<>();
		JSONArray existingLeagues = getDBLeaguesArray();
		JSONArray mappedLeagues = fetchLeaguesHavingTeam(existingLeagues, teamName);
		for(Object currentLeague : mappedLeagues) {
			JSONObject leagueObject = (JSONObject) currentLeague;
			ILeague league = new League();
			league.setLeagueId((int) (long) leagueObject.get("leagueId"));
			league.setLeagueName((String) leagueObject.get("leagueName"));
			league.setCurrentDate(getFormattedDate((String) leagueObject.get("currentDate")));
			IConferenceDB conferenceDB = new ConferenceDB();
			JSONArray conferences = (JSONArray) leagueObject.get("conferences");
			conferenceDB.loadConferences(conferences, league);
			IGameplayConfigDB gameplayConfigDB = new GameplayConfigDB();
			JSONObject gameplayConfig = (JSONObject) leagueObject.get("gameplayConfig");
			gameplayConfigDB.loadGameplayConfig(gameplayConfig, league);
			ICoachDB coachDB = new CoachDB();
			JSONArray coaches = (JSONArray) leagueObject.get("coaches");
			coachDB.loadCoaches(coaches, league);
			IManagerDB managerDB = new ManagerDB();
			JSONArray managers = (JSONArray) leagueObject.get("managers");
			managerDB.loadManagers(managers, league);
			IFreeAgentDB freeAgentDB = new FreeAgentDB();
			JSONArray freeAgents = (JSONArray) leagueObject.get("freeAgents");
			freeAgentDB.loadFreeAgents(freeAgents, league);
			leaguesMatched.add(league);
		}
		return leaguesMatched;
	}

	@Override
	public boolean exportGameToJSON(IGame game) {
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IDisplayToUser displayToUser = ioFactory.createDisplayToUser();
		FileWriter fileWriter = null;
		try {
			Gson gson = new Gson();
			ILeague league = game.getLeagues().get(0);
			String leagueName = league.getLeagueName();
			if(leagueName.isEmpty()) {
				displayToUser.displayMsgToUser("Invalid league name. Please provide a valid leagueName to save");
			} else {
				String filePath = generateFilePath();

				JSONArray existingLeagues = getLeaguesArray(filePath);
				int leagueIndex = getMatchingLeague(existingLeagues, leagueName);
				if(leagueIndex >= 0) {
					existingLeagues.remove(leagueIndex);
				}
				existingLeagues.add(league);

				fileWriter = new FileWriter(filePath);
				gson.toJson(existingLeagues, fileWriter);
				displayToUser.displayMsgToUser("Successfully exported current state of Game as JSON into a file.");
			}
		} catch(IOException e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			try {
				if(isFileWriterNotNull(fileWriter)) {
					fileWriter.close();
				}
			} catch(IOException | NullPointerException e) {
				logger.error(e.getLocalizedMessage());
			}
		}
		return false;
	}

	private int getMatchingLeague(JSONArray leagues, String leagueName) {
		int matchingIndex = -1;
		for(int i = 0; i < leagues.size(); i++) {
			JSONObject currentLeague = (JSONObject) leagues.get(i);
			String currentLeagueName = (String) currentLeague.get("leagueName");
			if(leagueName.equals(currentLeagueName)) {
				matchingIndex = i;
				break;
			}
		}
		return matchingIndex;
	}

	private JSONArray getLeaguesArray(String filePath) {
		JSONParser jsonParser = new JSONParser();
		JSONArray existingLeagues = new JSONArray();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
			Object leagueDb = jsonParser.parse(fileReader);
			existingLeagues = (JSONArray) leagueDb;
		} catch(ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(isFileReaderNotNull(fileReader)) {
					fileReader.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return existingLeagues;
	}

	private Date getFormattedDate(String dateValue) {
		Date formattedDate = null;
		if(dateValue == null) {
			return formattedDate;
		}
		try {
			java.util.Date dateNew = new SimpleDateFormat("MMM dd, yyyy").parse(dateValue);
			formattedDate = new Date(dateNew.getTime());
		} catch(java.text.ParseException e) {
			logger.error(e.getLocalizedMessage());
		}
		return formattedDate;
	}

	private JSONArray fetchLeaguesHavingTeam(JSONArray leagues, String teamName) {
		JSONArray leaguesMatched = new JSONArray();
		for(Object currentLeague : leagues) {
			JSONObject league = (JSONObject) currentLeague;
			JSONArray conferences = (JSONArray) league.get("conferences");
			boolean matchFound = false;
			for(Object currentConference : conferences) {
				if(matchFound) {
					break;
				}
				JSONObject conference = (JSONObject) currentConference;
				JSONArray divisions = (JSONArray) conference.get("divisions");
				for(Object currentDivision : divisions) {
					if(matchFound) {
						break;
					}
					JSONObject division = (JSONObject) currentDivision;
					JSONArray teams = (JSONArray) division.get("teams");
					for(Object currentTeam : teams) {
						if(matchFound) {
							break;
						}
						JSONObject team = (JSONObject) currentTeam;
						String currentTeamName = (String) team.get("teamName");
						String createdBy = (String) team.get("teamCreatedBy");
						if(isStringNotNull(createdBy) && createdBy.equals("user") && currentTeamName.equals(teamName)) {
							leaguesMatched.add(currentLeague);
							matchFound = true;
						}
					}
				}
			}
		}
		return leaguesMatched;
	}

	private boolean findLeague(JSONArray leagues, String leagueName) {
		boolean leagueFound = false;
		for(Object league : leagues) {
			JSONObject currentLeague = (JSONObject) league;
			String currentLeagueName = (String) currentLeague.get("leagueName");
			if(leagueName.equals(currentLeagueName)) {
				leagueFound = true;
				break;
			}
		}
		return leagueFound;
	}

	private JSONArray getDBLeaguesArray() {
		JSONArray existingLeagues = new JSONArray();
		FileReader fileReader = null;
		String filePath = generateFilePath();
		try {
			JSONParser jsonParser = new JSONParser();
			fileReader = new FileReader(filePath);
			Object leagueDb = jsonParser.parse(fileReader);
			existingLeagues = (JSONArray) leagueDb;
		} catch(ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(isFileReaderNotNull(fileReader)) {
					fileReader.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return existingLeagues;
	}

	private String generateFilePath() {
		return STORAGE_PATH;
	}

	private boolean isStringNotNull(String text) {
		if(text == null) {
			return false;
		}
		return true;
	}

	private boolean isFileReaderNotNull(FileReader fileReader) {
		if(fileReader == null) {
			return false;
		}
		return true;
	}

	private boolean isFileWriterNotNull(FileWriter fileWriter) {
		if(fileWriter == null) {
			return false;
		}
		return true;
	}
}
