package trading;

import g4dhl.FreeAgent;
import g4dhl.IFreeAgent;
import g4dhl.IPlayer;

public class PlayerToFreeAgent {

	private IFreeAgent freeAgent;
	private IPlayer player;

	public PlayerToFreeAgent(IPlayer player) {
		this.player = player;
		freeAgent = new FreeAgent();
		setFreeAgentName();
		setFreeAgentPosition();
		setFreeAgentAgeYear();
		setFreeAgentAgeDays();
		setFreeAgentSkating();
		setFreeAgentShooting();
		setFreeAgentChecking();
		setFreeAgentSaving();
		setFreeAgentIsInjured();
		setFreeAgentWasInjured();
		setRecoveryDate();
	}

	public IFreeAgent getFreeAgent() {
		return freeAgent;
	}

	private void setFreeAgentName() {
		freeAgent.setFreeAgentName(player.getPlayerName());
	}

	private void setFreeAgentPosition() {
		freeAgent.setFreeAgentPosition(player.getPlayerPosition());
	}

	private void setFreeAgentAgeYear() {
		freeAgent.setFreeAgentAgeYear(player.getPlayerAgeYear());
	}

	private void setFreeAgentAgeDays() {
		freeAgent.setFreeAgentAgeDays(player.getPlayerAgeDays());
	}

	private void setFreeAgentSkating() {
		freeAgent.setFreeAgentSkating(player.getPlayerSkating());
	}

	private void setFreeAgentShooting() {
		freeAgent.setFreeAgentShooting(player.getPlayerShooting());
	}

	private void setFreeAgentChecking() {
		freeAgent.setFreeAgentChecking(player.getPlayerChecking());
	}

	private void setFreeAgentSaving() {
		freeAgent.setFreeAgentSaving(player.getPlayerSaving());
	}

	private void setFreeAgentIsInjured() {
		freeAgent.setFreeAgentIsInjured(player.isPlayerInjured());
	}

	private void setFreeAgentWasInjured() {
		freeAgent.setFreeAgentWasInjured(player.wasPlayerInjured());
	}

	private void setRecoveryDate() {
		freeAgent.setRecoveryDate(player.getRecoveryDate());
	}
}
