package lv.javaguru.homeworks.gravitrips.objects;

import lv.javaguru.homeworks.gravitrips.objects.Gravitrips.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @DisplayName("Test to player move")
    @Test
    void makePlayerMove() {
        boolean result;
        Player humanPlayer = new HumanPlayer(1, TypesOfSigns.X, TypesOfPlayers.HUMAN, 0);
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

