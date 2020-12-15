package org.jazzteam.sidorov.onlinepiano.sheet;


public enum NoteAlteration {
    SHARP("#"),
    FLAT("b");

    private String name;

    NoteAlteration(String name) {
        this.name = name;
    }

    public String getAlteration() {
        return name;
    }
}
