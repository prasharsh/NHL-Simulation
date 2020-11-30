package com.persistencemodel;

import org.json.simple.JSONArray;

import com.datamodel.leaguedatamodel.IConference;

public interface IDivisionDB {
    void loadDivisions(JSONArray divisionArray, IConference conference);
}
