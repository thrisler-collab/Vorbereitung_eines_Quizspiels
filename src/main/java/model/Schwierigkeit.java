package model;

public enum Schwierigkeit {
    LEICHT("Leicht"),
    MITTEL("Mittel"),
    SCHWER("Schwer");

    final String info;

    Schwierigkeit(String info) {
        this.info = info;
    }
}
