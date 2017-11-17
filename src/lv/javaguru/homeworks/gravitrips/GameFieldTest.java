package lv.javaguru.homeworks.gravitrips;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_COL;
import static lv.javaguru.homeworks.gravitrips.Gravitrips.MAX_ROW;

class GameFieldTest {

    public Signs[][] convertToSigns(String[][] stringArray) {
        Signs[][] array = new Signs[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (stringArray[i][j] == ".") {
                    array[i][j] = Signs.valueOf("EMPTY");
                } else {
                    array[i][j] = Signs.valueOf(stringArray[i][j]);
                }
            }
        }
        return array;
    }

    @DisplayName("Test to field is full")
    @Test
    void isGameFieldFull() {
        boolean result;
        String[][] stringArray = {
                {"X", "X", "O", "X", "O", "X", "O"},
                {"X", "X", "O", "X", "O", "X", "O"},
                {"X", "X", "O", "O", "X", "O", "X"},
                {"O", "O", "X", "O", "O", "X", "O"},
                {"X", "O", "O", "X", "X", "O", "X"},
                {"X", "O", "O", "X", "O", "X", "O"}};
        GameField gameField = new GameField(convertToSigns(stringArray));
        result = gameField.isGameFieldFull();
        assertTrue(result, "Game field is full");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isGameFieldFull();
        assertFalse(result, "Game field is not full");
    }

    @DisplayName("Test to column is not busy")
    @Test
    void isColumnNotBusy() {
        boolean result;
        String[][] stringArray = {
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."}};
        GameField gameField = new GameField(convertToSigns(stringArray));
        result = gameField.isColumnNotBusy(1);
        assertTrue(result, "Column is not busy");

        stringArray = new String[][]{
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isColumnNotBusy(1);
        assertFalse(result, "Column is busy");
    }

    @DisplayName("Test to player move")
    @Test
    void makePlayerMove() {
        boolean result;
        Player humanPlayer = new HumanPlayer(Signs.X, Players.HUMAN);
        String[][] stringArray = {
                {".", "X", "O", ".", "O", ".", "."},
                {".", "O", ".", ".", "X", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."}};
        GameField gameField = new GameField(convertToSigns(stringArray));
        gameField.makePlayerMove(humanPlayer, 2);
        stringArray = new String[][]{
                {".", "X", "O", ".", "O", ".", "."},
                {".", "O", ".", ".", "X", ".", "."},
                {".", "X", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."}};
        assertArrayEquals(convertToSigns(stringArray), gameField.getField(), "Player move to the game");
    }

    @DisplayName("Test to player is won")
    @Test
    void isPlayerWon() {
        boolean result;
        String[][] stringArray = {
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", "X", "X", "X", "X", ".", "."}};
        GameField gameField = new GameField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by horizontal");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by vertical");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", "X", ".", "."},
                {".", ".", ".", "X", ".", ".", "."},
                {".", ".", "X", ".", ".", ".", "."},
                {".", "X", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by right main diagonal");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "X"},
                {".", ".", ".", ".", ".", "X", "."},
                {".", ".", ".", ".", "X", ".", "."},
                {".", ".", ".", "X", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by under of right main diagonal");

        stringArray = new String[][]{
                {".", ".", ".", "X", ".", ".", "."},
                {".", ".", "X", ".", ".", ".", "."},
                {".", "X", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by above of right main diagonal");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", "X", ".", ".", "."},
                {".", ".", ".", ".", "X", ".", "."},
                {".", ".", ".", ".", ".", "X", "."},
                {".", ".", ".", ".", ".", ".", "X"}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by left side diagonal");

        stringArray = new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"X", ".", ".", ".", ".", ".", "."},
                {".", "X", ".", ".", ".", ".", "."},
                {".", ".", "X", ".", ".", ".", "."},
                {".", ".", ".", "X", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by under of left side diagonal");

        stringArray = new String[][]{
                {".", ".", ".", "X", ".", ".", "."},
                {".", ".", ".", ".", "X", ".", "."},
                {".", ".", ".", ".", ".", "X", "."},
                {".", ".", ".", ".", ".", ".", "X"},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."}};
        gameField.setField(convertToSigns(stringArray));
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by above of left side diagonal");
    }
}

