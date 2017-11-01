package lv.javaguru.homeworks.gravitrips.objects;

import java.util.Random;
import java.util.Scanner;
import static lv.javaguru.homeworks.gravitrips.objects.Gravitrips.*;

public abstract class Player {
    private int number;
    private TypesOfSigns sign;
    private TypesOfPlayers type;
    private int countOfWins;

    public Player(int number, TypesOfSigns sign, TypesOfPlayers type, int countOfWins) {
        this.number = number;
        this.sign = sign;
        this.type = type;
        this.countOfWins = countOfWins;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypesOfSigns getSign() {
        return sign;
    }

    public void setSign(TypesOfSigns sign) {
        this.sign = sign;
    }

    public TypesOfPlayers getType() {
        return type;
    }

    public void setType(TypesOfPlayers type) {
        this.type = type;
    }

    public int getCountOfWins() {
        return countOfWins;
    }

    public void setCountOfWins(int countOfWins) {
        this.countOfWins = countOfWins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return sign == player.sign;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public abstract int getPlayerMove(Scanner scanner, Random randomGenerator, GameField gameField);

    public void makePlayerMove(int playerMove, GameField gameField) {
        boolean isGetPlayerMove = false;
        char[][] field = gameField.getField();
        for (int i = 0; i < MAX_ROW && !isGetPlayerMove; i++) {
            if (field[i][playerMove - 1] == '.') {
                isGetPlayerMove = true;
                field[i][playerMove - 1] = sign.getName();
            }
        }
        gameField.setField(field);
        if (type == TypesOfPlayers.COMPUTER) {
            System.out.println("Player " + number + " as " + type + ": " + playerMove);
        }
    }


}