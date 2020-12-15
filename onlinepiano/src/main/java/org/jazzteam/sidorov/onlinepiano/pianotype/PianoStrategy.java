package org.jazzteam.sidorov.onlinepiano.pianotype;

import org.jazzteam.sidorov.onlinepiano.Piano;

public interface PianoStrategy {
    /**
     * @param value        value that changes standard setting
     */
    void execute(double value, Piano piano);

    void info();
}
