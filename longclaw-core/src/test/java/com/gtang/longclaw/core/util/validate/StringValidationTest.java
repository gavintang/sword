/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Gavin
 */
public class StringValidationTest {
    
    private final static Logger logger = Logger.getLogger(StringValidationTest.class.getName());
    private final static String GETTER = "getField";
    
    @Test
    public void testStringValidation(){
        
        MyObject myobj = new MyObject("ABCDE");
        
        try {
            Method method = MyObject.class.getMethod(GETTER, (Class<?>[]) null);
            Assert.assertNotNull(method);
            Assert.assertEquals(1, method.getAnnotations().length);
            
            String value = (String) method.invoke(myobj);
            
            for (Annotation annotation : method.getAnnotations() ){
                if(annotation instanceof StringValidation) {
                    StringValidation validation = (StringValidation) annotation;
                    Assert.assertEquals(2, validation.min());
                    Assert.assertEquals(5, validation.max());
                    Assert.assertEquals(false, validation.nullable());
                    Assert.assertEquals(StringValidation.Type.ALPHANUMERIC, validation.type());
                    
                    String fieldName = GETTER.substring(3, 4).toUpperCase() + GETTER.substring(4);
                    StringValidationUtil.validate(validation, fieldName, value);
                }
            }
            
        } catch (NoSuchMethodException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StringValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(StringValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(StringValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            Logger.getLogger(StringValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static class MyObject {
        
        private String field;
        
        MyObject(String field) {
            this.field = field;
        }
        
        @StringValidation(min=2, max=5, nullable=false, type=StringValidation.Type.ALPHANUMERIC)
        public String getField() {
            return field;
        }
    
    }
    
}
