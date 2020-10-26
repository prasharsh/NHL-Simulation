package trading;

import g4dhl.FreeAgent;
import g4dhl.IFreeAgent;
import g4dhl.IPlayer;

public class PlayerToFreeAgent {

    private IFreeAgent freeAgent;
    private IPlayer player;

    public PlayerToFreeAgent(IPlayer player){
        this.player = player;
        freeAgent = new FreeAgent();
        setFreeAgentName();
        setFreeAgentPosition();
        setFreeAgentAge();
        setFreeAgentSkating();
        setFreeAgentShooting();
        setFreeAgentChecking();
        setFreeAgentSaving();
    }

    public IFreeAgent getFreeAgent(){
        return freeAgent;
    }

    private void setFreeAgentName(){
        freeAgent.setFreeAgentName(player.getPlayerName());
    }

    private void setFreeAgentPosition(){
        freeAgent.setFreeAgentPosition(player.getPlayerPosition());
    }

    private void setFreeAgentAge(){
        freeAgent.setFreeAgentAge(player.getPlayerAge());
    }

    private void setFreeAgentSkating(){
        freeAgent.setFreeAgentSkating(player.getPlayerSkating());
    }

    private void setFreeAgentShooting(){
        freeAgent.setFreeAgentShooting(player.getPlayerShooting());
    }

    private void setFreeAgentChecking(){
        freeAgent.setFreeAgentChecking(player.getPlayerChecking());
    }

    private void setFreeAgentSaving(){
        freeAgent.setFreeAgentSaving(player.getPlayerSaving());
    }
}
