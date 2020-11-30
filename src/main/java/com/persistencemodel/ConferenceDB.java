package com.persistencemodel;

import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.ILeague;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConferenceDB implements IConferenceDB {

	@Override
	public void loadConferences(JSONArray conferencesArray, ILeague league) {
		for(Object currentConference : conferencesArray) {
			JSONObject conferenceObject = (JSONObject) currentConference;
			IConference conference = new Conference();
			conference.setConferenceId((int) (long) conferenceObject.get("conferenceId"));
			conference.setConferenceName((String) conferenceObject.get("conferenceName"));
			JSONArray divisions = (JSONArray) conferenceObject.get("divisions");
			IDivisionDB divisionDB = new DivisionDB();
			divisionDB.loadDivisions(divisions, conference);
			league.addConference(conference);
		}
	}
}
