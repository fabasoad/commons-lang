package org.fabasoad.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yevhen Fabizhevskyi
 * @date 02.04.2016.
 */
public class Logger {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Logger instance = new Logger();

    public static Logger getInstance() {
        return instance;
    }

    private Logger() {
    }

    public void error(Class clazz, String message) {
        log(System.err, clazz, "ERROR", message);
    }

    public void flow(Class clazz, String message) {
        log(System.out, clazz, "FLOW", message);
    }

    public void warning(Class clazz, String message) {
        log(System.out, clazz, "WARNING", message);
    }

    private static void log(PrintStream printStream, Class clazz, String logType, String message) {
        printStream.println(String.format("[%s] [%s] [%s] %s",
                DATE_FORMAT.format(new Date()), logType, clazz.getSimpleName(), message));
    }
}