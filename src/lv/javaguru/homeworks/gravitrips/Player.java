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

    public void setSign(Signs sign) {
        this.sign = sign;
    }

    public Players getType() {
        return type;
    }

    public void setType(Players type) {
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