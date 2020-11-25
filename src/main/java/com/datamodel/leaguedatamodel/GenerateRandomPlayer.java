package com.datamodel.leaguedatamodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static com.datamodel.leaguedatamodel.Constants.*;


public class GenerateRandomPlayer implements IGenerateRandomPlayer {

    private ArrayList<IPlayer> newPlayers;

    private String[] firstName = {"Walter","John","Prashant","Hardik","Rashita","Mary","Fred","Mohammed","Raghav",
            "Bonnie","Tami","Chris","Pat","Sammy","Abraham","Tina","Nancy","Roger","Mike","Rob","Zongming","Wen",
            "Don","Rahul","Sai","Prabhjot","Mozhgan","Shakuntala","Karan","Jimmy","Abioye","Hanne","Krista","Katlego",
            "Motya","Nell","Barta","Othman","Adaline","Eva","Patroklos","Vlasta","Borislav","Stanko","Andra"};

    private String[] lastName = {"Price","Cobb","Blanchard","Faulkner","Hendricks","Downs","Beard","Lowery",
            "Everett","Boone","Rasmussen","Estrada","Francis","Bowman","Griffin","Fox","Delgado","Hickman","Joyce",
            "Mckay","Gutierrez","Robles","Thompson","Cole","Parsons","Olson","Hill","Petty","Osborne","Mccormick",
            "Avila","Mullen","Mcfarland","Mays","Preston","Good","Gordon","Hooper","Ho","Mooney"};

    public GenerateRandomPlayer() {
        newPlayers = new ArrayList<>();
    }

    public ArrayList<IPlayer> getRandomPlayers(int totalPlayers,String currentDate) {
        int forward = (int) (totalPlayers * 0.5);
        int defense = (int) (totalPlayers * 0.4);
        int goalie = totalPlayers - (forward + defense);
        for (int i = 0; i < forward; i++) {
            String playerName = generatePlayerName();
            int birthDay = generatePlayerBirthDay();
            int birthMonth = generatePlayerBirthMonth();
            int birthYear = generatePlayerBirthYear(currentDate);
            IPlayer newPlayer = new Player();
            newPlayer.setPlayerName(playerName);
            newPlayer.setPlayerPosition(FORWARD);
            newPlayer.setPlayerBirthDay(birthDay);
            newPlayer.setPlayerBirthMonth(birthMonth);
            newPlayer.setPlayerBirthYear(birthYear);
            newPlayer.setPlayerSkating(generatePlayerSkating(FORWARD));
            newPlayer.setPlayerChecking(generatePlayerChecking(FORWARD));
            newPlayer.setPlayerShooting(generatePlayerShooting(FORWARD));
            newPlayer.setPlayerSaving(generatePlayerSaving(FORWARD));
            newPlayer.calculatePlayerAge(LocalDate.of(birthYear,birthMonth,birthDay),LocalDate.parse(currentDate));
            newPlayers.add(newPlayer);
        }
        for (int j = 0; j < defense; j++) {
            String playerName = generatePlayerName();
            int birthDay = generatePlayerBirthDay();
            int birthMonth = generatePlayerBirthMonth();
            int birthYear = generatePlayerBirthYear(currentDate);
            IPlayer newPlayer = new Player();
            newPlayer.setPlayerName(playerName);
            newPlayer.setPlayerPosition(DEFENSE);
            newPlayer.setPlayerBirthDay(birthDay);
            newPlayer.setPlayerBirthMonth(birthMonth);
            newPlayer.setPlayerBirthYear(birthYear);
            newPlayer.setPlayerSkating(generatePlayerSkating(DEFENSE));
            newPlayer.setPlayerChecking(generatePlayerChecking(DEFENSE));
            newPlayer.setPlayerShooting(generatePlayerShooting(DEFENSE));
            newPlayer.setPlayerSaving(generatePlayerSaving(DEFENSE));
            newPlayer.calculatePlayerAge(LocalDate.of(birthYear,birthMonth,birthDay),LocalDate.parse(currentDate));
            newPlayers.add(newPlayer);
        }
        for (int k = 0; k < goalie; k++) {
            String playerName = generatePlayerName();
            int birthDay = generatePlayerBirthDay();
            int birthMonth = generatePlayerBirthMonth();
            int birthYear = generatePlayerBirthYear(currentDate);
            IPlayer newPlayer = new Player();
            newPlayer.setPlayerName(playerName);
            newPlayer.setPlayerPosition(GOALIE);
            newPlayer.setPlayerBirthDay(birthDay);
            newPlayer.setPlayerBirthMonth(birthMonth);
            newPlayer.setPlayerBirthYear(birthYear);
            newPlayer.setPlayerSkating(generatePlayerSkating(GOALIE));
            newPlayer.setPlayerChecking(generatePlayerChecking(GOALIE));
            newPlayer.setPlayerShooting(generatePlayerShooting(GOALIE));
            newPlayer.setPlayerSaving(generatePlayerSaving(GOALIE));
            newPlayer.calculatePlayerAge(LocalDate.of(birthYear,birthMonth,birthDay),LocalDate.parse(currentDate));
            newPlayers.add(newPlayer);
        }
        return newPlayers;
    }


