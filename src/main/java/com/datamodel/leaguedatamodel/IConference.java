package com.datamodel.leaguedatamodel;
import java.util.List;

public interface IConference {

	int getConferenceId();

	boolean setConferenceId(int conferenceId);

	String getConferenceName();

	boolean setConferenceName(String conferenceName);

	List<IDivision> getDivisions();

	boolean addDivision(IDivision division);
}