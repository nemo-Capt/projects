package org.jazzteam.sidorov.onlinepiano.pianotype;

import org.jazzteam.sidorov.onlinepiano.Piano;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrandPiano implements PianoStrategy {
    private static final Logger logger = LoggerFactory.getLogger(GrandPiano.class);

    @Override
    public void info() {
        logger.info("===============================");
        logger.info("You have chosen grand piano");
        logger.info("===============================");
    }

    /**
     * @param value piano cover open level, changes master volume, 100% opened cover = +100% master volume
     */
    @Override
    public void execute(double value, Piano piano) {
        if (value > 0) {
            piano.setMasterVolume(piano.getMasterVolume() * value);
            logger.info("Cover is opened at " + value * 100 + " percent");
            logger.info("Instrument - Grand piano, master volume is now: " + piano.getMasterVolume() * 100);
        } else {
            logger.info("Cover is closed");
        }
    }
}
