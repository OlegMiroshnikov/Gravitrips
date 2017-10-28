package lv.javaguru.homeworks.gravitrips;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {
    @Test
    void onGameFieldIsFull() {
        boolean result;
        char[][] array = {
                {'X', 'X', 'O', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'O', 'X', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'C', 'X', 'O', 'X', 'O'}};
        GameField gameField = new GameField(array);
        result = gameField.onGameFieldIsFull();
        assertTrue(result, "Game field is full");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.onGameFieldIsFull();
        assertFalse(result, "Game field is not full");
    }

    @Test
    void onColumnIsNotBusy() {
        boolean result;
        char[][] array = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        GameField gameField = new GameField(array);
        result = gameField.onColumnIsNotBusy(1);
        assertTrue(result, "Column is not busy");

        array = new char[][]{
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.onColumnIsNotBusy(1);
        assertFalse(result, "Column is busy");
    }

}