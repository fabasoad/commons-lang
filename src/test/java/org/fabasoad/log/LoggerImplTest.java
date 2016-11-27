package org.fabasoad.log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    public void testError() {
        Logger logger = LoggerImpl.getInstance(LoggerType.CONSOLE);
        String message = "test error";
        logger.error(getClass(), message);
        assertTrue(outputStreamErr.toString().contains(message));
    }

    @Test
    public void testWarning() {
        Logger logger = LoggerImpl.getInstance(LoggerType.CONSOLE);
        String message = "test warning";
        logger.warning(getClass(), message);
        assertTrue(outputStreamOut.toString().contains(message));
    }

    @Test
    public void testFlow() {
        Logger logger = LoggerImpl.getInstance(LoggerType.CONSOLE);
        String message = "test flow";
        logger.warning(getClass(), message);
        assertTrue(outputStreamOut.toString().contains(message));
    }
}
