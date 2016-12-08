package org.fabasoad.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Yevhen Fabizhevskyi
 */
class ConsoleLogger implements Logger {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ConsoleLogger() {
    }

    @Override
    public void error(Class clazz, String message) {
        log(System.err, clazz, LogLevel.ERROR, message);
    }

    @Override
    public void flow(Class clazz, String message) {
        log(System.out, clazz, LogLevel.FLOW, message);
    }

    @Override
    public void warning(Class clazz, String message) {
        log(System.out, clazz, LogLevel.WARNING, message);
    }

    @Override
    public void debug(Class clazz, String message) {
        log(System.out, clazz, LogLevel.DEBUG, message);
    }

    private static void log(PrintStream printStream, Class clazz, LogLevel logLevel, String message) {
        printStream.println(String.format("[%s] [%s] [%s] %s",
                DATE_FORMAT.format(new Date()), logLevel.name(), clazz.getSimpleName(), message));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && Objects.equals(this.getClass(), obj.getClass());
    }
}