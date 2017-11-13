package lv.javaguru.homeworks.gravitrips;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {
    @DisplayName("Test to field is full")
    @Test
    void isGameFieldFull() {
        boolean result;
        char[][] array = {
                {'X', 'X', 'O', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'O', 'X', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'C', 'X', 'O', 'X', 'O'}};
        GameField gameField = new GameField(array);
        result = gameField.isGameFieldFull();
        assertTrue(result, "Game field is full");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isGameFieldFull();
        assertFalse(result, "Game field is not full");
    }

    @DisplayName("Test to column is not busy")
    @Test
    void isColumnNotBusy() {
        boolean result;
        char[][] array = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        GameField gameField = new GameField(array);
        result = gameField.isColumnNotBusy(1);
        assertTrue(result, "Column is not busy");

        array = new char[][]{
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isColumnNotBusy(1);
        assertFalse(result, "Column is busy");
    }

    @DisplayName("Test to player move")
    @Test
    void makePlayerMove() {
        boolean result;
        Player humanPlayer = new HumanPlayer(Signs.X, Players.HUMAN);
        char[][] array = {
                {'.', 'X', 'O', '.', 'O', '.', '.'},
                {'.', 'O', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        GameField gameField = new GameField(array);
        gameField.makePlayerMove(humanPlayer, 2);
        array = new char[][]{
                {'.', 'X', 'O', '.', 'O', '.', '.'},
                {'.', 'O', '.', '.', 'X', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        assertArrayEquals(array, gameField.getField(), "Player move to the game");
    }

    @DisplayName("Test to player is won")
    @Test
    void isPlayerWon() {
        boolean result;
        char[][] array = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', ')', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', 'X', 'X', 'X', '.', '.'}};
        GameField gameField = new GameField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by horizontal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by vertical");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by right main diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by under of right main diagonal");

        array = new char[][]{
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by above of right main diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by left side diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by under of left side diagonal");

        array = new char[][]{
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        gameField.setField(array);
        result = gameField.isPlayerWon(Signs.X);
        assertTrue(result, "Four in line by above of left side diagonal");
    }
}