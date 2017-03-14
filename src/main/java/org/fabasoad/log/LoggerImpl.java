package org.fabasoad.log;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author efabizhevsky
 * created on 11/27/2016.
 */
public class LoggerImpl implements Logger {

    private final Set<Logger> loggers = new HashSet<>();

    private LoggerImpl() {
    }

    private static LoggerImpl instance = new LoggerImpl();

    private EnumSet<LogLevel> logLevels;

    public static Logger getInstance(Configuration config) {
        instance.logLevels = config.getLogLevels();
        instance.loggers.clear();
        for (LoggerType type : config.getLoggerTypes()) {
            if (type == LoggerType.CONSOLE) {
                instance.loggers.add(new ConsoleLogger());
            }
        }
        return instance;
    }

    @Override
    public void error(Class clazz, String message) {
        if (logLevels.contains(LogLevel.ERROR)) {
            loggers.forEach(l -> l.error(clazz, message));
        }
    }

    @Override
    public void flow(Class clazz, String message) {
        if (logLevels.contains(LogLevel.FLOW)) {
            loggers.forEach(l -> l.flow(clazz, message));
        }
    }

    @Override
    public void warning(Class clazz, String message) {
        if (logLevels.contains(LogLevel.WARNING)) {
            loggers.forEach(l -> l.warning(clazz, message));
        }
    }

    @Override
    public void debug(Class clazz, String message) {
        if (logLevels.contains(LogLevel.DEBUG)) {
            loggers.forEach(l -> l.debug(clazz, message));
        }
    }
}
