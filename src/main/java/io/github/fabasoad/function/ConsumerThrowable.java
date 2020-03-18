package io.github.fabasoad.function;

@FunctionalInterface
public interface ConsumerThrowable<T, E extends Throwable> {

    void accept(T arg) throws E;
}
