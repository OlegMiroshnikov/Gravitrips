package lv.javaguru.homeworks.gravitrips;

import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_ROW;

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

    public boolean onGameFieldIsFull() {
        boolean onThereIsFreeCell = false;
        for (int i = 0; i < MAX_ROW && !onThereIsFreeCell; i++) {
            for (int j = 0; j < MAX_COL && !onThereIsFreeCell; j++) {
                onThereIsFreeCell = (field[i][j] == '.');
            }
        }
        return !onThereIsFreeCell;
    }

    public boolean onColumnIsNotBusy(int columnNumber) {
        boolean onColumnIsNotBusy = true;
        for (int i = 0; i < MAX_ROW && !(onColumnIsNotBusy = (field[i][columnNumber - 1] == '.')); i++) {
        }
        return onColumnIsNotBusy;
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

}
