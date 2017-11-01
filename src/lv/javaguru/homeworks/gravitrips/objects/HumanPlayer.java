package lv.javaguru.homeworks.gravitrips.objects;

import java.util.Random;
import java.util.Scanner;
import static lv.javaguru.homeworks.gravitrips.objects.Gravitrips.*;

public class HumanPlayer extends Player {

    public HumanPlayer(int number, TypesOfSigns sign, TypesOfPlayers type, int countOfWins) {
        super(number, sign, type, countOfWins);
    }

    public int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField) {
        int columnNumber = 0;
        boolean inputIsOK = false;
        while (!inputIsOK) {
            try {
                System.out.println("Player " + getNumber() + " as " + getType() + ", please, enter a column number:");
                columnNumber = Integer.valueOf(scanner.nextLine());
                if (!(inputIsOK = (columnNumber >= 1 && columnNumber <= MAX_COL))) {
                    System.out.println("You can use only numbers from 1 to " + MAX_COL + ", choose another column.");
                } else if (!(inputIsOK = gameField.columnIsNotBusy(columnNumber))) {
                    System.out.println("Column " + columnNumber + " is busy, choose another column.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You can use only numbers from 1 to " + MAX_COL);
            }
        }
        return columnNumber;
    }
}

