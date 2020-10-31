package com.datamodeltest;

import java.io.FileReader;

import com.datamodeltest.ImportJson;
import com.datamodeltest.SerializeState;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import com.datamodeltest.leaguedatamodel.Game;
import com.datamodeltest.leaguedatamodel.IGame;
import com.datamodeltest.leaguedatamodel.ILeague;

public class SerializeStateTest {
    private static IGame game;
    ClassLoader classLoader = getClass().getClassLoader();
    String mockReaderFile = classLoader.getResource("LeagueJSONMock.json").getPath();
    private String mockWriterFile = classLoader.getResource("gameStateMock.txt").getPath();

    private void importJSON() {
        game = new Game();
        ImportJson importer = new ImportJson();
        System.out.println();
        ILeague league = importer.parseJson(mockReaderFile);
        game.addLeague(league);
    }

    @Test
    public void SerializeStateTest() {
        importJSON();
        FileReader text = null;
        SerializeState testState = new SerializeState();
        try {
            testState.exportGameToJSON(game, mockWriterFile);
            Gson gson = new Gson();
            text = new FileReader(mockWriterFile);
            JSONParser parser = new JSONParser();
            JSONObject newObj = (JSONObject) parser.parse(text);
            JSONObject oldObj = (JSONObject) parser.parse(gson.toJson(game));
            Assert.assertEquals(oldObj, newObj);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error Occurred");
        } finally {
            try {
                text.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
