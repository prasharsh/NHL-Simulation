package com.persistencemodel;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.google.gson.Gson;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame implements ISaveGame {

    private final IDisplayToUser displayToUser = new DisplayToUser();

    @Override
    public boolean exportGameToJSON(IGame game) {
        FileWriter fileWriter = null;
        try {
            Gson gson = new Gson();
            ILeague league = game.getLeagues().get(0);
            String leagueName = league.getLeagueName();
            if (leagueName.isEmpty()) {
                displayToUser.displayMsgToUser("Invalid league name. Please provide a valid leagueName to save");
            } else {
                String filePath = generateFilePath();

                JSONArray existingLeagues = getLeaguesArray(filePath);
                int leagueIndex = getMatchingLeague(existingLeagues, leagueName);
                if (leagueIndex >= 0) {
                    existingLeagues.remove(leagueIndex);
                }
                existingLeagues.add(league);

                fileWriter = new FileWriter(filePath);
                gson.toJson(existingLeagues, fileWriter);
                displayToUser.displayMsgToUser("Successfully exported current state of Game as JSON into a file.");
            }
        } catch (IOException e) {
            displayToUser.displayMsgToUser(e.getLocalizedMessage());
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                displayToUser.displayMsgToUser(e.getLocalizedMessage());
            }
        }
        return false;
    }

    private String generateFilePath() {
        return Constants.STORAGE_PATH;
    }

    private int getMatchingLeague(JSONArray leagues, String leagueName) {
        int matchingIndex = -1;
        for (int i = 0; i < leagues.size(); i++) {
            JSONObject currentLeague = (JSONObject) leagues.get(i);
            String currentLeagueName = (String) currentLeague.get("leagueName");
            if (leagueName.equals(currentLeagueName)) {
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
            existingLeagues = (JSONArray)leagueDb;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return existingLeagues;
    }
}
