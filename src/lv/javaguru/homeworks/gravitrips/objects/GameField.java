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

    public boolean gameFieldIsFull() {
        boolean isThereFreeCell = false;
        for (int i = 0; i < MAX_ROW && !isThereFreeCell; i++) {
            for (int j = 0; j < MAX_COL && !isThereFreeCell; j++) {
                isThereFreeCell = (field[i][j] == '.');
            }
        }
        return !isThereFreeCell;
    }

    public boolean columnIsNotBusy(int columnNumber) {
        boolean columnIsNotBusy = true;
        for (int i = 0; i < MAX_ROW && !(columnIsNotBusy = (field[i][columnNumber - 1] == '.')); i++) {
        }
        return columnIsNotBusy;
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

    public boolean playerWon(TypesOfSigns sign) {
        boolean playerWon = true;
        if (!fourInLineByHorizontal(sign)) {
            if (!fourInLineByVertical(sign)) {
                if (!fourInLineByRightDiagonal(sign)) {
                    if (!fourInLineByLeftDiagonal(sign)) {
                        playerWon = false;
                    }
                }
            }
        }
        return playerWon;
    }

    private boolean fourInLineByHorizontal(TypesOfSigns sign) {
        int countInLine = 0;
        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
            countInLine = 0;
            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
                countInLine = countInLine(field[i][j], countInLine, sign);
            }
        }
        return countInLine >= 4;
    }

    private boolean fourInLineByVertical(TypesOfSigns sign) {
        int countInLine = 0;
        for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
            countInLine = 0;
            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
                countInLine = countInLine(field[i][j], countInLine, sign);
            }
        }
        return countInLine >= 4;
    }

    private boolean fourInLineByRightDiagonal(TypesOfSigns sign) {
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

    private boolean fourInLineByLeftDiagonal(TypesOfSigns sign) {
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



//    public boolean playerWon(char[][] field) {
//        boolean playerWon = true;
//        if (!fourInLineByHorizontal(field)) {
//            if (!fourInLineByVertical(field)) {
//                if (!fourInLineByRightDiagonal(field)) {
//                    if (!fourInLineByLeftDiagonal(field)) {
//                        playerWon = false;
//                    }
//                }
//            }
//        }
//        return playerWon;
//    }
//
//    private boolean fourInLineByHorizontal(char[][] field) {
//        int countInLine = 0;
//        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//            countInLine = 0;
//            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                countInLine = countInLine(field[i][j], countInLine);
//            }
//        }
//        return countInLine >= 4;
//    }
//
//    private boolean fourInLineByVertical(char[][] field) {
//        int countInLine = 0;
//        for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//            countInLine = 0;
//            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//                countInLine = countInLine(field[i][j], countInLine);
//            }
//        }
//        return countInLine >= 4;
//    }
//
//    private boolean fourInLineByRightDiagonal(char[][] field) {
//        int countInLine = 0;
//        //main diagonal
//        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                if (i == j) {
//                    countInLine = countInLine(field[i][j], countInLine);
//                }
//            }
//        }
//        //under the main diagonal
//        for (int d = 1; d < 4 && countInLine < 4; d++) {
//            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                    if (i < j && j == i + d) {
//                        countInLine = countInLine(field[i][j], countInLine);
//                    }
//                }
//            }
//        }
//        //above the main diagonal
//        for (int d = 1; d < 3 && countInLine < 4; d++) {
//            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                    if (i > j && j == i - d) {
//                        countInLine = countInLine(field[i][j], countInLine);
//                    }
//                }
//            }
//        }
//        return countInLine >= 4;
//    }
//
//    private boolean fourInLineByLeftDiagonal(char[][] field) {
//        int countInLine = 0;
//        //side diagonal
//        for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//            for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                if (i == MAX_ROW - j) {
//                    countInLine = countInLine(field[i][j], countInLine);
//                }
//            }
//        }
//        //under the side diagonal
//        for (int d = 1; d < 4 && countInLine < 4; d++) {
//            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                    if (i < MAX_ROW - j && j == MAX_ROW - i - d) {
//                        countInLine = countInLine(field[i][j], countInLine);
//                    }
//                }
//            }
//        }
//        //above the side diagonal
//        for (int d = 1; d < 3 && countInLine < 4; d++) {
//            for (int i = 0; i < MAX_ROW && countInLine < 4; i++) {
//                for (int j = 0; j < MAX_COL && countInLine < 4; j++) {
//                    if (i > MAX_ROW - j && j == MAX_ROW - i + d) {
//                        countInLine = countInLine(field[i][j], countInLine);
//                    }
//                }
//            }
//        }
//        return countInLine >= 4;
//    }
//
//    private int countInLine(char fieldCell, int countInLine) {
//        int newCountInLine;
//        if (fieldCell == sign.getName()) {
//            newCountInLine = countInLine + 1;
//        } else {
//            newCountInLine = 0;
//        }
//        return newCountInLine;
//    }
//

}
