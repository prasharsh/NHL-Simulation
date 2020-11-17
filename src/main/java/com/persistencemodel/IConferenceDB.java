package com.persistencemodel;

import com.datamodel.leaguedatamodel.ILeague;
import org.json.simple.JSONArray;

public interface IConferenceDB {
    void loadConferences(JSONArray conferencesArray, ILeague league);
}
