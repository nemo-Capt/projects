package org.jazzteam.sidorov.onlinepiano.sheet;

public enum NoteName {

    C ("C"),
    D ("D"),
    E ("E"),
    F ("F"),
    G ("G"),
    A ("A"),
    B ("B");

    private String name;

    NoteName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
