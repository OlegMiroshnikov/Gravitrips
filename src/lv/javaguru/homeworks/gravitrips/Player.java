package lv.javaguru.homeworks.gravitrips;

public abstract class Player {
    private Signs sign;
    private Players type;
    private int countOfWins;

    public Player(Signs sign, Players type) {
        this.sign = sign;
        this.type = type;
        this.countOfWins = 0;
    }

    public Signs getSign() {
        return sign;
    }

    public Players getType() {
        return type;
    }

    public int getCountOfWins() {
        return countOfWins;
    }

    public void setCountOfWins(int countOfWins) {
        this.countOfWins = countOfWins;
    }

    @Override
    public String toString() {
        return "Player " + (getSign().ordinal()+1) + " as " + getType();
    }
    public abstract int getPlayerMove(GameField gameField);


}