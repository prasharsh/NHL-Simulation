package com.datamodeltest.leaguedatamodeltest;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ImportJson;
import com.datamodel.leaguedatamodel.ExportGameToFile;
import com.google.gson.Gson;

public class ExportGameToFileTest {

	private static IGame game;
	ClassLoader classLoader = getClass().getClassLoader();
	String mockReaderFile = classLoader.getResource("LeagueJSONMock.json").getPath();
	private String mockWriterFile = classLoader.getResource("gameStateMock.txt").getPath();

	private void importJSON() {
		game = new Game();
		ImportJson importer = new ImportJson();
		ILeague league = importer.parseJson(mockReaderFile);
		game.addLeague(league);
	}

	@Test
	public void SerializeStateTest() {
		importJSON();
		FileReader text = null;
		ExportGameToFile testState = new ExportGameToFile();
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