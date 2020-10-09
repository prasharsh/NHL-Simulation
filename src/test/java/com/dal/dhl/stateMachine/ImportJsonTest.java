package com.dal.dhl.stateMachine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class ImportJsonTest {

	@Test
	public void containStringKeyTest() {
		String jsonFile = "{\"leagueName\":\"DHL\"}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		String key = "leagueName";
		Assert.assertTrue("key exist", jsonObj.containsKey(key));

		jsonFile = "{\"league\":\"DHL\"}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertFalse("key does not exist", jsonObj.containsKey(key));

		jsonFile = "{\"leagueName\":\"\"}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals("", jsonObj.get(key));

		jsonFile = "{\"leagueName\":null}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(null, jsonObj.get(key));
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
			System.out.println(e.getMessage());
		}
		String key = "player";
		Assert.assertTrue("Array key exist", jsonObj.containsKey(key));

		jsonFile = "{\"playerList\":[{\"name\":\"one\",\"position\":\"goli\"}]}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertFalse("Arraykey does not exist", jsonObj.containsKey(key));

		jsonFile = "{\"player\":[{\"name\":\"\",\"position\":\"goli\"}]}";
		key = "name";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(null, jsonObj.get(key));

		jsonFile = "{\"player\":[]}";
		key = "player";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
			jsonArray = (JSONArray) jsonObj.get(key);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(0, jsonArray.size());
	}

	@Test
	public void containKeyCaptainTest() {
		String jsonFile = "{\"captain\":true}";
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		String key = "captain";
		Assert.assertTrue("Boolean key exist", jsonObj.containsKey(key));

		jsonFile = "{\"isCaptain\":true}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertFalse("Boolean key does not exist", jsonObj.containsKey(key));

		jsonFile = "{\"captain\":\"no\"}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals("no", jsonObj.get(key));

		jsonFile = "{\"captain\":null}";
		try {
			jsonObj = (JSONObject) parser.parse(jsonFile);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(null, jsonObj.get(key));
	}

}
