package org.fabasoad.log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author efabizhevsky
 * @date 11/27/2016.
 */
public class LoggerImpl implements Logger {

    private final Set<Logger> loggers = new HashSet<>();

    private LoggerImpl() {
    }

    private static LoggerImpl instance = new LoggerImpl();

    public static Logger getInstance(LoggerType... types) {
        instance.loggers.clear();
        for (LoggerType type : types) {
            if (type == LoggerType.CONSOLE) {
                instance.loggers.add(new ConsoleLogger());
            }
        }
        return instance;
    }

    @Override
    public void error(Class clazz, String message) {
        loggers.forEach(l -> l.error(clazz, message));
    }

    @Override
    public void flow(Class clazz, String message) {
        loggers.forEach(l -> l.flow(clazz, message));
    }

    @Override
    public void warning(Class clazz, String message) {
        loggers.forEach(l -> l.warning(clazz, message));
    }
}
