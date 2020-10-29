package g4dhl;

import java.sql.Date;

public interface IFreeAgent {

	int getFreeAgentId();

	String getFreeAgentName();

	String getFreeAgentPosition();

	int getFreeAgentAgeYear();

	int getFreeAgentAgeDays();

	int getFreeAgentSkating();

	int getFreeAgentShooting();

	int getFreeAgentChecking();

	int getFreeAgentSaving();

	double getFreeAgentStrength();

	void ageFreeAgent(int days);

	boolean setFreeAgentId(int freeAgentId);

	boolean setFreeAgentName(String freeAgentName);

	boolean setFreeAgentPosition(String freeAgentPosition);

	boolean setFreeAgentAgeYear(int freeAgentAgeYear);

	boolean setFreeAgentAgeDays(int freeAgentAgeDays);

	boolean setFreeAgentSkating(int freeAgentSkating);

	boolean setFreeAgentShooting(int freeAgentShooting);

	boolean setFreeAgentChecking(int freeAgentChecking);

	boolean setFreeAgentSaving(int freeAgentSaving);

	boolean setFreeAgentIsInjured(boolean freeAgentIsInjured);

	boolean setFreeAgentWasInjured(boolean freeagentWasInjured);

	boolean setRecoveryDate(Date recoveryDate);

	Date getRecoveryDate();

	boolean isFreeAgentInjured();

	boolean wasFreeAgentInjured();

}
