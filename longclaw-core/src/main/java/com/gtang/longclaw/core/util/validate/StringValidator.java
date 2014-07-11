/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Gavin
 */
public class StringValidator implements Validator <StringValidation, String> {
    
    private String fieldName;
    
    public StringValidator(String fieldName){
        this.fieldName = fieldName;
    }
    
    @Override
    public boolean validate(StringValidation validation, String value)
            throws ValidationException {
        
        ValidationException exception = new ValidationException();
        
        if(validation.nullable() == false) {
            if(StringUtils.isEmpty(value)) {
                exception.putError(getFieldName(), "cannot be null or blank.");
                throw exception;
            }
        } else { //if can be nullable and it is null or empty, then means valid
            if(StringUtils.isEmpty(value)) {
                return true;
            }
        }
        
        if(validation.min() > 0 && validation.min() > value.length()) {
            exception.putError(getFieldName(), String.format("cannnot be less than %s.", validation.min()));
        }
        
        if(validation.max() > 0 && validation.max() < value.length()) {
            exception.putError(getFieldName(), String.format("cannot be longer than %s.", validation.max()));
        }
        
        //TODO type
        
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

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Class getValidationType() {
        return StringValidation.class;
    }

}
