package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.List;

public class Conference implements IConference {

	private int conferenceId;
	private String conferenceName;
	private final List<IDivision> divisions;

	public Conference() {
		conferenceName = null;
		divisions = new ArrayList<>();
	}

	private boolean checkIfConferenceNameIsNullOrEmpty(String conferenceName) {
		return conferenceName == null || conferenceName.trim().isEmpty();
	}

	private boolean checkIfDivisionIsNull(IDivision division) {
		return division == null;
	}

	private boolean checkIfDivisionNameAlreadyExists(String divisionName) {
		for (IDivision division : divisions) {
			if (division.getDivisionName().equals(divisionName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getConferenceId() {
		return conferenceId;
	}

	@Override
	public boolean setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
		return true;
	}

	@Override
	public String getConferenceName() {
		return conferenceName;
	}

	@Override
	public boolean setConferenceName(String conferenceName) {
		if (checkIfConferenceNameIsNullOrEmpty(conferenceName)) {
			return false;
		}
		this.conferenceName = conferenceName;
		return true;
	}

	@Override
	public List<IDivision> getDivisions() {
		return divisions;
	}

	@Override
	public boolean addDivision(IDivision division) {
		if (checkIfDivisionIsNull(division)) {
			return false;
		}
		if (checkIfConferenceNameIsNullOrEmpty(division.getDivisionName())) {
			return false;
		}
		if (checkIfDivisionNameAlreadyExists(division.getDivisionName())) {
			return false;
		}
		divisions.add(division);
		return true;
	}
}