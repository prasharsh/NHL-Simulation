package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public interface IInjuryDB {
	void loadInjury(JSONObject injuryObject, IGamePlayConfig gameplayConfig);
}
