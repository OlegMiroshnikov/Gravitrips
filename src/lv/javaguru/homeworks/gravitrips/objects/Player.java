package lv.javaguru.homeworks.gravitrips.objects;

public abstract class Player {
    private TypesOfSigns sign;
    private TypesOfPlayers type;
    private int countOfWins;

    public Player(TypesOfSigns sign, TypesOfPlayers type, int countOfWins) {
        this.sign = sign;
        this.type = type;
        this.countOfWins = countOfWins;
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

    public abstract int getPlayerMove(GameField gameField);



}