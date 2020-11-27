package com.datamodel.gameplayconfig;

public class GeneralManagerConfig implements IGeneralManagerConfig {

    private float shrewd;
    private float normal;
    private float gambler;

    @Override
    public float getShrewd() {
        return shrewd;
    }

    @Override
    public boolean setShrewd(float shrewd) {
        this.shrewd = shrewd;
        return true;
    }

    @Override
    public float getNormal() {
        return normal;
    }

    @Override
    public boolean setNormal(float normal) {
        this.normal = normal;
        return true;
    }

    @Override
    public float getGambler() {
        return gambler;
    }

    @Override
    public boolean setGambler(float gambler) {
        this.gambler = gambler;
        return true;
    }
}
