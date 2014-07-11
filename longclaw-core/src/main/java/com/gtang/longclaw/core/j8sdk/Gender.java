/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.j8sdk;

/**
 *
 * @author gtang
 */
public enum Gender {
    MAIL(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private Gender(int code) {
        this.code = code;
    }
    
    static Gender findByCode(int code) {
        for(Gender g : Gender.values()) {
           if(g.getCode()== code) return g; 
        }
        
        throw new IllegalArgumentException(
                String.format("Invalid gender code, code=%s", code)
        );
    }

    private int getCode() {
        return code;
    }
    
    private int code;
}


