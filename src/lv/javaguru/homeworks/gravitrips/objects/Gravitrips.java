package lv.javaguru.homeworks.gravitrips.objects;

import java.util.Random;
import java.util.Scanner;

import static lv.javaguru.homeworks.gravitrips.objects.TypesOfSigns.O;
import static lv.javaguru.homeworks.gravitrips.objects.TypesOfSigns.X;

public class Gravitrips {
    public static final int MAX_ROW = 6;
    public static final int MAX_COL = 7;
    public static Scanner scanner = new Scanner(System.in);
    public static Random randomGenerator = new Random();

    private GameField gameField;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public void startGravitrips() {

        boolean exitFromGravitrips = false;
        boolean exitFromMatch = false;
        int countOfGames = 0;
        int countOfMatches = 0;
        outputInstructionsToUser();
        while (!exitFromGravitrips) {
            countOfMatches++;
            System.out.println(countOfMatches + " match");
            setUpMatch(countOfMatches);
            countOfGames = 0;
            exitFromMatch = false;
            while (!exitFromMatch) {
                countOfGames++;
                System.out.println(countOfGames + " game");
                setUpGame();
                toPlayGame();
                exitFromMatch = exitFromMatch();
            }
            outputMatchResult(countOfMatches);
            exitFromGravitrips = exitFromGravitrips();
        }
        finishGravitrips();
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
        boolean exitFromGame = false;
        int playerMove = 0;
        int countOfWins = 0;
        Player winnersPlayer = null;
        while (!exitFromGame) {
            if (!gameField.isGameFieldFull()) {
                currentPlayer = whoIsNextPlayer();
                playerMove = currentPlayer.getPlayerMove(gameField);
                gameField.makePlayerMove(currentPlayer, playerMove);
                gameField.outputGameField();
                if (exitFromGame = gameField.isPlayerWon(currentPlayer.getSign())) {
                    winnersPlayer = currentPlayer;
                    countOfWins = winnersPlayer.getCountOfWins() + 1;
                    winnersPlayer.setCountOfWins(countOfWins);
                }
                if (player1.getType() == TypesOfPlayers.COMPUTER && player2.getType() == TypesOfPlayers.COMPUTER) {
                    System.out.println("Press ENTER to continue...");
                    String anyKey = scanner.nextLine();
                }
            } else {
                exitFromGame = true;
            }
        }
        outputGameResult(winnersPlayer);
    }

    private void setUpMatch(int countOfMatches) {
        whoIsWho();
        currentPlayer = null;
        System.out.println(countOfMatches + " match " +
                player1.getType() + " - " + player2.getType());
    }

    private void whoIsWho() {
        chosePlayer(X);
        chosePlayer(O);
    }

    private void chosePlayer(TypesOfSigns playerSign) {
        int userChoice = 0;
        final int CHOICE_ITEM_HUMAN = 1;
        final int CHOICE_ITEM_COMPUTER = 2;
        System.out.print("\nChoose the " + "'" + playerSign.getName() + "'" + " player as called: \n"
                + " " + CHOICE_ITEM_HUMAN + " - HUMAN\n"
                + " " + CHOICE_ITEM_COMPUTER + " - COMPUTER\n" +
                ">> ");
        do {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case CHOICE_ITEM_HUMAN:
                        switch (playerSign) {
                            case X:
                                player1 = new HumanPlayer(playerSign, TypesOfPlayers.HUMAN, 0);
                                break;
                            case O:
                                player2 = new HumanPlayer(playerSign, TypesOfPlayers.HUMAN, 0);
                                break;
                        }
                        break;
                    case CHOICE_ITEM_COMPUTER:
                        switch (playerSign) {
                            case X:
                                player1 = new ComputerPlayer(playerSign, TypesOfPlayers.COMPUTER, 0);
                                break;
                            case O:
                                player2 = new ComputerPlayer(playerSign, TypesOfPlayers.COMPUTER, 0);
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

    private void setUpGame() {
        char[][] array = new char[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                array[i][j] = '.';
            }
        }
        gameField = new GameField(array);
    }

    private Player whoIsNextPlayer() {
        if (currentPlayer == null) {
            currentPlayer = player1;
        } else if (currentPlayer.getSign() == player1.getSign()) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        return currentPlayer;
    }


    private void outputGameResult(Player winnersPlayer) {
        if (winnersPlayer != null) {
            System.out.println("Won: player " +
                    winnersPlayer.getSign().getNumber() + " as " + winnersPlayer.getType());
        } else {
            System.out.println("No one won - draw");
        }
    }

    private void outputMatchResult(int countOfMatches) {
        System.out.println(countOfMatches + " match " +
                player1.getType() + "-" + player2.getType() + ", results " +
                player1.getCountOfWins() + " : " + player2.getCountOfWins());
    }

    private boolean exitFromMatch() {
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

    private boolean exitFromGravitrips() {
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

//"C:\Program Files\Java\jdk1.8.0_144\bin\java" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.4\lib\idea_rt.jar=55976:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_144\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_144\jre\lib\rt.jar;C:\Users\Oleg\IdeaProjects\Gravitrips\out\production\Gravitrips" lv.javaguru.homeworks.gravitrips.objects.Gravitrips
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
//          в течении всего матча
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
