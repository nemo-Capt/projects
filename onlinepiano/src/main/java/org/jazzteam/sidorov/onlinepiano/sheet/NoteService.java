package org.jazzteam.sidorov.onlinepiano.sheet;

import java.util.ArrayList;
import java.util.List;

class NoteService {
    /**
     * @param id gets and constructs sheetMusic object from database data
     * @return returns sheetMusic object with piece main info and list of notes in it
     */
    SheetMusic getSheetMusic(int id) {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(NoteName.C, 3, "1/16", true));
        notes.add(new Note(NoteName.C, 3, "1/16", true));
        notes.add(new Note(NoteName.C, 3, "1/16", NoteAlteration.SHARP, true));
        notes.add(new Note(NoteName.C, 4, "1/16", true));
        notes.add(new Note(NoteName.C, 4, "1/8", true));
        return new SheetMusic(id, 0.5, NoteName.C, "Rondo Alla Turca", 120, notes);
    }
}
