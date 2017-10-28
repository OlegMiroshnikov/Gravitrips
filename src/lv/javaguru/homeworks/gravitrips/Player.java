package lv.javaguru.homeworks.gravitrips;

import java.util.Random;
import java.util.Scanner;
import static lv.javaguru.homeworks.gravitrips.Enum.*;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_ROW;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;

public abstract class Player {
    private int playerNumber;
    private char playerSign;
    private TypesOfPlayers playerType;
    private int countOfWins;

    public Player(int playerNumber, char playerSign, TypesOfPlayers playerType, int countOfWins) {
        this.playerNumber = playerNumber;
        this.playerSign = playerSign;
        this.playerType = playerType;
        this.countOfWins = countOfWins;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public char getPlayerSign() {
        return playerSign;
    }

    public void setPlayerSign(char playerSign) {
        this.playerSign = playerSign;
    }

    public TypesOfPlayers getPlayerType() {
        return playerType;
    }

    public void setPlayerType(TypesOfPlayers playerType) {
        this.playerType = playerType;
    }

    public int getCountOfWins() {
        return countOfWins;
    }

    public void setCountOfWins(int countOfWins) {
        this.countOfWins = countOfWins;
    }

    public int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField) {
        return 0;
    }

    public void makePlayerMove(int playerMove, GameField gameField) {
        boolean onGetPlayerMove = false;
        char[][] field = gameField.getField();
        for (int i = 0; i < MAX_ROW && !onGetPlayerMove; i++) {
            if (field[i][playerMove - 1] == '.') {
                onGetPlayerMove = true;
                field[i][playerMove - 1] = playerSign;
            }
        }
        gameField.setField(field);
        if (playerType == TypesOfPlayers.COMPUTER) {
            System.out.println("Player " + playerNumber + " as " + playerType + ": " + playerMove);
        }
    }

    public boolean playerWon(char[][] field) {
        boolean playerWon = true;
        if (!FourInLineByHorizontal(field)) {
            if (!FourInLineByVertical(field)) {
                if (!FourInLineByRightDiagonal(field)) {
                    if (!FourInLineByLeftDiagonal(field)) {
                        playerWon = false;
                    }
                }
            }
        }
        return playerWon;
    }

    private boolean FourInLineByHorizontal(char[][] field) {
        int countInLine = 0;
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            countInLine = 0;
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                countInLine = countInLine(field[i][j], countInLine);
            }
        }
        return countInLine >= 4;
    }

    private boolean FourInLineByVertical(char[][] field) {
        int countInLine = 0;
        for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
            countInLine = 0;
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                countInLine = countInLine(field[i][j], countInLine);
            }
        }
        return countInLine >= 4;
    }

    private boolean FourInLineByRightDiagonal(char[][] field) {
        int countInLine = 0;
        //main diagonal
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                if (i == j) {
                    countInLine = countInLine(field[i][j], countInLine);
                }
            }
        }
        //under the main diagonal
        for (int d = 1; d < 4 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i < j && j == i + d) {
                        countInLine = countInLine(field[i][j], countInLine);
                    }
                }
            }
        }
        //above the main diagonal
        for (int d = 1; d < 3 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i > j && j == i - d) {
                        countInLine = countInLine(field[i][j], countInLine);
                    }
                }
            }
        }
        return countInLine >= 4;
    }

    private boolean FourInLineByLeftDiagonal(char[][] field) {
        int countInLine = 0;
        //side diagonal
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                if (i == MAX_ROW - j) {
                    countInLine = countInLine(field[i][j], countInLine);
                }
            }
        }
        //under the side diagonal
        for (int d = 1; d < 4 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i < MAX_ROW - j && j == MAX_ROW - i - d) {
                        countInLine = countInLine(field[i][j], countInLine);
                    }
                }
            }
        }
        //above the side diagonal
        for (int d = 1; d < 3 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i > MAX_ROW - j && j == MAX_ROW - i + d) {
                        countInLine = countInLine(field[i][j], countInLine);
                    }
                }
            }
        }
        return countInLine >= 4;
    }

    private int countInLine(char fieldCell, int countInLine) {
        int newCountInLine;
        if (fieldCell == playerSign) {
            newCountInLine = countInLine + 1;
        } else {
            newCountInLine = 0;
        }
        return newCountInLine;
    }
}