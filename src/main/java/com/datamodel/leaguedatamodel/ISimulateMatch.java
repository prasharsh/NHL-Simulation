package com.datamodel.leaguedatamodel;

public interface ISimulateMatch {

	public boolean simulateMatchResult(ITeam team, double teamStrength, ITeam opponentTeam, double opponentTeamStrength,float randomWinChance, IGame game);

}
