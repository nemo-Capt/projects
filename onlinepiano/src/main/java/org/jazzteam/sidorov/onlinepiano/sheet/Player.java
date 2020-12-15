package org.jazzteam.sidorov.onlinepiano.sheet;

import org.jazzteam.sidorov.onlinepiano.Piano;
import org.jazzteam.sidorov.onlinepiano.PianoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {
    private static final Logger logger = LoggerFactory.getLogger(Player.class);


    void play(SheetMusic sheetMusic) {
        logger.info("===============================");
        logger.info("Now playing: " + sheetMusic.getName());
        logger.info("Tempo: " + sheetMusic.getTempo());
        logger.info("In key: " + sheetMusic.getKey());
        for (int i = 0; i < sheetMusic.getNotes().size(); i++) {
            logger.info(sheetMusic.getNotes().get(i).getNoteName()
                    + sheetMusic.getNotes().get(i).getNoteOctave()
                    + sheetMusic.getNotes().get(i).getNoteAlteration()
                    + " Duration: " + sheetMusic.getNotes().get(i).getNoteDuration());
        }
    }

    /**
     * @param octave    choose in which octave do you want your chord in
     * @param chordType choose chord type (relation between chord notes)
     */
    public void playChord(NoteName noteName, int octave, String chordDuration, String chordType) {
        Note.checkNote(octave, chordDuration);
        if (octave < 1 || octave > 6) {
            throw new IllegalArgumentException("Invalid Octave");
        }
        if (!chordType.equals(("M")) && !chordType.equals(("m")) && !chordType.equals(("Aug")) && !chordType.equals(("Dim")) && !chordType.equals(("Sus")) && !chordType.equals(("Ext"))) {
            throw new IllegalArgumentException("Invalid Type");
        }
        logger.info("Played chord: " + noteName.getName() + " " + chordType);
    }

    void test() {
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
    }
}
