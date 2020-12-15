package org.jazzteam.sidorov.onlinepiano;


import org.jazzteam.sidorov.onlinepiano.sheet.NoteName;

public interface PianoInterface {

    /**
     * @param value percentage scale of setting (1=100%, 0.1=10%)
     */
    void setPianoType(PianoType pianoType, double value, Piano piano);

    /**
     * @param keyBindType choose keybind preset
     */
    void switchKeyBind(String keyBindType);

    /**
     * @param playBackSpeed set playback speed in percent of original (1=100%)
     */
    void setPlayBackSpeed(double playBackSpeed);

    void accentsOn();

    void accentsOff();

    /**
     * @param octave    choose in which octave do you want your chord in
     * @param chordType choose chord type (relation between chord notes)
     */
    void playChord(NoteName noteName, int octave, String chordDuration, String chordType);

    /**
     * @param masterVolume set master volume in percentage (1=100%)
     */
    void setMasterVolume(double masterVolume);

    /**
     * @param sustainPedalPress set level of sustain pedal (0 is no pedal, 1=100% press)
     */
    void setSustainPedalPress(double sustainPedalPress);
}
