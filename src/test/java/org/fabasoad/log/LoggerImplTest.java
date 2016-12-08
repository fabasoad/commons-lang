package org.fabasoad.log;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * @author efabizhevsky
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

    private void testOutput(Map<Pair<LogLevel, ByteArrayOutputStream>, Function<Logger, BiConsumer<Class, String>>> map) {
        final EnumSet<LogLevel> logLevels =
                EnumSet.copyOf(map.keySet().stream().map(Pair::getLeft).collect(Collectors.toList()));
        final Logger logger = LoggerImpl.getInstance(prepareConfiguration(logLevels));
        map.forEach((pair, function) -> {
            String message = RandomStringUtils.randomAlphabetic(10);
            function.apply(logger).accept(getClass(), message);
            assertTrue(pair.getRight().toString().contains(message));
            assertTrue(pair.getRight().toString().contains(pair.getLeft().name()));
        });
    }

    @Test
    public void testError() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.ERROR, outputStreamErr), l -> l::error
        ));
    }

    @Test
    public void testWarning() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.WARNING, outputStreamOut), l -> l::warning
        ));
    }

    @Test
    public void testFlow() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.FLOW, outputStreamOut), l -> l::flow
        ));
    }

    @Test
    public void testDebug() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.DEBUG, outputStreamOut), l -> l::debug
        ));
    }

    @Test
    public void testFlowDebug() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.FLOW, outputStreamOut), l -> l::flow,
                Pair.of(LogLevel.DEBUG, outputStreamOut), l -> l::debug
        ));
    }

    @Test
    public void testWarningDebug() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.WARNING, outputStreamOut), l -> l::warning,
                Pair.of(LogLevel.DEBUG, outputStreamOut), l -> l::debug
        ));
    }

    @Test
    public void testWarningFlowDebug() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.WARNING, outputStreamOut), l -> l::warning,
                Pair.of(LogLevel.FLOW, outputStreamOut), l -> l::flow,
                Pair.of(LogLevel.DEBUG, outputStreamOut), l -> l::debug
        ));
    }

    @Test
    public void testWarningFlowDebugError() {
        testOutput(ImmutableMap.of(
                Pair.of(LogLevel.WARNING, outputStreamOut), l -> l::warning,
                Pair.of(LogLevel.FLOW, outputStreamOut), l -> l::flow,
                Pair.of(LogLevel.DEBUG, outputStreamOut), l -> l::debug,
                Pair.of(LogLevel.ERROR, outputStreamErr), l -> l::error
        ));
    }
}
