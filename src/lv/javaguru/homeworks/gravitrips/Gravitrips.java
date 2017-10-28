package lv.javaguru.homeworks.gravitrips;

//import lv.javaguru.homeworks.gravitrips.GameField.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.Enum.*;

public class Gravitrips {
    static final int MAX_ROW = 6;
    static final int MAX_COL = 7;

    GameField gameField;
    ArrayList<Player> playerList = new ArrayList(2);
    Scanner scanner = new Scanner(System.in);
    Random randomGenerator = new Random();
    private int currentPlayerNumber;

    public static void main(String[] args) {
        boolean onExitFromGravitrips = false;
        boolean onExitFromMatch = false;
        int countOfGames = 0;
        int countOfMatches = 0;
        Gravitrips gravitrips = new Gravitrips();
        gravitrips.outputInstructionsToUser();
        while (!onExitFromGravitrips) {
            countOfMatches++;
            System.out.println(countOfMatches + " match");
            gravitrips.setUpMatch(countOfMatches);
            countOfGames = 0;
            onExitFromMatch = false;
            while (!onExitFromMatch) {
                countOfGames++;
                System.out.println(countOfGames + " game");
                gravitrips.setUpGame();
                gravitrips.toPlayGame();
                onExitFromMatch = gravitrips.onExitFromMatch();
            }
            gravitrips.OutputMatchResult(countOfMatches);
            onExitFromGravitrips = gravitrips.onExitFromGravitrips();
        }
        gravitrips.finishGravitrips();
    }

    private void outputInstructionsToUser() {
        System.out.println("");
        System.out.println(
                "  Welcome to the game GRAVITRIPS (Four in a Line)             \n" +
                        "       Some assumptions and limitations:                      \n\n" +

                        "- размер игрового поля 6(rows) х 7(columns)                   \n" +
                        "- количество матчей произвольное                              \n" +
                        "- количество игр в матче произвольное                         \n" +
                        "- межлу собой играют 2 игрока - HUMAN & COMPUTER              \n" +
                        "- в рамках одного матча типы игроков не меняются              \n" +
                        "- 1-й игрок в качестве фишек использует 'Х'                   \n" +
                        "- 2-й игрок в качестве фишек использует 'O'                   \n" +
                        "- в 1-й игре матча первым ходит 1-й игрок, далее по очереди   \n" +
                        "  в течении всего матча                                       \n" +
                        "- используется простейшая стратегия для хода компюьтера, -    \n" +
                        "  его ход генерируется генератором случайных чисел.           ");
        System.out.println("Press ENTER to continue...");
        String aneKey = scanner.nextLine();
    }

    private void toPlayGame() {
        boolean onExitFromGame = false;
        int playerMove = 0;
        int winnersPlayerNumber = 0;
        int countOfWins = 0;
        while (!onExitFromGame) {
            if (!gameField.onGameFieldIsFull()) {
                currentPlayerNumber = whoIsNextPlayer();
                playerMove = playerList.get(currentPlayerNumber - 1).getPlayerMove(scanner, randomGenerator, gameField);
                playerList.get(currentPlayerNumber - 1).makePlayerMove(playerMove, gameField);
                gameField.outputGameField();
                if (onExitFromGame = playerList.get(currentPlayerNumber - 1).playerWon(gameField.getField())) {
                    winnersPlayerNumber = currentPlayerNumber;
                    countOfWins = playerList.get(currentPlayerNumber - 1).getCountOfWins() + 1;
                    playerList.get(currentPlayerNumber - 1).setCountOfWins(countOfWins);
                }
                if (playerList.get(0).getPlayerType() == TypesOfPlayers.COMPUTER && playerList.get(1).getPlayerType() == TypesOfPlayers.COMPUTER) {
                    System.out.println("Press ENTER to continue...");
                    String anyKey = scanner.nextLine();
                }
            } else {
                onExitFromGame = true;
            }
        }
        outputGameResult(winnersPlayerNumber);
    }

