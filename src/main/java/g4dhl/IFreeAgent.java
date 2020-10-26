package g4dhl;

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

	boolean setFreeAgentId(int freeAgentId);

	boolean setFreeAgentName(String freeAgentName);

	boolean setFreeAgentPosition(String freeAgentPosition);

	boolean setFreeAgentAgeYear(int freeAgentAgeYear);

	boolean setFreeAgentAgeDays(int freeAgentAgeDays);

	boolean setFreeAgentSkating(int freeAgentSkating);

	boolean setFreeAgentShooting(int freeAgentShooting);

	boolean setFreeAgentChecking(int freeAgentChecking);

	boolean setFreeAgentSaving(int freeAgentSaving);
}
