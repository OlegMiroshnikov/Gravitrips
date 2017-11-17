package lv.javaguru.homeworks.gravitrips;

import java.util.Scanner;
import static lv.javaguru.homeworks.gravitrips.Signs.O;
import static lv.javaguru.homeworks.gravitrips.Signs.X;

public class Match {

    private int number;
    private Player player1;
    private Player player2;
    private Player lastPlayer;
    private Scanner scanner = new Scanner(System.in);

    public Match(int number) {
        this.number = number;
        setUpMatch();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(Player lastPlayer) {
        this.lastPlayer = lastPlayer;
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


    public void outputMatchResult() {
        System.out.println(this +
                ", results " + player1.getCountOfWins() + " : " + player2.getCountOfWins());

    }


}
