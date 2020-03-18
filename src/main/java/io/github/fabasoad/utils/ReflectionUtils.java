package io.github.fabasoad.utils;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Yevhen Fabizhevskyi
 */
public class ReflectionUtils {

    private static Reflections create(String packageName) {
        return new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage(packageName))
            .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));
    }

    public static <T extends Annotation> Set<Class<?>> getTypesAnnotatedWith(String packageName, Class<T> annotation) {
        return create(packageName).getTypesAnnotatedWith(annotation);
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(String packageName, Class<T> clazz) {
        return create(packageName).getSubTypesOf(clazz);
    }

    public static <T, R> Optional<R> invokeThrowable(
        T obj, String methodName, Class<R> returnedType, Consumer<Throwable> handleException) {
        Optional<R> result = Optional.empty();
        try {
            result = Optional.ofNullable(returnedType.cast(obj.getClass().getMethod(methodName).invoke(obj)));
        } catch (Throwable e) {
            handleException.accept(e);
        }
        return result;
    }
}
