package org.jazzteam.sidorov.onlinepiano;

import org.jazzteam.sidorov.onlinepiano.pianotype.*;
import org.jazzteam.sidorov.onlinepiano.sheet.NoteName;
import org.jazzteam.sidorov.onlinepiano.sheet.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Piano implements PianoInterface {
    private static final Logger logger = LoggerFactory.getLogger(Piano.class);
    private boolean accents;
    private double masterVolume = 1;
    private double maxNoteLength = 3;
    private double playBackSpeed = 1;
    private double sustainPedalPress;
    private PianoContext pianoContext = new PianoContext();
    private PianoStrategy strategy;

    /**
     * @param value percentage scale of setting (1=100%, 0.1=10%)
     */
    public void setPianoType(PianoType pianoType, double value, Piano piano) {
        switch (pianoType) {
            case GRAND_PIANO:
                strategy = new GrandPiano();
                pianoContext.setStrategy(strategy);
                strategy.execute(value, piano);
                break;
            case UPRIGHT_PIANO:
                strategy = new UprightPiano();
                pianoContext.setStrategy(strategy);
                strategy.execute(value, piano);
                break;
            case DIGITAL_PIANO:
                strategy = new DigitalPiano();
                pianoContext.setStrategy(strategy);
                strategy.execute(value, piano);
                break;
        }
    }

    /**
     * @param keyBindType choose keybind preset
     */
    public void switchKeyBind(String keyBindType) {
        KeyBinds keyBinds = new KeyBinds();
        if ("digits".equals(keyBindType)) {
            keyBinds.setKeyBindDigits();
            logger.info("Layout set for digits");
        } else {
            keyBinds.setKeyBindQwerty();
            logger.info("Layout set for letters");
        }
    }

    /**
     * @param playBackSpeed set playback speed in percent of original (1=100%)
     */
    public void setPlayBackSpeed(double playBackSpeed) {
        if (playBackSpeed < 0) {
            throw new IllegalArgumentException("Playback speed must be above 0");
        }
        logger.info("Playback speed is set to " + playBackSpeed * 100 + " percent");
        this.playBackSpeed = playBackSpeed;
    }

    public void accentsOn() {
        accents = true;
        logger.info("Accents: " + accents);
    }

    public void accentsOff() {
        accents = false;
        logger.info("Accents: " + accents);
    }

    /**
     * @param octave    choose in which octave do you want your chord in
     * @param chordType choose chord type (relation between chord notes)
     */
    public void playChord(NoteName noteName, int octave, String chordDuration, String chordType) {
        logger.info("===============================");
        Player player = new Player();
        player.playChord(noteName, octave, chordDuration, chordType);
    }

    /**
     * @param masterVolume set master volume in percentage (1=100%)
     */
    public void setMasterVolume(double masterVolume) {
        if (masterVolume < 0 || masterVolume > 1) {
            throw new IllegalArgumentException("Invalid volume value");
        }
        logger.info("Master volume is set to " + masterVolume * 100 + " percent");
        this.masterVolume = masterVolume;
    }

    /**
     * @param sustainPedalPress set level of sustain pedal (0 is no pedal, 1=100% press)
     */
    public void setSustainPedalPress(double sustainPedalPress) {
        if (sustainPedalPress < 0 || sustainPedalPress > 1) {
            throw new IllegalArgumentException("Invalid pedal press level value");
        }
        if (sustainPedalPress > 0) {
            logger.info("Sustain pedal is pressed at " + sustainPedalPress * 100 + " percent");
        } else {
            logger.info("Sustain pedal is not used");
        }
        this.sustainPedalPress = sustainPedalPress;
    }

    public double getMasterVolume() {
        return masterVolume;
    }

    public double getMaxNoteLength() {
        return maxNoteLength;
    }

    public double getPlayBackSpeed() {
        return playBackSpeed;
    }

    public double getSustainPedalPress() {
        return sustainPedalPress;
    }

    public void setMaxNoteLength(double maxNoteLength) {
        this.maxNoteLength = maxNoteLength;
    }

}
