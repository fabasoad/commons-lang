package org.fabasoad.log;

import org.apache.commons.lang3.RandomStringUtils;
import org.fabasoad.function.TriConsumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EnumSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;

/**
 * @author efabizhevsky
 * @date 11/27/2016.
 */
public class LoggerImplTest {

    private PrintStream oldOut;
    private PrintStream oldErr;

    private ByteArrayOutputStream outputStreamOut;
    private ByteArrayOutputStream outputStreamErr;

    private static Configuration prepareConfiguration(EnumSet<LogLevel> logLevels) {
        Configuration configuration = new Configuration();
        configuration.setLoggerTypes(EnumSet.of(LoggerType.CONSOLE));
        configuration.setLogLevels(logLevels);
        return configuration;
    }

    @Before
    public void setUp() {
        outputStreamOut = new ByteArrayOutputStream();
        PrintStream psOut = new PrintStream(outputStreamOut);

        outputStreamErr = new ByteArrayOutputStream();
        PrintStream psErr = new PrintStream(outputStreamErr);

        oldOut = System.out;
        oldErr = System.err;

        System.setOut(psOut);
        System.setErr(psErr);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.err.flush();

        System.setOut(oldOut);
        System.setErr(oldErr);
    }

    private void testOutput(LogLevel logLevel, TriConsumer<Logger, Class, String> consumer, ByteArrayOutputStream outputStream) {
        Logger logger = LoggerImpl.getInstance(prepareConfiguration(EnumSet.of(logLevel)));
        String message = RandomStringUtils.randomAlphabetic(10);
        consumer.accept(logger, getClass(), message);
        assertTrue(outputStream.toString().contains(message));
        assertTrue(outputStream.toString().contains(logLevel.name()));
    }

    @Test
    public void testError() {
        testOutput(LogLevel.ERROR,);
        Logger logger = LoggerImpl.getInstance(prepareConfiguration(EnumSet.of(LogLevel.ERROR)));
        String message = "test error";
        logger.error(getClass(), message);
        assertTrue(outputStreamErr.toString().contains(message));
        assertTrue(outputStreamErr.toString().contains(LogLevel.ERROR.name()));
    }

    @Test
    public void testWarning() {
        Logger logger = LoggerImpl.getInstance(prepareConfiguration(EnumSet.of(LogLevel.WARNING)));
        String message = "test warning";
        logger.warning(getClass(), message);
        assertTrue(outputStreamOut.toString().contains(message));
        assertTrue(outputStreamOut.toString().contains(LogLevel.WARNING.name()));
    }

    @Test
    public void testFlow() {
        Logger logger = LoggerImpl.getInstance(prepareConfiguration(EnumSet.of(LogLevel.FLOW)));
        String message = "test flow";
        logger.flow(getClass(), message);
        assertTrue(outputStreamOut.toString().contains(message));
        assertTrue(outputStreamOut.toString().contains(LogLevel.FLOW.name()));
    }

    @Test
    public void testDebug() {
        Logger logger = LoggerImpl.getInstance(prepareConfiguration(EnumSet.of(LogLevel.DEBUG)));
        String message = "test debug";
        logger.debug(getClass(), message);
        assertTrue(outputStreamOut.toString().contains(message));
        assertTrue(outputStreamOut.toString().contains(LogLevel.DEBUG.name()));
    }
}
