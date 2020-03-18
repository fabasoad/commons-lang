package io.github.fabasoad.function;

/**
 * @author efabizhevsky
 */
@FunctionalInterface
public interface FourConsumer<T1, T2, T3, T4> {

    void accept(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
}
