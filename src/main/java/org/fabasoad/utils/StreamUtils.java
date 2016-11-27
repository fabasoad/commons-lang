package org.fabasoad.utils;

import org.fabasoad.function.FunctionThrowable;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.nonNull;

/**
 * @author Yevhen Fabizhevskyi
 */
public class StreamUtils {

    public static <T, R, E extends Throwable> Optional<R> map(
            T obj, FunctionThrowable<T, R, E> function, Consumer<Throwable> handleException) {
        Optional<R> result = Optional.empty();
        if (nonNull(obj) && nonNull(function) && nonNull(handleException)) {
            try {
                result = Optional.ofNullable(function.apply(obj));
            } catch (Throwable e) {
                handleException.accept(e);
            }
        }
        return result;
    }
}
