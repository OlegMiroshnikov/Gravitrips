package lv.javaguru.homeworks.gravitrips;

import java.util.Scanner;

public class Gravitrips {
    public static final int MAX_ROW = 6;
    public static final int MAX_COL = 7;

    private Scanner scanner = new Scanner(System.in);

    public void startGravitrips() {
        boolean exitFromGravitrips = false;
        boolean exitFromMatch = false;
        int matchNumber = 0;
        int gameNumber = 0;
        outputInstructionsToUser();
        while (!exitFromGravitrips) {
            matchNumber++;
            Match match = new Match(matchNumber);
            System.out.println(match);
            gameNumber = 0;
            exitFromMatch = false;
            while (!exitFromMatch) {
                gameNumber++;
                System.out.println(gameNumber + " game");
                match.toPlayGame();
                exitFromMatch = exitFromMatch();
            }
            match.outputMatchResult();
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
