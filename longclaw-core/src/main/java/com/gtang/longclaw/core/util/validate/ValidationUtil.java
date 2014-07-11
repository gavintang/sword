/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Gavin
 */
public class ValidationUtil {
    
    private static final Logger logger = Logger.getLogger(ValidationUtil.class);
    
    private static Map <Class <? extends Validateable>, Validators> validatorsMap = new HashMap <> ();
    
    private ValidationUtil(){}
    
    final public static void registry(Class <? extends Validateable> ownerType){
        
        Validators validators = new Validators(ownerType);
        
        for(Method method : ownerType.getMethods()) {
            
            if(method.getName().startsWith("get")) {                
                String fieldName = method.getName().substring(3,4).toLowerCase()
                        + method.getName().substring(4);
                
                for (Annotation annotation : method.getAnnotations()) {

                    if (annotation.annotationType().getName().endsWith("Validation"))  {
                        String validatorName = annotation.annotationType().getName().replace("Validation", "Validator");
                        try {
                            Class clazz = Class.forName(validatorName);
                            Constructor constructor = clazz.getConstructor(String.class);
                            Validator validator = (Validator)constructor.newInstance(fieldName);
                            validators.addValidator(validator);
                        } catch (ClassNotFoundException ex) {
                            //Simplly ignore it, in case didn't find the validator
                        } catch (NoSuchMethodException ex) {
                            logger.error("Failed to find init validator", ex);
                        } catch (SecurityException ex) {
                            logger.error("Failed to find init validator", ex);
                        } catch (InstantiationException ex) {
                            logger.error("Failed to find init validator", ex);
                        } catch (IllegalAccessException ex) {
                            logger.error("Failed to find init validator", ex);
                        } catch (IllegalArgumentException ex) {
                            logger.error("Failed to find init validator", ex);
                        } catch (InvocationTargetException ex) {
                            logger.error("Failed to find init validator", ex);
                        }
                    }
                }
            }
        }
        
        validatorsMap.put(ownerType, validators);
    }
    
    final public static Validators getValidators (Class <? extends Validateable> ownerType) {
        return validatorsMap.get(ownerType);
    }
    
    final public static void remove(Class <? extends Validateable> ownerType) {
        validatorsMap.remove(ownerType);
    }
    
    final public static void validate(Object obj) throws ValidationException {
        
        Class clazz = obj.getClass();
        Validators validators = validatorsMap.get(clazz);
        List<ValidationException> exList = validators.validate(obj);
        
        //if Validation doesn't pass, then merge all ValidationException
        if(exList != null && !exList.isEmpty()) {
            ValidationException ex = new ValidationException();
            exList.stream().forEach(e -> {
                ex.putError(e.getLastField(), e.getLastMsg());
            });
            throw ex;
        }
    }
    
}
