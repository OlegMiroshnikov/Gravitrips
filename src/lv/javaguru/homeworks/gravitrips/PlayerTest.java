package lv.javaguru.homeworks.gravitrips;

import static lv.javaguru.homeworks.gravitrips.Enum.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void playerWon() {
        Player humanPlayer = new HumanPlayer(1, 'X', TypesOfPlayers.HUMAN, 0);
        boolean result;
        char[][] array = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', 'X', 'X', 'X', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by horizontal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by vertical");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by right main diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by under of right main diagonal");

        array = new char[][]{
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by above of right main diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by left side diagonal");

        array = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', 'X', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by under of left side diagonal");

        array = new char[][]{
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        result = humanPlayer.playerWon(array);
        assertTrue(result, "Four in line by above of left side diagonal");
    }

    @Test
    void makePlayerMove() {
        boolean result;
        Player humanPlayer = new HumanPlayer(1, 'X', TypesOfPlayers.HUMAN, 0);
        char[][] array = {
                {'.', 'X', 'O', '.', 'O', '.', '.'},
                {'.', 'O', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        GameField gameField = new GameField(array);
        humanPlayer.makePlayerMove(2, gameField);
        array = new char[][]{
                {'.', 'X', 'O', '.', 'O', '.', '.'},
                {'.', 'O', '.', '.', 'X', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}};
        assertArrayEquals(array, gameField.getField(), "Player move to the game");
    }
}

