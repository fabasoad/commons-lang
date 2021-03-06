package io.github.fabasoad.function;

/**
 * @author Yevhen Fabizhevskyi
 */
@FunctionalInterface
public interface TriConsumer<T1, T2, T3> {

    void accept(T1 arg1, T2 arg2, T3 arg3);
}
