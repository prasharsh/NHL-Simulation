package g4dhl;

public interface IFreeAgent {

	int getFreeAgentId();

	String getFreeAgentName();

	String getFreeAgentPosition();

	int getFreeAgentAge();

	int getFreeAgentSkating();

	int getFreeAgentShooting();

	int getFreeAgentChecking();

	int getFreeAgentSaving();

	boolean setFreeAgentId(int freeAgentId);

	boolean setFreeAgentName(String freeAgentName);

	boolean setFreeAgentPosition(String freeAgentPosition);

	boolean setFreeAgentAge(int freeAgentAge);

	boolean setFreeAgentSkating(int freeAgentSkating);

	boolean setFreeAgentShooting(int freeAgentShooting);

	boolean setFreeAgentChecking(int freeAgentChecking);

	boolean setFreeAgentSaving(int freeAgentSaving);
}
