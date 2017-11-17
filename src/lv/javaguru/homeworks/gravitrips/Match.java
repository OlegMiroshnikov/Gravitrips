package lv.javaguru.homeworks.gravitrips;

import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_ROW;
import static lv.javaguru.homeworks.gravitrips.Signs.O;
import static lv.javaguru.homeworks.gravitrips.Signs.X;

public class Match {

    private int number;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameField gameField;
    private Scanner scanner = new Scanner(System.in);

    public Match(int number) {
        this.number = number;
        setUpMatch();
    }

    @Override
    public String toString() {
        return number + " match: " +
                player1.getType() + " - " + player2.getType();
    }

    private void setUpMatch() {
        chosePlayer(X);
        chosePlayer(O);
    }

    private void chosePlayer(Signs sign) {
        int userChoice = 0;
        final int CHOICE_ITEM_HUMAN = 1;
        final int CHOICE_ITEM_COMPUTER = 2;
        System.out.print("\nChoose the " + "'" + sign + "'" + " player as called: \n"
                + " " + CHOICE_ITEM_HUMAN + " - HUMAN\n"
                + " " + CHOICE_ITEM_COMPUTER + " - COMPUTER\n" +
                ">> ");
        do {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case CHOICE_ITEM_HUMAN:
                        switch (sign) {
                            case X:
                                player1 = new HumanPlayer(sign, Players.HUMAN);
                                break;
                            case O:
                                player2 = new HumanPlayer(sign, Players.HUMAN);
                                break;
                        }
                        break;
                    case CHOICE_ITEM_COMPUTER:
                        switch (sign) {
                            case X:
                                player1 = new ComputerPlayer(sign, Players.COMPUTER);
                                break;
                            case O:
                                player2 = new ComputerPlayer(sign, Players.COMPUTER);
                                break;
                        }
                        break;
                    default:
                        System.out.println("Wrong choice! You can use only numbers from 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong choice! You can use only numbers from 1 or 2");
            }
        } while (userChoice != 1 && userChoice != 2);
    }

    public void toPlayGame() {
        boolean exitFromGame = false;
        int playerMove = 0;
        int countOfWins = 0;
        Player winnersPlayer = null;
        setUpGame();
        while (!exitFromGame) {
            if (!gameField.isGameFieldFull()) {
                whoIsNextPlayer();
                playerMove = currentPlayer.getPlayerMove(gameField);
                gameField.makePlayerMove(currentPlayer, playerMove);
                gameField.outputGameField();
                if (player1.getType() == Players.COMPUTER && player2.getType() == Players.COMPUTER) {
                    System.out.println("Press ENTER to continue...");
                    String anyKey = scanner.nextLine();
                }
                if (exitFromGame = gameField.isPlayerWon(currentPlayer.getSign())) {
                    winnersPlayer = currentPlayer;
                    countOfWins = winnersPlayer.getCountOfWins() + 1;
                    winnersPlayer.setCountOfWins(countOfWins);
                }
            } else {
                exitFromGame = true;
            }
        }
        outputGameResult(winnersPlayer);
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
            currentPlayer = player1;
        } else if (currentPlayer.getSign() == player1.getSign()) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void outputMatchResult() {
        System.out.println(this +
                ", results " + player1.getCountOfWins() + " : " + player2.getCountOfWins());

    }

    private void outputGameResult(Player winnersPlayer) {
        if (winnersPlayer != null) {
            System.out.println("Won: " + winnersPlayer);
        } else {
            System.out.println("No one won - draw");
        }
    }
}
