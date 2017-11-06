package lv.javaguru.homeworks.gravitrips;

import static lv.javaguru.homeworks.gravitrips.Gravitrips.*;

public class ComputerPlayer extends Player {

    public ComputerPlayer(TypesOfSigns sign, TypesOfPlayers type, int countOfWins) {
        super(sign, type, countOfWins);
    }

    public int getPlayerMove(GameField gameField) {
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(MAX_COL) + 1;
        } while (!gameField.isColumnNotBusy(randomNumber));
        return randomNumber;
    }
}
