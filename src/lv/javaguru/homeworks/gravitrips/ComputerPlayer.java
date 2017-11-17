package lv.javaguru.homeworks.gravitrips;

import java.util.Random;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.*;

public class ComputerPlayer extends Player {

    private Random randomGenerator = new Random();

    public ComputerPlayer(Signs sign, Players type) {
        super(sign, type);
    }

    public int getPlayerMove(GameField gameField) {
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(MAX_COL) + 1;
        } while (!gameField.isColumnNotBusy(randomNumber));
        return randomNumber;
    }
}
