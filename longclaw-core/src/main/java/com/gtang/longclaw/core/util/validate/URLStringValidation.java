/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Gavin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface URLStringValidation {
 
    int min();
    int max();
    boolean nullable();
    Type type();
    
    static enum Type {
        ALPHA,
        ALPHANUMERIC,
        NUMBER
    }
    
}