    private void setUpMatch(int countOfMatches) {
        currentPlayerNumber = 0;
        playerList.clear();
        whoIsWho();
        System.out.println(countOfMatches + " match " +
                playerList.get(0).getPlayerType() + " - " + playerList.get(1).getPlayerType());
    }

    private void whoIsWho() {
        choseFirstPlayer();
        choseSecondPlayer();
    }

    private void choseFirstPlayer() {
        int userChoice = 0;
        final int CHOICE_X_ITEM_HUMAN = 1;
        final int CHOICE_X_ITEM_COMPUTER = 2;
        System.out.print("\nChoose the 'X' player as called: \n"
                + " " + CHOICE_X_ITEM_HUMAN + " - HUMAN\n"
                + " " + CHOICE_X_ITEM_COMPUTER + " - COMPUTER\n" +
                ">> ");
        do {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case CHOICE_X_ITEM_HUMAN:
                        playerList.add(new HumanPlayer(1, 'X', TypesOfPlayers.HUMAN, 0));
                        break;
                    case CHOICE_X_ITEM_COMPUTER:
                        playerList.add(new ComputerPlayer(1, 'X', TypesOfPlayers.COMPUTER, 0));
                        break;
                    default:
                        System.out.println("Wrong choice! You can use only numbers from 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong choice! You can use only numbers from 1 or 2");
            }
        } while (userChoice != 1 && userChoice != 2);
    }

    private void choseSecondPlayer() {
        int userChoice = 0;
        final int CHOICE_O_ITEM_HUMAN = 1;
        final int CHOICE_O_ITEM_COMPUTER = 2;
        System.out.print("\nChoose the 'O' player as called: \n"
                + " " + CHOICE_O_ITEM_HUMAN + " - HUMAN\n"
                + " " + CHOICE_O_ITEM_COMPUTER + " - COMPUTER\n" +
                ">> ");
        do {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case CHOICE_O_ITEM_HUMAN:
                        playerList.add(new HumanPlayer(2, 'O', TypesOfPlayers.HUMAN, 0));
                        break;
                    case CHOICE_O_ITEM_COMPUTER:
                        playerList.add(new ComputerPlayer(2, 'O', TypesOfPlayers.COMPUTER, 0));
                        break;
                    default:
                        System.out.println("Wrong choice! You can use only numbers from 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong choice! You can use only numbers from 1 or 2");
            }
        } while (userChoice != 1 && userChoice != 2);
    }

    private void setUpGame() {
        char[][] array = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        gameField = new GameField(array);
    }

    private int whoIsNextPlayer() {
        switch (currentPlayerNumber) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return 1;
        }
    }

    private void outputGameResult(int winnersPlayerNumber) {
        if (winnersPlayerNumber > 0) {
            System.out.println("Won: player " +
                    playerList.get(winnersPlayerNumber - 1).getPlayerNumber() +
                    " as " + playerList.get(winnersPlayerNumber - 1).getPlayerType());
        } else {
            System.out.println("No one won - draw");
        }
    }

    private void OutputMatchResult(int countOfMatches) {
        System.out.println(countOfMatches + " match " +
                playerList.get(0).getPlayerType() + "-" + playerList.get(1).getPlayerType() + ", results " +
                playerList.get(0).getCountOfWins() + " : " + playerList.get(1).getCountOfWins());
    }

    private boolean onExitFromMatch() {
        String userChoice = "";
        System.out.println("Do you want to start a new game in this match (Y/N)?");
        do {
            userChoice = scanner.nextLine().toUpperCase();
            if (!userChoice.equals("Y") && !userChoice.equals("N")) {
                System.out.println("Wrong choice, enter only Y (Yes) or N (No)");
            }
        } while (!userChoice.equals("Y") && !userChoice.equals("N"));
        return userChoice.equals("N");
    }

    private boolean onExitFromGravitrips() {
        String userChoice = "";
        System.out.println("Do you want to start a new match (Y/N)?");
        do {
            userChoice = scanner.nextLine().toUpperCase();
            if (!userChoice.equals("Y") && !userChoice.equals("N")) {
                System.out.println("Wrong choice, enter only Y (Yes) or N (No)");
            }
        } while (!userChoice.equals("Y") && !userChoice.equals("N"));
        return userChoice.equals("N");
    }

    private void finishGravitrips() {
        System.out.println("Thanks you for the game! Goodbye!");
        scanner.close();
    }
}

