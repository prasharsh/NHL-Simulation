package com.dal.dhl.stateMachine;

import com.google.gson.Gson;
import g4dhl.Game;
import g4dhl.IGame;
import g4dhl.ILeague;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;

public class SerializeStateTest {
    private static IGame game;
    private static String mockReaderFile = System.getProperty("user.dir").concat("\\src\\test\\java\\com\\dal\\dhl\\stateMachine\\LeagueJSONMock.json");
    private static String mockWriterFile = System.getProperty("user.dir").concat("\\src\\test\\java\\com\\dal\\dhl\\stateMachine\\gameStateMock.txt");
    @BeforeClass
    public static void importJSON() {
        game = new Game();
        ImportJson importer = new ImportJson();
        System.out.println();
        ILeague league = importer.parseJson(mockReaderFile);
        game.addLeague(league);
    }

    @Test
    public void SerializeStateTest() {
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
