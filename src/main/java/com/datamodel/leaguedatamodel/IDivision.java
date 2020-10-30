package com.datamodel.leaguedatamodel;

import java.util.ArrayList;

public interface IDivision {

    int getDivisionId();

    String getDivisionName();

    boolean setDivisionId(int divisionId);

    boolean setDivisionName(String divisionName);

    ArrayList<ITeam> getTeams();

    boolean addTeam(ITeam team);
}
