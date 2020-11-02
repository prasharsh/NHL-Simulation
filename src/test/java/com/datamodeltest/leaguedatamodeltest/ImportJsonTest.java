package com.datamodeltest.leaguedatamodeltest;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.datamodel.leaguedatamodel.ImportJson;

public class ImportJsonTest {

	private static ImportJson parserObj;
	private IDisplayToUser displayToUser;

	public ImportJsonTest(){
		displayToUser = new DisplayToUser();
	}

	@BeforeClass
	public static void initializeParser() {
		parserObj = new ImportJson();
	}

	@AfterClass
	public static void disconnectParser() {
		parserObj = null;
	}

	@Test
	public void containStringKeyTest() {
		String jsonFile = "{\"leagueName\":\"DHL\"}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		String val = parserObj.containStringKey(jsonObj, "leagueName");
		Assert.assertEquals(val, "DHL");
	}

	@Test
	public void containArrayTest() {
		String jsonFile = "{\"player\":[{\"name\":\"one\",\"position\":\"goli\"}]}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		jsonArray = parserObj.containArray(jsonObj, "player");
		Assert.assertEquals(1, jsonArray.size());
	}

	@Test
	public void containKeyCaptainTest() {
		String jsonFile = "{\"captain\":true}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		Boolean val = parserObj.containKeyCaptain(jsonObj, "captain");
		Assert.assertEquals(true, val);
	}

	@Test
	public void containObjectKeyTest() {
		String jsonFile = "{\"gameplayConfig\": {\"aging\": {}}}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonOutput = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
			jsonOutput = (JSONObject) parser.parse("{\"aging\": {}}");
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		JSONObject val = parserObj.containObjectKey(jsonObj, "gameplayConfig");
		Assert.assertEquals(jsonOutput, val);
	}

	@Test
	public void containFloatKeyTest() {
		String jsonFile = "{\"injury\": 0.6}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		float val = parserObj.containFloatKey(jsonObj, "injury");
		Assert.assertEquals(val, (float) 0.6, 0.0);
	}

	@Test
	public void containIntKeyTest() {
		String jsonFile = "{\"recovery\": 10}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			displayToUser.displayMsgToUser(e.getLocalizedMessage());
		}
		float val = parserObj.containIntKey(jsonObj, "recovery");
		Assert.assertEquals(10, val, 0.0);
	}
}