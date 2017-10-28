package lv.javaguru.homeworks.gravitrips;

import java.util.Random;
import java.util.Scanner;
import static lv.javaguru.homeworks.gravitrips.Enum.*;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;

public class HumanPlayer extends Player {

    public HumanPlayer(int playerNumber, char playerSign, TypesOfPlayers playerType, int countOfWins) {
        super(playerNumber, playerSign, playerType, countOfWins);
    }

    @Override
    public int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField) {
        int columnNumber = 0;
        boolean onInputIsOK = false;
        while (!onInputIsOK) {
            try {
                System.out.println("Player " + getPlayerNumber() + " as " + getPlayerType() + ", please, enter a column number:");
                columnNumber = Integer.valueOf(scanner.nextLine());
                if (!(onInputIsOK = (columnNumber >= 1 && columnNumber <= MAX_COL))) {
                    System.out.println("You can use only numbers from 1 to " + MAX_COL + ", choose another column.");
                } else if (!(onInputIsOK = gameField.onColumnIsNotBusy(columnNumber))) {
                    System.out.println("Column " + columnNumber + " is busy, choose another column.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You can use only numbers from 1 to " + MAX_COL);
            }
        }
        return columnNumber;
    }
}

