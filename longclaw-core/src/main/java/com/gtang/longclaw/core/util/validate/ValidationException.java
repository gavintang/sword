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
    private Map <String, String> errors = new HashMap<>();
    
    private String lastField;
    private String lastMsg;
    
    public ValidationException () {}
    
    public ValidationException (String field, String msg) {
        errors.put(field, msg);
        
        this.lastField = field;
        this.lastMsg = msg;
    } 
    
    public void putError(String field, String msg) {
        errors.put(field, msg);
        
        this.lastField = field;
        this.lastMsg = msg;
    }
    
    public boolean hasError(){
        return !errors.isEmpty();
    }

    public String getLastField() {
        return lastField;
    }

    public String getLastMsg() {
        return lastMsg;
    }
    
    @Override
    public String getMessage() {
        return this.toString();
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
