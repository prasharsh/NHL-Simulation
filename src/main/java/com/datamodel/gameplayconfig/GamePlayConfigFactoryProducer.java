package com.datamodel.gameplayconfig;
import static com.datamodel.gameplayconfig.Constants.IMPORT_TYPE_JSON;

public class GamePlayConfigFactoryProducer {

    private static IGamePlayConfigAbstractFactory factory;

    public static IGamePlayConfigAbstractFactory getFactory(){
        if (factory == null){
            GetPropertyValues properties = new GetPropertyValues();
            String importType = properties.loadProperties();
            if (importType.equals(IMPORT_TYPE_JSON)){
                factory = new GamePlayConfigFactory();
            }
        }
        return factory;
    }
}
