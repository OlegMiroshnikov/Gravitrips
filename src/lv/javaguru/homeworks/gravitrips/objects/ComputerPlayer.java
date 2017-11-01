package lv.javaguru.homeworks.gravitrips.objects;

import java.util.Random;
import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.objects.Gravitrips.*;

public class ComputerPlayer extends Player {

    public ComputerPlayer(int number, TypesOfSigns sign, TypesOfPlayers type, int countOfWins) {
        super(number, sign, type, countOfWins);
    }

    public int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField) {
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(MAX_COL) + 1;
        } while (!gameField.columnIsNotBusy(randomNumber));
        return randomNumber;
    }
}
