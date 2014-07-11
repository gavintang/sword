/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

/**
 * @author Gavin
 */
public class IntegerValidator implements Validator <IntegerValidation, Integer> {
    
    private final String fieldName;
    private final Class clazz;
    
    public IntegerValidator(String fieldName){
        this.fieldName = fieldName;
        this.clazz = IntegerValidation.class;
    }
    
    @Override
    public boolean validate(IntegerValidation validation, Integer value)
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
            } else if (value > validation.max()) {
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
