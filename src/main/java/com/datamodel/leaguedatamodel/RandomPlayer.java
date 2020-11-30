package com.datamodel.leaguedatamodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class RandomPlayer implements IRandomPlayer {

	final String FORWARD = "forward";
	final String DEFENSE = "defense";
	final String GOALIE = "goalie";
	final int MAXIMUM_DAY = 29;
	final int MAXIMUM_MONTH = 13;
	final int MINIMUM_AGE = 18;
	final int MINIMUM = 1;
	private final List<IPlayer> newPlayers;
	private final String[] firstName = {"Walter", "John", "Prashant", "Hardik", "Rashita", "Mary", "Fred", "Mohammed",
			"Raghav", "Bonnie", "Tami", "Chris", "Pat", "Sammy", "Abraham", "Tina", "Nancy", "Roger", "Mike", "Rob",
			"Zongming", "Wen", "Don", "Rahul", "Sai", "Prabhjot", "Mozhgan", "Shakuntala", "Karan", "Jimmy", "Abioye",
			"Hanne", "Krista", "Katlego", "Motya", "Nell", "Barta", "Othman", "Adaline", "Eva", "Patroklos", "Vlasta",
			"Borislav", "Stanko", "Andra"};

	private final String[] lastName = {"Price", "Cobb", "Blanchard", "Faulkner", "Hendricks", "Downs", "Beard",
			"Lowery", "Everett", "Boone", "Rasmussen", "Estrada", "Francis", "Bowman", "Griffin", "Fox", "Delgado",
			"Hickman", "Joyce", "Mckay", "Gutierrez", "Robles", "Thompson", "Cole", "Parsons", "Olson", "Hill", "Petty"
			, "Osborne", "Mccormick", "Avila", "Mullen", "Mcfarland", "Mays", "Preston", "Good", "Gordon", "Hooper",
			"Ho", "Mooney"};

	public RandomPlayer() {
		newPlayers = new ArrayList<>();
	}

	@Override
	public List<IPlayer> getRandomPlayers(int totalPlayers, String currentDate) {
		int forward = (int) (totalPlayers * 0.5);
		int defense = (int) (totalPlayers * 0.4);
		int goalie = totalPlayers - (forward + defense);
		generatePlayer(currentDate, forward, FORWARD);
		generatePlayer(currentDate, defense, DEFENSE);
		generatePlayer(currentDate, goalie, GOALIE);
		newPlayers.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength).reversed());
		return newPlayers;
	}

	private void generatePlayer(String currentDate, int count, String position) {
		for(int i = 0; i < count; i++) {
			String playerName = generatePlayerName();
			int birthDay = generatePlayerBirthDay();
			int birthMonth = generatePlayerBirthMonth();
			int birthYear = generatePlayerBirthYear(currentDate);
			LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.instance();
			IPlayer newPlayer = dataModelFactory.createPlayer();
			newPlayer.setPlayerName(playerName);
			newPlayer.setPlayerPosition(position);
			newPlayer.setPlayerBirthDay(birthDay);
			newPlayer.setPlayerBirthMonth(birthMonth);
			newPlayer.setPlayerBirthYear(birthYear);
			newPlayer.setPlayerSkating(generatePlayerSkating(position));
			newPlayer.setPlayerChecking(generatePlayerChecking(position));
			newPlayer.setPlayerShooting(generatePlayerShooting(position));
			newPlayer.setPlayerSaving(generatePlayerSaving(position));
			newPlayer.calculatePlayerAge(LocalDate.of(birthYear, birthMonth, birthDay), LocalDate.parse(currentDate));
			newPlayers.add(newPlayer);
		}
	}

	@Override
	public String generatePlayerName() {
		int playerFirstName = new Random().nextInt(firstName.length);
		int playerLastName = new Random().nextInt(lastName.length);
		String playerName = firstName[playerFirstName] + " " + lastName[playerLastName];
		return playerName;
	}

	@Override
	public int generatePlayerSkating(String position) {
		int playerSkating;
		if(position.equals(FORWARD)) {
			playerSkating = new Random().nextInt(21 - 12) + 12;
		} else if(position.equals(DEFENSE)) {
			playerSkating = new Random().nextInt(20 - 10) + 10;
		} else {
			playerSkating = new Random().nextInt(16 - 8) + 8;
		}
		return playerSkating;
	}

	@Override
	public int generatePlayerShooting(String position) {
		int playerShooting;
		if(position.equals(FORWARD)) {
			playerShooting = new Random().nextInt(21 - 12) + 12;
		} else if(position.equals(DEFENSE)) {
			playerShooting = new Random().nextInt(19 - 9) + 9;
		} else {
			playerShooting = new Random().nextInt(11 - 1) + 1;
		}
		return playerShooting;
	}

	@Override
	public int generatePlayerChecking(String position) {
		int playerChecking;
		if(position.equals(FORWARD)) {
			playerChecking = new Random().nextInt(18 - 9) + 9;
		} else if(position.equals(DEFENSE)) {
			playerChecking = new Random().nextInt(21 - 12) + 12;
		} else {
			playerChecking = new Random().nextInt(13 - 1) + 1;
		}
		return playerChecking;
	}

	@Override
	public int generatePlayerSaving(String position) {
		int playerSaving;
		if(position.equals(FORWARD)) {
			playerSaving = new Random().nextInt(8 - 1) + 1;
		} else if(position.equals(DEFENSE)) {
			playerSaving = new Random().nextInt(13 - 1) + 1;
		} else {
			playerSaving = new Random().nextInt(21 - 12) + 12;
		}
		return playerSaving;
	}

	@Override
	public int generatePlayerBirthDay() {
		int birthDay = new Random().nextInt(MAXIMUM_DAY - MINIMUM) + MINIMUM;
		return birthDay;
	}

	@Override
	public int generatePlayerBirthMonth() {
		int birthMonth = new Random().nextInt(MAXIMUM_MONTH - MINIMUM) + MINIMUM;
		return birthMonth;
	}

	@Override
	public int generatePlayerBirthYear(String date) {
		int year = Integer.parseInt(date.split("-")[0]);
		int[] years = new int[4];
		for(int i = 0; i < 4; i++) {
			years[i] = year - (MINIMUM_AGE + i);
		}
		int birthYear = new Random().nextInt(years.length);
		return years[birthYear];
	}
}
