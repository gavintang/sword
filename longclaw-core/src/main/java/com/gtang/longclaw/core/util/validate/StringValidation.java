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
public @interface StringValidation {
 
    int min() default 0;
    int max() default 0;
    boolean nullable() default true;
    String charset() default "";
    
    final static String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String NUMBERIC = "01234567890.,";
    final static String ALPHANUMBERIC = ALPHA + NUMBERIC;
    
}
