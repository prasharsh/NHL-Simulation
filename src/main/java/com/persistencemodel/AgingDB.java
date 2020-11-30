package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.AgingConfig;
import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.gameplayconfig.IGamePlayConfig;

public class AgingDB implements IAgingDB {

    @Override
    public void loadAging(JSONObject agingObject, IGamePlayConfig gameplayConfig) {
        IAgingConfig aging = new AgingConfig();
        aging.setAgingId((int) (long) agingObject.get("agingId"));
        aging.setAverageRetirementAge((int) (long) agingObject.get("averageRetirementAge"));
        aging.setAgingId((int) (long) agingObject.get("maximumAge"));
        aging.setStatDecayChance((int) (long) agingObject.get("statDecayChance"));
        gameplayConfig.setAging(aging);
    }
}
