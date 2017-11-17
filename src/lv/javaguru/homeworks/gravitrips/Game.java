package lv.javaguru.homeworks.gravitrips;

import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_ROW;

public class Game {

    private int number;
    private Match match;
    private Player currentPlayer;
    private GameField gameField;
    private Scanner scanner = new Scanner(System.in);

    public Game(int number, Match match) {
        this.number = number;
        this.match = match;
        setUpGame();
        currentPlayer = match.getLastPlayer();
    }

    @Override
    public String toString() {
        return number + " game";
    }

    public void toPlayGame() {
        boolean exitFromGame = false;
        Player winnersPlayer = null;
        while (!exitFromGame) {
            if (!gameField.isGameFieldFull()) {
                whoIsNextPlayer();
                int playerMove = currentPlayer.getPlayerMove(gameField);
                gameField.makePlayerMove(currentPlayer, playerMove);
                gameField.outputGameField();
                if (match.getPlayer1().getType() == Players.COMPUTER && match.getPlayer2().getType() == Players.COMPUTER) {
                    System.out.println("Press ENTER to continue...");
                    String anyKey = scanner.nextLine();
                }
                if (exitFromGame = gameField.isPlayerWon(currentPlayer.getSign())) {
                    winnersPlayer = currentPlayer;
                    int countOfWins = winnersPlayer.getCountOfWins() + 1;
                    winnersPlayer.setCountOfWins(countOfWins);
                }
            } else {
                exitFromGame = true;
            }
        }
        outputGameResult(winnersPlayer);
        match.setLastPlayer(currentPlayer);
    }

    private void setUpGame() {
        Signs[][] array = new Signs[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                array[i][j] = Signs.EMPTY;
            }
        }
        gameField = new GameField(array);
    }

    private void whoIsNextPlayer() {
        if (currentPlayer == null) {
            currentPlayer = match.getPlayer1();
        } else if (currentPlayer.getSign() == match.getPlayer1().getSign()) {
            currentPlayer = match.getPlayer2();
        } else {
            currentPlayer = match.getPlayer1();
        }
    }

    private void outputGameResult(Player winnersPlayer) {
        if (winnersPlayer != null) {
            System.out.println("Won: " + winnersPlayer);
        } else {
            System.out.println("No one won - draw");
        }
    }
}
