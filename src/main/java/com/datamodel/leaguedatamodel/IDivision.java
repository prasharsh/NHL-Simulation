package com.datamodel.leaguedatamodel;

import java.util.List;

public interface IDivision {

	int getDivisionId();

	String getDivisionName();

	boolean setDivisionId(int divisionId);

	boolean setDivisionName(String divisionName);

	List<ITeam> getTeams();

	boolean addTeam(ITeam team);
}