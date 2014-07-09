/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Gavin
 */
public class StringValidationUtil {
    
    private StringValidationUtil(){}
    
    final static boolean validate(StringValidation validation, String field, String value)
            throws ValidationException {
        
        ValidationException exception = new ValidationException();
        
        if(validation.nullable() == false) {
            if(StringUtils.isEmpty(value)) {
                exception.putError(field, "cannot be null or blank.");
                throw exception;
            }
        } else { //if can be nullable and it is null or empty, then means valid
            if(StringUtils.isEmpty(value)) {
                return true;
            }
        }
        
        if(validation.min() > value.length()) {
            exception.putError(field, String.format("cannnot less than %s.", validation.min()));
        }
        
        if(validation.max() < value.length()) {
            exception.putError(field, String.format("cannot longer than %s.", validation.max()));
        }
        
        //TODO type
        
        if(exception.hasError()) {
            throw exception;
        } else {
            return true;
        }
        
    }
    
}
