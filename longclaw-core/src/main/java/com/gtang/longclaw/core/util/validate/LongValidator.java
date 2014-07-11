/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Gavin
 */
public class LongValidator implements Validator <LongValidation, Long> {
    
    private final String fieldName;
    private final Class clazz;
    
    public LongValidator(String fieldName){
        this.fieldName = fieldName;
        this.clazz = LongValidation.class;
    }
    
    @Override
    public boolean validate(LongValidation validation, Long value)
            throws ValidationException {
        
        ValidationException exception = new ValidationException();
        
        if(validation.nullable() == false) {
            if(value == null) {
                exception.putError(getFieldName(), "cannot be null or blank.");
                throw exception;
            }
        } else { //if can be nullable and it is null or empty, then means valid
            if(value == null) {
                return true;
            }
        }

        if(validation.hasRange()) {
            if(value < validation.min()) {
                exception.putError(getFieldName(), String.format("cannot be less than %d", validation.min()));
            }
            
            if(value > validation.max()) {
                exception.putError(getFieldName(), String.format("cannot be greater than %d", validation.max()));
            }
        }
        
        if(exception.hasError()) {
            throw exception;
        } else {
            return true;
        }
        
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Class getValidationType() {
        return clazz;
    }
}
