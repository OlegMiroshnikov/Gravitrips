package lv.javaguru.homeworks.gravitrips;

import java.util.Random;
import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.Enum.*;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;

public class ComputerPlayer extends Player {

    public ComputerPlayer(int playerNumber, char playerSign, TypesOfPlayers playerType, int countOfWins) {
        super(playerNumber, playerSign, playerType, countOfWins);
    }

    @Override
    public int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField) {
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(MAX_COL) + 1;
        } while (!gameField.onColumnIsNotBusy(randomNumber));
        return randomNumber;
    }

}
