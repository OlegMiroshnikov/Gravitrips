package lv.javaguru.homeworks.gravitrips;

public enum Signs {
    X("X"),
    O("O"),
    EMPTY(".");

    private String value;

    private Signs(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}


