package lv.javaguru.homeworks.gravitrips;

import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.Gravitrips.*;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(Signs sign, Players type) {
        super(sign, type);
    }

    public int getPlayerMove(GameField gameField) {
        int columnNumber = 0;
        boolean isInputOK = false;
        while (!isInputOK) {
            try {
                System.out.println("Player " + getSign().getNumber() + " as " + getType() + ", please, enter a column number:");
                columnNumber = Integer.valueOf(scanner.nextLine());
                if (!(isInputOK = (columnNumber >= 1 && columnNumber <= MAX_COL))) {
                    System.out.println("You can use only numbers from 1 to " + MAX_COL + ", choose another column.");
                } else if (!(isInputOK = gameField.isColumnNotBusy(columnNumber))) {
                    System.out.println("Column " + columnNumber + " is busy, choose another column.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You can use only numbers from 1 to " + MAX_COL);
            }
        }
        return columnNumber;
    }
}

