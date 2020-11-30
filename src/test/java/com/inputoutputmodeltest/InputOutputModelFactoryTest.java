package com.inputoutputmodeltest;
import com.inputoutputmodel.CreateTeamUI;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.ICreateTeamUI;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IDisplayToUser;
import com.inputoutputmodel.IDisplayTradingOffers;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.ITrainingUI;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import com.inputoutputmodel.PropertyLoader;
import com.inputoutputmodel.TrainingUI;

public class InputOutputModelFactoryTest extends InputOutputModelAbstractFactory {

    private ICreateTeamUI createTeamUI = null;
    private IDisplayRoaster displayRoaster = null;
    private IDisplayToUser displayToUser = null;
    private IDisplayTradingOffers displayTradingOffers = null;
    private IPropertyLoader propertyLoader = null;
    private ITrainingUI trainingUI = null;

    @Override
    public ICreateTeamUI createTeamUI() {
        if (createTeamUI == null) {
            createTeamUI = new CreateTeamUI();
        }
        return createTeamUI;
    }

    @Override
    public IDisplayRoaster createDisplayRoster() {
        if (displayRoaster == null) {
            displayRoaster = new DisplayRoster();
        }
        return displayRoaster;
    }

    @Override
    public IDisplayToUser createDisplayToUser() {
        if (displayToUser == null) {
            displayToUser = new DisplayToUser();
        }
        return displayToUser;
    }

    @Override
    public IDisplayTradingOffers createDisplayTradingOffers() {
        if (displayTradingOffers == null) {
            displayTradingOffers = new DisplayTradingOffersMock();
        }
        return displayTradingOffers;
    }

    @Override
    public IPropertyLoader createPropertyLoader() {
        if (propertyLoader == null) {
            propertyLoader = new PropertyLoader();
        }
        return propertyLoader;
    }

    @Override
    public ITrainingUI createTrainingUI() {
        if (trainingUI == null) {
            trainingUI = new TrainingUI();
        }
        return trainingUI;
    }
}
