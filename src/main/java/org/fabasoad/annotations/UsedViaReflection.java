package org.fabasoad.annotations;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @author Yevhen Fabizhevskyi
 */
@Target({TYPE, METHOD})
public @interface UsedViaReflection {
}
