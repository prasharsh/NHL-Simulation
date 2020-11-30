package com.persistencemodel;

import org.json.simple.JSONArray;

import com.datamodel.leaguedatamodel.ILeague;

public interface IConferenceDB {
    void loadConferences(JSONArray conferencesArray, ILeague league);
}
