package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.datamodel.gameplayconfig.InjuryConfig;
import org.json.simple.JSONObject;

public class InjuryDB implements IInjuryDB {

    @Override
    public void loadInjury(JSONObject injuryObject, IGamePlayConfig gameplayConfig) {
        IInjuryConfig injury = new InjuryConfig();
        injury.setInjuryId((int) (long) injuryObject.get("injuryId"));
        injury.setRandomInjuryChance((float) (double) injuryObject.get("randomInjuryChance"));
        injury.setInjuryDaysLow((int) (long) injuryObject.get("injuryDaysLow"));
        injury.setInjuryDaysHigh((int) (long) injuryObject.get("injuryDaysHigh"));
        gameplayConfig.setInjury(injury);
    }
}
