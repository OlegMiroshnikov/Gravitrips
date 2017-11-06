package lv.javaguru.homeworks.gravitrips.objects;

public enum TypesOfSigns {
    X('X',1),
    O('O',2);

    TypesOfSigns(char name, int number) {
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

