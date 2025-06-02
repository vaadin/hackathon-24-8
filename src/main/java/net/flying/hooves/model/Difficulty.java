package net.flying.hooves.model;

public enum Difficulty {
    EASY(2),
    MEDIUM(25),
    HARD(35),
    EXTREME(55);
    private final int value;
    private Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
