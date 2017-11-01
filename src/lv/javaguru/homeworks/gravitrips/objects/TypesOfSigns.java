package lv.javaguru.homeworks.gravitrips.objects;

public enum TypesOfSigns {
    X('X'),
    O('O');

    TypesOfSigns(char name) {
        this.name = name;
    }

    private char name;

    public char getName() {
        return name;
    }
}

