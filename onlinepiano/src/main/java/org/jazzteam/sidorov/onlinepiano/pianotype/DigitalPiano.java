package org.jazzteam.sidorov.onlinepiano.pianotype;

import org.jazzteam.sidorov.onlinepiano.Piano;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigitalPiano implements PianoStrategy {
    private static final Logger logger = LoggerFactory.getLogger(DigitalPiano.class);

    @Override
    public void info() {
        logger.info("===============================");
        logger.info("You have chosen digital piano");
        logger.info("===============================");
    }

    /**
     * @param value changes max note length
     */
    @Override
    public void execute(double value, Piano piano) {
        if (value < 0) {
            throw new IllegalArgumentException("Note length must be above 0");
        }
        piano.setMaxNoteLength(value * piano.getMaxNoteLength());
        logger.info("Note max length is set to " + value * 100 + " percent");
        logger.info("Note max length is now " + piano.getMaxNoteLength() + " sec");
    }

}
