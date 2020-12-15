package org.jazzteam.sidorov.onlinepiano.sheet;

import org.jazzteam.sidorov.onlinepiano.KeyBinds;
import org.jazzteam.sidorov.onlinepiano.Piano;
import org.jazzteam.sidorov.onlinepiano.PianoType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

//TODO string->enum
class PlayerTest {

    @Test
    void pianoTest() {
        Piano piano = new Piano();
        piano.accentsOn();
        piano.accentsOff();
        piano.setMasterVolume(1);
        piano.setPlayBackSpeed(1);
        piano.setSustainPedalPress(0.5);
        piano.setPianoType(PianoType.GRAND_PIANO, 1, piano);
        piano.setPianoType(PianoType.UPRIGHT_PIANO, 0.9, piano);
        piano.setPianoType(PianoType.DIGITAL_PIANO, 2, piano);
        piano.playChord(NoteName.C, 3, "1/16", "Dim");
        piano.switchKeyBind("qwerty");
        piano.switchKeyBind("digits");
        Assertions.assertEquals(6, piano.getMaxNoteLength());
        Assertions.assertEquals(0.1, piano.getMasterVolume());
        Assertions.assertEquals(1, piano.getPlayBackSpeed());
        Assertions.assertEquals(0.5, piano.getSustainPedalPress());
        KeyBinds keyBinds = new KeyBinds();
        keyBinds.setKeyBind("test");
        Assertions.assertEquals("test", keyBinds.getKeyBind());
    }

    @Test
    void pianoExceptionsTest() {
        Piano piano = new Piano();

        try {
            piano.playChord(NoteName.C, 7, "1/16", "M");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid octave number", e.getMessage());
        }
        try {
            piano.playChord(NoteName.C, 3, "1/16", "N");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid Type", e.getMessage());
        }
        try {
            piano.setSustainPedalPress(1.1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid pedal press level value", e.getMessage());
        }
        try {
            piano.setMasterVolume(1.1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid volume value", e.getMessage());
        }

    }

    @Test
    void sheetMusicTest() {
        try {
            new SheetMusic(1, 0.4, NoteName.C, "Rondo Alla Turca", 120, Collections.emptyList());
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid bar size", e.getMessage());
        }
        try {
            new SheetMusic(1, 0.5, NoteName.C, "Rondo Alla Turca", -1, Collections.emptyList());
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Tempo must be above 0", e.getMessage());
        }
    }

    @Test
    void noteTest() {
        try {
            new Note(NoteName.C, 8, "1", NoteAlteration.SHARP, true);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid octave number", e.getMessage());
        }
        try {
            new Note(NoteName.C, 3, "1.1", NoteAlteration.SHARP, true);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid note duration", e.getMessage());
        }
    }

    @Test
//TODO delete
    void playerTest() {
        Player player = new Player();
        player.test();
        SheetMusic sheetMusic = new NoteService().getSheetMusic(1);
        player.play(sheetMusic);
    }

}
