package org.fabasoad.log;

import java.util.EnumSet;

/**
 * @author efabizhevsky
 * created on 12/8/2016.
 */
public class Configuration {

    private EnumSet<LogLevel> logLevels;
    private EnumSet<LoggerType> loggerTypes;

    public void setLogLevels(EnumSet<LogLevel> logLevels) {
        this.logLevels = logLevels;
    }

    EnumSet<LogLevel> getLogLevels() {
        return logLevels;
    }

    public void setLoggerTypes(EnumSet<LoggerType> loggerTypes) {
        this.loggerTypes = loggerTypes;
    }

    EnumSet<LoggerType> getLoggerTypes() {
        return loggerTypes;
    }
}
