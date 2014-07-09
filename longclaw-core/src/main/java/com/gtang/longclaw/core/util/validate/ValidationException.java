/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gavin
 */
public class ValidationException extends Exception {
    Map <String, String> errors = new HashMap<String, String>();
   
    public ValidationException () {}
    
    public ValidationException (String field, String msg) {
        errors.put(field, msg);
    } 
    
    public void putError(String field, String msg) {
        errors.put(field, msg);
    }
    
    public boolean hasError(){
        return !errors.isEmpty();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        errors.entrySet().stream().forEach((entry) -> {
            sb.append(entry.getKey()).append("[").append(entry.getValue()).append("] ");
        });
        return sb.toString();
    }
}
