package g4dhl;

public interface IFreeAgent {

    int getFreeAgentId();
    String getFreeAgentName();

    boolean setFreeAgentId(int freeAgentId);
    boolean setFreeAgentName(String freeAgentName);

    String getFreeAgentPosition();
    boolean setFreeAgentPosition(String freeAgentPosition);
}
