package org.jazzteam.sidorov.onlinepiano.sheet;

class Note {

    private String noteName;
    private int noteOctave;
    private String noteDuration;
    private String noteAlteration;
    private boolean timeLineSwitch;

    /**
     * @param noteOctave   note octave (1-6)
     * @param noteDuration note duration (in relation to bar size)
     */
    public static void checkNote(int noteOctave, String noteDuration) {
        if (noteOctave < 1 || noteOctave > 6) {
            throw new IllegalArgumentException("Invalid octave number");
        }
        if (!noteDuration.equals("1") && !noteDuration.equals("1/2") && !noteDuration.equals("1/3") && !noteDuration.equals("1/4")
                && !noteDuration.equals("1/6") && !noteDuration.equals("1/8") && !noteDuration.equals("1/12") && !noteDuration.equals("1/16")
                && !noteDuration.equals("1/32")) {
            throw new IllegalArgumentException("Invalid note duration");
        }
    }

    /**
     * @param noteName       note name (CDEFGAB)
     * @param noteOctave     note octave (1-6)
     * @param noteDuration   note duration (in relation to bar size)
     * @param noteAlteration note alteration (#, b)
     * @param timeLineSwitch true - next note playes after duration of current note ends, false - next note plays simultaneously with current note
     */
    Note(NoteName noteName, int noteOctave, String noteDuration, NoteAlteration noteAlteration, boolean timeLineSwitch) {
        checkNote(noteOctave, noteDuration);
        this.noteName = noteName.getName();
        this.noteOctave = noteOctave;
        this.noteDuration = noteDuration;
        this.noteAlteration = noteAlteration.getAlteration();
        this.timeLineSwitch = timeLineSwitch;
    }

    /**
     * @param noteName       note name (CDEFGAB)
     * @param noteOctave     note octave (1-6)
     * @param noteDuration   note duration (in relation to bar size)
     * @param timeLineSwitch true - next note playes after duration of current note ends, false - next note plays simultaneously with current note
     */
    Note(NoteName noteName, int noteOctave, String noteDuration, boolean timeLineSwitch) {
        checkNote(noteOctave, noteDuration);
        this.noteName = noteName.getName();
        this.noteOctave = noteOctave;
        this.noteDuration = noteDuration;
        this.noteAlteration = "clear";
        this.timeLineSwitch = timeLineSwitch;
    }

    String getNoteName() {
        return noteName;
    }


    int getNoteOctave() {
        return noteOctave;
    }


    String getNoteDuration() {
        return noteDuration;
    }


    String getNoteAlteration() {
        return noteAlteration;
    }

    public boolean isTimeLineSwitch() {
        return timeLineSwitch;
    }

    public void setTimeLineSwitch(boolean timeLineSwitch) {
        this.timeLineSwitch = timeLineSwitch;
    }
}
