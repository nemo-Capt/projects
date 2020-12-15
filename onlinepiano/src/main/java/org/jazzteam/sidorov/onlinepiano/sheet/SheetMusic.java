package org.jazzteam.sidorov.onlinepiano.sheet;

import java.util.List;

public class SheetMusic {

    private double barSize;
    private int id;
    private String key;
    private String name;
    private double tempo;
    private List<Note> notes;

    /**
     * @param id      id of the piece
     * @param barSize bar size (4/4 = 1, 2/4 = 0.5, 12/8 = 1.5)
     * @param key     tonality of the piece
     * @param name    name of the piece
     * @param tempo   tempo of the piece
     * @param notes   list of notes to be played
     */
    SheetMusic(int id, double barSize, NoteName key, String name, double tempo, List<Note> notes) {
        if (barSize != 0.5 && barSize != 0.75 && barSize != 1 && barSize != 1.25 && barSize != 1.5) {
            throw new IllegalArgumentException("Invalid bar size");
        }
        if (tempo < 0) {
            throw new IllegalArgumentException("Tempo must be above 0");
        }
        this.id = id;
        this.barSize = barSize;
        this.key = key.getName();
        this.name = name;
        this.tempo = tempo;
        this.notes = notes;
    }


    public int getId() {
        return id;
    }


    String getKey() {
        return key;
    }


    String getName() {
        return name;
    }


    double getTempo() {
        return tempo;
    }


    List<Note> getNotes() {
        return notes;
    }

    public double getBarSize() {
        return barSize;
    }

    public void setBarSize(double barSize) {
        this.barSize = barSize;
    }
}