//"C:\Program Files\Java\jdk1.8.0_144\bin\java" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.4\lib\idea_rt.jar=55976:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_144\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\rt.jar;C:\Users\Oleg\IdeaProjects\Gravitrips\out\production\Gravitrips" lv.javaguru.homeworks.gravitrips.Gravitrips
//
//        Welcome to the game GRAVITRIPS (Four in a Line)
//        Some assumptions and limitations:
//
//        - размер игрового поля 6(rows) х 7(columns)
//        - количество матчей произвольное
//        - количество игр в матче произвольное
//        - межлу собой играют 2 игрока - HUMAN & COMPUTER
//        - в рамках одного матча типы игроков не меняются
//        - 1-й игрок в качестве фишек использует 'Х'
//        - 2-й игрок в качестве фишек использует 'O'
//        - в 1-й игре матча первым ходит 1-й игрок, далее по очереди
//        в течении всего матча
//        - используется простейшая стратегия для хода компюьтера, -
//        его ход генерируется генератором случайных чисел.
//        Press ENTER to continue...
//
//        1 match
//
//        Choose the 'X' player as called:
//        1 - HUMAN
//        2 - COMPUTER
//        >> 1
//
//        Choose the 'O' player as called:
//        1 - HUMAN
//        2 - COMPUTER
//        >> 2
//        1 match HUMAN - COMPUTER
//        1 game
//        Player 1 as HUMAN, please, enter a column number:
//        1
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        Player 2 as COMPUTER: 6
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . O .
//        Player 1 as HUMAN, please, enter a column number:
//        1
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        X . . . . O .
//        Player 2 as COMPUTER: 5
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        X . . . O O .
//        Player 1 as HUMAN, please, enter a column number:
//        1
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        X . . . . . .
//        X . . . O O .
//        Player 2 as COMPUTER: 6
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        X . . . . O .
//        X . . . O O .
//        Player 1 as HUMAN, please, enter a column number:
//        1
//        . . . . . . .
//        . . . . . . .
//        X . . . . . .
//        X . . . . . .
//        X . . . . O .
//        X . . . O O .
//        Won: player 1 as HUMAN
//        Do you want to start a new game in this match (Y/N)?
//        y
//        2 game
//        Player 2 as COMPUTER: 5
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . O . .
//        Player 1 as HUMAN, please, enter a column number:
//        3
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X . O . .
//        Player 2 as COMPUTER: 4
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X O O . .
//        Player 1 as HUMAN, please, enter a column number:
//        3
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X . . . .
//        . . X O O . .
//        Player 2 as COMPUTER: 1
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X . . . .
//        O . X O O . .
//        Player 1 as HUMAN, please, enter a column number:
//        3
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X . . . .
//        . . X . . . .
//        O . X O O . .
//        Player 2 as COMPUTER: 5
//        . . . . . . .
//        . . . . . . .
//        . . . . . . .
//        . . X . . . .
//        . . X . O . .
//        O . X O O . .
//        Player 1 as HUMAN, please, enter a column number:
//        3
//        . . . . . . .
//        . . . . . . .
//        . . X . . . .
//        . . X . . . .
//        . . X . O . .
//        O . X O O . .
//        Won: player 1 as HUMAN
//        Do you want to start a new game in this match (Y/N)?
//        n
//        1 match HUMAN-COMPUTER, results 2 : 0
//        Do you want to start a new match (Y/N)?
//        n
//        Thanks you for the game! Goodbye!
//
//        Process finished with exit code 0
