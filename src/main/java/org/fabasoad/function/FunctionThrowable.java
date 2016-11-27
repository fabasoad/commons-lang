package org.fabasoad.function;

/**
 * @author Yevhen Fabizhevskyi
 */
@FunctionalInterface
public interface FunctionThrowable<T, R, E extends Throwable> {

    R apply(T obj) throws E;
}
