package org.fabasoad.log;

/**
 * @author efabizhevsky
 * @date 11/27/2016.
 */
public interface Logger {

    void error(Class clazz, String message);

    void flow(Class clazz, String message);

    void warning(Class clazz, String message);

    void debug(Class clazz, String message);
}