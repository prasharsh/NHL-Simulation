package com.datamodel.leaguedatamodel;

public interface ISimulateMatch {

	boolean simulateMatchResult(ITeam team, double teamStrength, ITeam opponentTeam, double opponentTeamStrength,float randomWinChance, IGame game);
}