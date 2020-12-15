package org.jazzteam.sidorov.onlinepiano.pianotype;

import org.jazzteam.sidorov.onlinepiano.Piano;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UprightPiano implements PianoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(UprightPiano.class);

    @Override
    public void info() {
        logger.info("===============================");
        logger.info("You have chosen upright piano");
        logger.info("===============================");
    }

    /**
     * @param value piano soft pedal press level, changes master volume, 90% pressed pedal = -90% master volume
     */
    @Override
    public void execute(double value, Piano piano) {
        if (value > 0) {
            piano.setMasterVolume(Math.round((piano.getMasterVolume() - piano.getMasterVolume() * value) * 100.00) / 100.00);
            logger.info("Soft pedal is pressed at " + value * 100 + " percent");
            logger.info("Master volume is now " + piano.getMasterVolume() * 100);
        } else {
            logger.info("Soft pedal is not used");
        }
    }

}
