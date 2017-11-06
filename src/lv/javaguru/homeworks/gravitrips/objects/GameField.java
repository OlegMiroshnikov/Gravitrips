package lv.javaguru.homeworks.gravitrips.objects;

import static lv.javaguru.homeworks.gravitrips.objects.Gravitrips.*;

public class GameField {
    private char[][] field;

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public GameField(char[][] field) {
        this.field = field;
    }

    public boolean isGameFieldFull() {
        boolean isThereFreeCell = false;
        for (int i = 0; i < MAX_ROW && !isThereFreeCell; i++) {
            for (int j = 0; j < MAX_COL && !isThereFreeCell; j++) {
                isThereFreeCell = (field[i][j] == '.');
            }
        }
        return !isThereFreeCell;
    }

    public boolean isColumnNotBusy(int columnNumber) {
        boolean isColumnNotBusy = true;
        for (int i = 0; i < MAX_ROW && !(isColumnNotBusy = (field[i][columnNumber - 1] == '.')); i++) {
        }
        return isColumnNotBusy;
    }

    public void outputGameField() {
        for (int i = MAX_ROW - 1; i >= 0; i--) {
            if (i < MAX_ROW - 1) {
                System.out.println("");
            }
            for (int j = 0; j < MAX_COL; j++) {
                System.out.print(field[i][j] + " ");
            }
        }
        System.out.println("");
    }

    public void makePlayerMove(Player player, int playerMove) {
        boolean isGetPlayerMove = false;
        for (int i = 0; i < MAX_ROW && !isGetPlayerMove; i++) {
            if (field[i][playerMove - 1] == '.') {
                isGetPlayerMove = true;
                field[i][playerMove - 1] = player.getSign().getName();
            }
        }
        if (player.getType() == TypesOfPlayers.COMPUTER) {
            System.out.println("Player " + player.getSign().getNumber() + " as " + player.getType() + ": " + playerMove);
        }
    }


    public boolean isPlayerWon(TypesOfSigns sign) {
        boolean isPlayerWon = true;
        if (!isFourInLineByHorizontal(sign)) {
            if (!isFourInLineByVertical(sign)) {
                if (!isFourInLineByRightDiagonal(sign)) {
                    if (!isFourInLineByLeftDiagonal(sign)) {
                        isPlayerWon = false;
                    }
                }
            }
        }
        return isPlayerWon;
    }

    private boolean isFourInLineByHorizontal(TypesOfSigns sign) {
        int countInLine = 0;
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            countInLine = 0;
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                countInLine = countInLine(field[i][j], countInLine, sign);
            }
        }
        return countInLine >= 4;
    }

    private boolean isFourInLineByVertical(TypesOfSigns sign) {
        int countInLine = 0;
        for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
            countInLine = 0;
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                countInLine = countInLine(field[i][j], countInLine, sign);
            }
        }
        return countInLine >= 4;
    }

    private boolean isFourInLineByRightDiagonal(TypesOfSigns sign) {
        int countInLine = 0;
        //main diagonal
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                if (i == j) {
                    countInLine = countInLine(field[i][j], countInLine, sign);
                }
            }
        }
        //under the main diagonal
        for (int d = 1; d < 4 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i < j && j == i + d) {
                        countInLine = countInLine(field[i][j], countInLine, sign);
                    }
                }
            }
        }
        //above the main diagonal
        for (int d = 1; d < 3 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i > j && j == i - d) {
                        countInLine = countInLine(field[i][j], countInLine, sign);
                    }
                }
            }
        }
        return countInLine >= 4;
    }

    private boolean isFourInLineByLeftDiagonal(TypesOfSigns sign) {
        int countInLine = 0;
        //side diagonal
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                if (i == MAX_ROW - j) {
                    countInLine = countInLine(field[i][j], countInLine, sign);
                }
            }
        }
        //under the side diagonal
        for (int d = 1; d < 4 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i < MAX_ROW - j && j == MAX_ROW - i - d) {
                        countInLine = countInLine(field[i][j], countInLine, sign);
                    }
                }
            }
        }
        //above the side diagonal
        for (int d = 1; d < 3 && countInLine < 4; d++) {
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                    if (i > MAX_ROW - j && j == MAX_ROW - i + d) {
                        countInLine = countInLine(field[i][j], countInLine, sign);
                    }
                }
            }
        }
        return countInLine >= 4;
    }

    private int countInLine(char fieldCell, int countInLine, TypesOfSigns sign) {
        int newCountInLine;
        if (fieldCell == sign.getName()) {
            newCountInLine = countInLine + 1;
        } else {
            newCountInLine = 0;
        }
        return newCountInLine;
    }

}
