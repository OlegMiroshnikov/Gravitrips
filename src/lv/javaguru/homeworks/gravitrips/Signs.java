package lv.javaguru.homeworks.gravitrips;

public enum Signs {
    X('X',1),
    O('O',2);

    Signs(char name, int number) {
        this.name = name;
        this.number = number;
    }

    private char name;
    private int number;

    public int getNumber() {
        return number;
    }

    public char getName() {
        return name;
    }
}