    public String generatePlayerName() {
        int playerFirstName = new Random().nextInt(firstName.length);
        int playerLastName = new Random().nextInt(lastName.length);
        String playerName = firstName[playerFirstName] + " " + lastName[playerLastName];
        return playerName;
    }


    public int generatePlayerSkating(String position) {
        int playerSkating;
        if (position.equals(FORWARD)) {
            playerSkating = new Random().nextInt(21 - 12) + 12;
        } else if (position.equals(DEFENSE)) {
            playerSkating = new Random().nextInt(20 - 10) + 10;
        } else {
            playerSkating = new Random().nextInt(16 - 8) + 8;
        }
        return playerSkating;
    }

    public int generatePlayerShooting(String position) {
        int playerShooting;
        if (position.equals(FORWARD)) {
            playerShooting = new Random().nextInt(21 - 12) + 12;
        } else if (position.equals(DEFENSE)) {
            playerShooting = new Random().nextInt(19 - 9) + 9;
        } else {
            playerShooting = new Random().nextInt(11 - 1) + 1;
        }
        return playerShooting;
    }

    public int generatePlayerChecking(String position) {
        int playerChecking;
        if (position.equals(FORWARD)) {
            playerChecking = new Random().nextInt(18 - 9) + 9;
        } else if (position.equals(DEFENSE)) {
            playerChecking = new Random().nextInt(21 - 12) + 12;
        } else {
            playerChecking = new Random().nextInt(13 - 1) + 1;
        }
        return playerChecking;
    }

    public int generatePlayerSaving(String position) {
        int playerSaving;
        if (position.equals(FORWARD)) {
            playerSaving = new Random().nextInt(8 - 1) + 1;
        } else if (position.equals(DEFENSE)) {
            playerSaving = new Random().nextInt(13 - 1) + 1;
        } else {
            playerSaving = new Random().nextInt(21 - 12) + 12;
        }
        return playerSaving;
    }

    public int generatePlayerBirthDay() {
        int birthDay = new Random().nextInt(MAXIMUM_DAY - MINIMUM) + MINIMUM;
        return birthDay;
    }

    public int generatePlayerBirthMonth() {
        int birthMonth = new Random().nextInt(MAXIMUM_MONTH - MINIMUM) + MINIMUM;
        return birthMonth;
    }

    public int generatePlayerBirthYear(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        int[] years = new int[4];
        for (int i = 0; i < 4; i++) {
            years[i] = year - (MINIMUM_AGE + i);
        }
        int birthYear = new Random().nextInt(years.length);
        return years[birthYear];
    }
}
