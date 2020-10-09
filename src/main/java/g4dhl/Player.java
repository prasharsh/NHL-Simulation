package g4dhl;

public class Player implements IPlayer {

	private int playerId;
	private String playerName;
	private String playerPosition;
	private boolean playerCaptain;

	public Player() {
		playerName = null;
		playerPosition = null;
		playerCaptain = false;
	}

	private boolean checkIfPlayerNameIsNullOrEmpty(String playerName) {
		return playerName == null || playerName.trim().isEmpty();
	}

	private boolean checkIfPlayerPositionIsNullOrEmpty(String playerName) {
		return playerName == null || playerName.trim().isEmpty();
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public boolean setPlayerName(String playerName) {
		if(checkIfPlayerNameIsNullOrEmpty(playerName)) return false;
		this.playerName = playerName;
		return true;
	}

	@Override
	public boolean setPlayerId(int playerId) {
		this.playerId = playerId;
		return true;
	}

	@Override
	public String getPlayerPosition() {
		return playerPosition;
	}

	@Override
	public boolean setPlayerPosition(String playerPosition) {
		if(checkIfPlayerPositionIsNullOrEmpty(playerName)) return false;
		this.playerPosition = playerPosition;
		return true;
	}

	@Override
	public boolean isPlayerCaptain() {
		return playerCaptain;
	}

	@Override
	public boolean setPlayerCaptain(boolean playerCaptain) {
		this.playerCaptain = playerCaptain;
		return true;
	}
}
